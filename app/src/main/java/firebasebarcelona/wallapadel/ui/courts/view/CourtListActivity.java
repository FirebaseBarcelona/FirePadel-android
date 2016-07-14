package firebasebarcelona.wallapadel.ui.courts.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import firebasebarcelona.wallapadel.R;
import firebasebarcelona.wallapadel.ui.login.view.LoginActivity;

public class CourtListActivity extends AppCompatActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_court_list);
    Intent i = new Intent(this, LoginActivity.class);
    startActivity(i);
  }
}
