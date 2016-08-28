package firebasebarcelona.wallapadel.ui;

import android.content.res.Resources;
import android.util.TypedValue;
import firebasebarcelona.wallapadel.app.PadelApplication;
import javax.inject.Inject;

public class DpToPxConverse {
  @Inject
  public DpToPxConverse() {
  }

  public float dpToPx(int dp) {
    Resources r = PadelApplication.getInstance().getResources();
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics());
  }
}
