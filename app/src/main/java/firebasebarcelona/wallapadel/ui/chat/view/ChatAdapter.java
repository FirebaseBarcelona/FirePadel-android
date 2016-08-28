package firebasebarcelona.wallapadel.ui.chat.view;

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
import firebasebarcelona.wallapadel.R;
import firebasebarcelona.wallapadel.app.PadelApplication;
import firebasebarcelona.wallapadel.ui.models.MessageViewModel;
import java.util.ArrayList;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatViewHolder> {
  private final Context context;
  private List<MessageViewModel> messages;

  public ChatAdapter(Context context) {
    this.context = context;
    messages = new ArrayList<>();
  }

  public void updateMessages(List<MessageViewModel> messages) {
    this.messages = messages;
    notifyDataSetChanged();
  }

  @Override
  public ChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    View view = inflater.inflate(R.layout.chat_message_mine, parent, false);
    ChatViewHolder viewHolder = new ChatViewHolder(view);
    return viewHolder;
  }

  @Override
  public void onBindViewHolder(final ChatViewHolder holder, int position) {
    MessageViewModel message = messages.get(position);
    holder.message.setText(message.getMessage());
    renderAvatar(holder, message);
  }

  private void renderAvatar(final ChatViewHolder holder, MessageViewModel message) {
    Glide.with(PadelApplication.getInstance()).load(
    message.getAvatar()).asBitmap().centerCrop().into(
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
}
