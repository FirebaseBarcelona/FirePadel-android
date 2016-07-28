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

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import firebasebarcelona.wallapadel.R;
import firebasebarcelona.wallapadel.ui.common.ImageLoader;
import firebasebarcelona.wallapadel.ui.courts.presentation.CourtListPresenter;
import firebasebarcelona.wallapadel.ui.courts.presentation.CourtListView;
import firebasebarcelona.wallapadel.ui.di.component.DaggerViewComponent;
import firebasebarcelona.wallapadel.ui.di.module.ViewModule;
import firebasebarcelona.wallapadel.ui.models.CourtViewModel;
import firebasebarcelona.wallapadel.ui.models.PlayerViewModel;

public class CourtListFragment extends Fragment implements CourtListView, GoogleApiClient.OnConnectionFailedListener,
    CourtAdapter.CourtAdapterEvents {
    public static final String TAG = CourtListFragment.class.getSimpleName();
    @Inject
    CourtListPresenter presenter;
    @Inject
    ImageLoader imageLoader;
    @BindView(R.id.courts_list)
    RecyclerView courts;
    private CourtAdapter courtAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_court_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        DaggerViewComponent.builder().viewModule(new ViewModule(this)).build().inject(this);
        initRecyclerView();
        initFirebase();
        presenter.requestCourts();
    }

    private void initRecyclerView() {
        courtAdapter = new CourtAdapter(new ArrayList<CourtViewModel>(), imageLoader, this);
        courts.setHasFixedSize(true);
        courts.setLayoutManager(new LinearLayoutManager(getContext()));
        courts.setAdapter(courtAdapter);
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
                    Toast.makeText(getActivity(), "Auth correct " + user.getUid(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Auth fail", Toast.LENGTH_SHORT).show();
                }
            }
        };
    }

    private void saveLocalPlayer(FirebaseUser user) {
        Uri photoUri = user.getPhotoUrl();
        PlayerViewModel playerViewModel = new PlayerViewModel.Builder().id(user.getUid()).photoUrl(photoUri == null ? "" : photoUri.toString()).name(user.getDisplayName()).build();
        presenter.setLocalPlayer(playerViewModel);
    }

    @Override
    public void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (authStateListener != null) {
            firebaseAuth.removeAuthStateListener(authStateListener);
        }
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
        GoogleSignInOptions googleSignInOptions =
                new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestProfile().requestEmail().requestIdToken(
                        getString(R.string.credential_android_client)).build();
        GoogleApiClient client =
                new GoogleApiClient.Builder(getContext().getApplicationContext()).enableAutoManage(getActivity(), this).addApi(
                        Auth.GOOGLE_SIGN_IN_API, googleSignInOptions).build();
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(client);
        startActivityForResult(signInIntent, RC_GOOGLE_LOGIN);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
