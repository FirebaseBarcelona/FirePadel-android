package firebasebarcelona.wallapadel.ui.courts.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.ButterKnife;
import firebasebarcelona.wallapadel.R;
import firebasebarcelona.wallapadel.ui.common.ImageLoader;
import firebasebarcelona.wallapadel.ui.models.CourtViewModel;
import firebasebarcelona.wallapadel.ui.models.PlayerViewModel;

public class CourtAdapter extends RecyclerView.Adapter<CourtViewHolder> {
    private final List<CourtViewModel> items;
    private final ImageLoader imageLoader;

    public CourtAdapter(List<CourtViewModel> items, ImageLoader imageLoader) {
        this.items = items;
        this.imageLoader = imageLoader;
    }

    public void setCourts(List<CourtViewModel> items) {
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public CourtViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_court, parent, false);
        return new CourtViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CourtViewHolder holder, int position) {
        CourtViewModel item = items.get(position);
        List<PlayerViewModel> players = item.getPlayers();
        for (int playerPosition = 0; playerPosition < players.size(); playerPosition++) {
            ImageView avatar = ButterKnife.findById(holder.players.getChildAt(playerPosition), R.id.avatar);
            imageLoader.loadImage(holder.itemView.getContext(), avatar, players.get(playerPosition).getPhotoUrl());
            TextView name = ButterKnife.findById(holder.players.getChildAt(playerPosition), R.id.name);
            name.setText(players.get(playerPosition).getName());
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
