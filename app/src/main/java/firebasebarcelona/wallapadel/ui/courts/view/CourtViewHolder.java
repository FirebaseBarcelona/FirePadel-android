package firebasebarcelona.wallapadel.ui.courts.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import firebasebarcelona.wallapadel.R;

public class CourtViewHolder extends RecyclerView.ViewHolder {
    private final CourtViewHolderEvents events;
    @BindView(R.id.players)
    LinearLayout players;

    public CourtViewHolder(View itemView, CourtViewHolderEvents events) {
        super(itemView);
        this.events = events;
        ButterKnife.bind(this, itemView);
    }

    @OnClick(R.id.add_player) void onAddPlayerClicked() {
        events.onAddButtonClick(getAdapterPosition());
    }

    interface CourtViewHolderEvents {
        void onAddButtonClick(int position);
    }
}
