package firebasebarcelona.wallapadel.ui.chat.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import firebasebarcelona.wallapadel.R;

public class ChatViewHolder extends RecyclerView.ViewHolder {
  @BindView(R.id.message) TextView message;

  public ChatViewHolder(View itemView) {
    super(itemView);
    ButterKnife.bind(this, itemView);
  }
}
