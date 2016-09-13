package firebasebarcelona.firepadel.ui.chat.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import firebasebarcelona.firepadel.R;

public class ChatActivity extends AppCompatActivity {
  private static final String TAG = "ChatFragment";
  @BindView(R.id.container) FrameLayout container;
  @BindView(R.id.toolbar) Toolbar toolbar;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_chat);
    ButterKnife.bind(this);
    if (savedInstanceState == null) {
      getSupportFragmentManager().beginTransaction().add(R.id.container, ChatFragment.newInstance(getIntent().getExtras()),
      ChatActivity.TAG).commit();
    }
    initActionBar();
  }

  private void initActionBar() {
    setSupportActionBar(toolbar);
  }

  public static Intent newIntent(Context context, String courtId) {
    Intent i = new Intent(context, ChatActivity.class);
    Bundle extras = new Bundle();
    extras.putString(ChatFragment.COURT_ID, courtId);
    i.putExtras(extras);
    return i;
  }
}
