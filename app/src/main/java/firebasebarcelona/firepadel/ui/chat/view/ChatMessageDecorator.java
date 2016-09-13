package firebasebarcelona.firepadel.ui.chat.view;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class ChatMessageDecorator extends RecyclerView.ItemDecoration {
  @Override
  public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
    super.getItemOffsets(outRect, view, parent, state);
    outRect.set(0,30, 0, 30);
  }
}
