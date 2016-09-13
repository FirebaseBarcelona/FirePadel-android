package firebasebarcelona.firepadel.ui.chat.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import firebasebarcelona.firepadel.R;

public class ChatViewHolder extends RecyclerView.ViewHolder {
  @BindView(R.id.message) TextView message;
  @BindView(R.id.avatar) ImageView avatar;

  public ChatViewHolder(View itemView) {
    super(itemView);
    ButterKnife.bind(this, itemView);
  }
}
