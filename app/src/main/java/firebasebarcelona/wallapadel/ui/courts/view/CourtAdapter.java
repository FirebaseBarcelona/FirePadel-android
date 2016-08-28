package firebasebarcelona.wallapadel.ui.courts.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;
import firebasebarcelona.wallapadel.R;
import firebasebarcelona.wallapadel.ui.common.ImageLoader;
import firebasebarcelona.wallapadel.ui.models.CourtViewModel;
import firebasebarcelona.wallapadel.ui.models.PlayerViewModel;
import java.util.List;

public class CourtAdapter extends RecyclerView.Adapter<CourtViewHolder> implements CourtViewHolder.CourtViewHolderEvents {
  private static final int MAX_PLAYERS = 4;
  private final List<CourtViewModel> items;
  private final ImageLoader imageLoader;
  private final PlayerViewModel myPlayer;
  private final CourtAdapterEvents events;

  public CourtAdapter(List<CourtViewModel> items, ImageLoader imageLoader, PlayerViewModel myPlayer, CourtAdapterEvents events) {
    this.items = items;
    this.imageLoader = imageLoader;
    this.myPlayer = myPlayer;
    this.events = events;
  }

  public void setCourts(List<CourtViewModel> items) {
    this.items.clear();
    this.items.addAll(items);
    notifyDataSetChanged();
  }

  @Override
  public CourtViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_court, parent, false);
    return new CourtViewHolder(view, this);
  }

  @Override
  public void onBindViewHolder(CourtViewHolder holder, int position) {
    CourtViewModel item = items.get(position);
    List<PlayerViewModel> players = item.getPlayers();
    resetViewHolder(holder);
    if (players.isEmpty()) {
      holder.titleNoPlayers.setVisibility(View.VISIBLE);
      holder.addPlayer.setImageResource(R.drawable.add);
      holder.chat.setVisibility(View.INVISIBLE);
    } else {
      holder.titleNoPlayers.setVisibility(View.GONE);
      for (int playerPosition = 0; playerPosition < players.size(); playerPosition++) {
        PlayerViewModel player = players.get(playerPosition);
        ImageView avatar = ButterKnife.findById(holder.players.getChildAt(playerPosition), R.id.avatar);
        imageLoader.loadImage(holder.itemView.getContext(), avatar, player.getPhotoUrl());
        TextView name = ButterKnife.findById(holder.players.getChildAt(playerPosition), R.id.name);
        name.setText(player.getName());
        if (myPlayer != null && player.getId().equals(myPlayer.getId())) {
          playerInCourt(holder);
        } else {
          playerNotInCourt(holder);
        }
      }
    }
  }

  private void playerNotInCourt(CourtViewHolder holder) {
    holder.addPlayer.setImageResource(R.drawable.add);
    holder.chat.setVisibility(View.GONE);
  }

  private void playerInCourt(CourtViewHolder holder) {
    holder.chat.setVisibility(View.VISIBLE);
    holder.addPlayer.setImageResource(R.drawable.remove);
  }

  private void resetViewHolder(CourtViewHolder holder) {
    for (int i = 0; i < MAX_PLAYERS; i++) {
      ImageView imageView = ButterKnife.findById(holder.players.getChildAt(i), R.id.avatar);
      imageView.setImageDrawable(null);
      TextView name = ButterKnife.findById(holder.players.getChildAt(i), R.id.name);
      name.setText("");
    }
  }

  @Override
  public int getItemCount() {
    return items.size();
  }

  @Override
  public void onAddButtonClick(int position) {
    CourtViewModel courtViewModel = items.get(position);
    String id = courtViewModel.getId();
    events.onRequestAddPlayerToCourt(id);
  }

  @Override
  public void onChatClicked(int position) {
    CourtViewModel courtViewModel = items.get(position);
    String courtId = courtViewModel.getId();
    events.onRequestToChat(courtId);
  }

  public void updateCourt(CourtViewModel court) {
    for (int position = 0; position < items.size(); position++) {
      if (court.getId().equals(items.get(position).getId())) {
        items.set(position, court);
      }
    }
    notifyItemRangeChanged(0, 2);
  }

  interface CourtAdapterEvents {
    void onRequestAddPlayerToCourt(String courtId);
    void onRequestToChat(String courtId);
  }
}
