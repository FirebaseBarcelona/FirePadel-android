package firebasebarcelona.wallapadel.ui.courts.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import firebasebarcelona.wallapadel.R;
import firebasebarcelona.wallapadel.app.PadelApplication;
import firebasebarcelona.wallapadel.app.di.component.DaggerViewComponent;
import firebasebarcelona.wallapadel.app.di.module.ViewModule;
import firebasebarcelona.wallapadel.ui.DpConversor;
import firebasebarcelona.wallapadel.ui.chat.view.ChatActivity;
import firebasebarcelona.wallapadel.ui.common.ImageLoader;
import firebasebarcelona.wallapadel.ui.courts.presentation.CourtListPresenter;
import firebasebarcelona.wallapadel.ui.courts.presentation.CourtListView;
import firebasebarcelona.wallapadel.ui.models.CourtViewModel;
import firebasebarcelona.wallapadel.ui.models.PlayerViewModel;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class CourtListFragment extends Fragment
implements CourtListView, GoogleApiClient.OnConnectionFailedListener, CourtAdapter.CourtAdapterEvents {
  public static final String TAG = CourtListFragment.class.getSimpleName();
  @Inject CourtListPresenter presenter;
  @Inject ImageLoader imageLoader;
  @Inject DpConversor dpConversor;
  @BindView(R.id.courts_list) RecyclerView courts;
  private CourtAdapter courtAdapter;
  private GoogleSignInOptions googleSignInOptions;
  private GoogleApiClient client;
  private PlayerViewModel myPlayer;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_court_list, container, false);
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    ButterKnife.bind(this, view);
    DaggerViewComponent.builder().applicationComponent(PadelApplication.getInstance().getApplicationComponent()).viewModule(
    new ViewModule(this)).build().inject(this);
    initFirebase();
    if (savedInstanceState == null) {
      initGoogleApi();
    }
    initRecyclerView();
  }

  private void initGoogleApi() {
    googleSignInOptions =
    new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestProfile().requestEmail().requestIdToken(
    getString(R.string.credential_android_client)).build();
    client = new GoogleApiClient.Builder(getContext().getApplicationContext()).enableAutoManage(getActivity(), this).addApi(
    Auth.GOOGLE_SIGN_IN_API, googleSignInOptions).build();
  }

  private void initRecyclerView() {
    courtAdapter = new CourtAdapter(new ArrayList<CourtViewModel>(), imageLoader, myPlayer, this);
    courts.setHasFixedSize(true);
    courts.setLayoutManager(new LinearLayoutManager(getContext()));
    courts.setAdapter(courtAdapter);
    courts.addItemDecoration(new CourtsItemDecorator(dpConversor));
  }

  @Override
  public void showCourts(List<CourtViewModel> courts) {
    courtAdapter.setCourts(courts);
  }

  @Override
  public void updateCourt(CourtViewModel court) {
    courtAdapter.updateCourt(court);
  }

  @Override
  public void showLoginProcess() {
  }

  @Override
  public void onRequestAddPlayerToCourt(String courtId) {
    presenter.requestAddLocalPlayerToCourt(courtId);
  }

  @Override
  public void onRequestToChat(String courtId) {
    presenter.requestToChat(courtId);
  }

  //TODO every piece of code from here needs a refactor
  public static final int RC_GOOGLE_LOGIN = 333;
  private FirebaseAuth firebaseAuth;
  private FirebaseAuth.AuthStateListener authStateListener;

  private void initFirebase() {
    firebaseAuth = FirebaseAuth.getInstance();
    if (firebaseAuth.getCurrentUser() != null) {
      saveLocalPlayer(firebaseAuth.getCurrentUser());
    }
    authStateListener = new FirebaseAuth.AuthStateListener() {
      @Override
      public void onAuthStateChanged(@NonNull FirebaseAuth auth) {
        FirebaseUser user = auth.getCurrentUser();
        if (user != null) {
          saveLocalPlayer(user);
        } else {
          Toast.makeText(getActivity(), "Auth fail", Toast.LENGTH_SHORT).show();
        }
      }
    };
  }

  private void saveLocalPlayer(FirebaseUser user) {
    Uri photoUri = user.getPhotoUrl();
    PlayerViewModel playerViewModel = new PlayerViewModel.Builder().id(user.getUid()).email(user.getEmail()).photoUrl(
    photoUri == null ? "" : photoUri.toString()).name(user.getDisplayName()).build();
    presenter.setLocalPlayer(playerViewModel);
  }

  @Override
  public void onStart() {
    super.onStart();
    firebaseAuth.addAuthStateListener(authStateListener);
    presenter.subscribeToCourts();
  }

  @Override
  public void onStop() {
    super.onStop();
    if (authStateListener != null) {
      firebaseAuth.removeAuthStateListener(authStateListener);
    }
    presenter.unsubscribeToCourts();
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == RC_GOOGLE_LOGIN) {
      GoogleSignInResult googleSignInResult = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
      if (googleSignInResult.isSuccess()) {
        // Google Sign In was successful, authenticate with Firebase
        GoogleSignInAccount account = googleSignInResult.getSignInAccount();
        Toast.makeText(getContext(), "Login successfullnessly", Toast.LENGTH_SHORT).show();
        firebaseAuthWithGoogle(account);
      } else {
        // Google Sign In failed, update UI appropriately
        // ...
        Toast.makeText(getContext(), "Login failed", Toast.LENGTH_SHORT).show();
      }
    }
  }

  private void firebaseAuthWithGoogle(GoogleSignInAccount googleSignInAccount) {
    AuthCredential credential = GoogleAuthProvider.getCredential(googleSignInAccount.getIdToken(), null);
    firebaseAuth.signInWithCredential(credential).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
      @Override
      public void onComplete(@NonNull Task<AuthResult> task) {
        if (!task.isSuccessful()) {
          Toast.makeText(getContext(), "Authentication failed.", Toast.LENGTH_SHORT).show();
        }
      }
    });
  }

  @Override
  public void loginWithGoogle() {
    Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(client);
    startActivityForResult(signInIntent, RC_GOOGLE_LOGIN);
  }

  @Override
  public void setMyPlayer(PlayerViewModel myPlayer) {
    this.myPlayer = myPlayer;
    ((CourtListParent) getActivity()).renderLoggedUser(myPlayer);
  }

  @Override
  public void openChat(String courtId) {
    Intent i = ChatActivity.newIntent(getActivity(), courtId);
    startActivity(i);
  }

  @Override
  public void renderAddPlayerToCourtError() {
    Toast.makeText(getActivity(), "You are already in another court", Toast.LENGTH_SHORT).show();
  }

  @Override
  public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
  }

  public interface CourtListParent {
    void renderLoggedUser(PlayerViewModel playerViewModel);
  }
}
