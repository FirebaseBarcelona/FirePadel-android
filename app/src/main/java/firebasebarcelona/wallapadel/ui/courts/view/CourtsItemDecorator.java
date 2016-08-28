package firebasebarcelona.wallapadel.ui.courts.view;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import firebasebarcelona.wallapadel.ui.DpToPxConversor;

public class CourtsItemDecorator extends RecyclerView.ItemDecoration {
  private final DpToPxConversor conversor;

  public CourtsItemDecorator(DpToPxConversor conversor) {
    this.conversor = conversor;
  }

  @Override
  public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
    super.getItemOffsets(outRect, view, parent, state);
    outRect.set(0, (int) conversor.dpToPx(30), 0, (int) conversor.dpToPx(30));
  }
}
