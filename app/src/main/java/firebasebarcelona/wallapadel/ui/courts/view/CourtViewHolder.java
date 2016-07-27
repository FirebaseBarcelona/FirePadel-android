package firebasebarcelona.wallapadel.ui.courts.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import firebasebarcelona.wallapadel.R;

public class CourtViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.players)
    LinearLayout players;

    public CourtViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
