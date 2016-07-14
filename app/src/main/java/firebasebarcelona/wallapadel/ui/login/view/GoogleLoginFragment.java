package firebasebarcelona.wallapadel.ui.login.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.OnClick;
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
import firebasebarcelona.wallapadel.ui.di.component.DaggerViewComponent;
import firebasebarcelona.wallapadel.ui.login.presentation.GoogleLoginPresenter;
import firebasebarcelona.wallapadel.ui.login.presentation.GoogleLoginView;
import javax.inject.Inject;

public class GoogleLoginFragment extends Fragment implements GoogleLoginView, GoogleApiClient.OnConnectionFailedListener {
  public static final int RC_GOOGLE_LOGIN = 333;
  @Inject GoogleLoginPresenter loginPresenter;
  private FirebaseAuth firebaseAuth;
  private FirebaseAuth.AuthStateListener authStateListener;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    doInjection();
    initFirebase();
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

  private void initFirebase() {
    firebaseAuth = FirebaseAuth.getInstance();
    authStateListener = new FirebaseAuth.AuthStateListener() {
      @Override
      public void onAuthStateChanged(@NonNull FirebaseAuth auth) {
        FirebaseUser user = auth.getCurrentUser();
        if (user != null) {
          Toast.makeText(getActivity(), "Auth correct " + user.getUid(), Toast.LENGTH_SHORT).show();
        } else {
          Toast.makeText(getActivity(), "Auth fail", Toast.LENGTH_SHORT).show();
        }
      }
    };
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

  private void doInjection() {
    DaggerViewComponent.builder().build().inject(this);
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.login_fragment, container, false);
    ButterKnife.bind(this, view);
    return view;
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
  }

  @OnClick(R.id.login_button)
  public void onGoogleLoginClick() {
    GoogleSignInOptions googleSignInOptions =
    new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().requestIdToken(
    getString(R.string.credential_android_client)).build();
    GoogleApiClient client =
    new GoogleApiClient.Builder(getContext().getApplicationContext()).enableAutoManage(getActivity(), this).addApi(
    Auth.GOOGLE_SIGN_IN_API, googleSignInOptions).build();
    Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(client);
    startActivityForResult(signInIntent, RC_GOOGLE_LOGIN);
  }

  @Override
  public void onConnectionFailed(@NonNull ConnectionResult result) {
  }
}
