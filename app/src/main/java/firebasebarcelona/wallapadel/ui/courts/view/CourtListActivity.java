package firebasebarcelona.wallapadel.ui.courts.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import firebasebarcelona.wallapadel.R;

public class CourtListActivity extends AppCompatActivity {
  @BindView(R.id.container) FrameLayout container;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_court_list);
    ButterKnife.bind(this);
    getSupportFragmentManager().beginTransaction().add(R.id.container, new CourtListFragment(), CourtListFragment.TAG).commit();
  }
}
