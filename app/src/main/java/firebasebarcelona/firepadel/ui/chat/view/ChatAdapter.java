package firebasebarcelona.firepadel.ui.chat.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import firebasebarcelona.firepadel.R;
import firebasebarcelona.firepadel.app.PadelApplication;
import firebasebarcelona.firepadel.ui.models.MessageViewModel;
import firebasebarcelona.firepadel.ui.models.PlayerViewModel;
import java.util.ArrayList;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatViewHolder> {
  private static final int OTHER_MESSAGE = 1;
  private static final int MY_MESSAGE = 0;
  private final Context context;
  private List<MessageViewModel> messages;
  private PlayerViewModel localPlayer;

  public ChatAdapter(Context context, PlayerViewModel player) {
    this.context = context;
    messages = new ArrayList<>();
    localPlayer = player;
  }

  public void updateMessages(List<MessageViewModel> messages) {
    this.messages = messages;
    notifyDataSetChanged();
  }

  @Override
  public ChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    View view = inflater.inflate(getLayout(viewType), parent, false);
    ChatViewHolder viewHolder = new ChatViewHolder(view);
    return viewHolder;
  }

  private int getLayout(int viewType) {
    if (viewType == MY_MESSAGE) {
      return R.layout.chat_message_mine;
    } else {
      return R.layout.chat_message_other;
    }
  }

  @Override
  public int getItemViewType(int position) {
    MessageViewModel messageViewModel = messages.get(position);
    if (messageViewModel.getUserUUID().equals(localPlayer.getId())) {
      return MY_MESSAGE;
    } else {
      return OTHER_MESSAGE;
    }
  }

  @Override
  public void onBindViewHolder(final ChatViewHolder holder, int position) {
    MessageViewModel message = messages.get(position);
    holder.message.setText(message.getMessage());
    renderAvatar(holder, message);
  }

  private void renderAvatar(final ChatViewHolder holder, MessageViewModel message) {
    Glide.with(PadelApplication.getInstance()).load(message.getAvatar()).asBitmap().centerCrop().into(
    new BitmapImageViewTarget(holder.avatar) {
      @Override
      protected void setResource(Bitmap resource) {
        RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), resource);
        circularBitmapDrawable.setCircular(true);
        holder.avatar.setImageDrawable(circularBitmapDrawable);
      }
    });
  }

  @Override
  public int getItemCount() {
    return messages.size();
  }

  public void setLocalPlayer(PlayerViewModel localPlayer) {
    this.localPlayer = localPlayer;
  }
}
