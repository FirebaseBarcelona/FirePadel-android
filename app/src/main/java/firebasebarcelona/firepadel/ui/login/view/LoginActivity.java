package firebasebarcelona.firepadel.ui.login.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import firebasebarcelona.firepadel.R;

public class LoginActivity extends AppCompatActivity {
  public static final String LOGIN_FRAGMENT_TAG = "Login";

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activit_login);
    loadFragments(savedInstanceState);
  }

  private void loadFragments(Bundle savedInstanceState) {
    if (savedInstanceState == null) {
      getSupportFragmentManager().beginTransaction().add(R.id.container, new GoogleLoginFragment(), LOGIN_FRAGMENT_TAG).commit();
    }
  }
}
