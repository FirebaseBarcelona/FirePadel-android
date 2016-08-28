package firebasebarcelona.wallapadel.ui.courts.view;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import firebasebarcelona.wallapadel.R;

public class CourtViewHolder extends RecyclerView.ViewHolder {
  private final CourtViewHolderEvents events;
  @BindView(R.id.players) LinearLayout players;
  @BindView(R.id.chat) FloatingActionButton chat;
  @BindView(R.id.title_no_players) TextView titleNoPlayers;
  @BindView(R.id.add_player) FloatingActionButton addPlayer;

  public CourtViewHolder(View itemView, CourtViewHolderEvents events) {
    super(itemView);
    this.events = events;
    ButterKnife.bind(this, itemView);
  }

  @OnClick(R.id.add_player)
  void onAddPlayerClicked() {
    events.onAddButtonClick(getAdapterPosition());
  }

  @OnClick(R.id.chat)
  void onChatClicked() {
    events.onChatClicked(getAdapterPosition());
  }

  interface CourtViewHolderEvents {
    void onAddButtonClick(int position);
    void onChatClicked(int position);
  }
}
