package firebasebarcelona.wallapadel.ui.courts.view;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import firebasebarcelona.wallapadel.R;
import firebasebarcelona.wallapadel.ui.models.PlayerViewModel;

public class CourtListActivity extends AppCompatActivity implements CourtListFragment.CourtListParent {
  @BindView(R.id.container) FrameLayout container;
  @BindView(R.id.avatar) ImageView avatar;
  @BindView(R.id.toolbar) Toolbar toolbar;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_court_list);
    ButterKnife.bind(this);
    if (savedInstanceState == null) {
      getSupportFragmentManager().beginTransaction().add(R.id.container, new CourtListFragment(), CourtListFragment.TAG).commit();
    }
    initActionBar();
  }

  private void initActionBar() {
    setSupportActionBar(toolbar);
  }

  @Override
  public void renderLoggedUser(PlayerViewModel playerViewModel) {
    Glide.with(this).load(playerViewModel.getPhotoUrl()).asBitmap().centerCrop().into(new BitmapImageViewTarget(avatar){
      @Override
      protected void setResource(Bitmap resource) {
        RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(), resource);
        circularBitmapDrawable.setCircular(true);
        avatar.setImageDrawable(circularBitmapDrawable);
      }
    });
  }
}
