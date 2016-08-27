package firebasebarcelona.wallapadel.ui.chat.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import firebasebarcelona.wallapadel.R;
import firebasebarcelona.wallapadel.ui.models.MessageViewModel;
import java.util.ArrayList;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatViewHolder> {
  private List<MessageViewModel> messages;

  public ChatAdapter() {
    messages = new ArrayList<>();
  }

  public void updateMessages(List<MessageViewModel> messages) {
    this.messages = messages;
    notifyDataSetChanged();
  }

  @Override
  public ChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    View view = inflater.inflate(R.layout.chat_message, parent, false);
    ChatViewHolder viewHolder = new ChatViewHolder(view);
    return viewHolder;
  }

  @Override
  public void onBindViewHolder(ChatViewHolder holder, int position) {
    MessageViewModel message = messages.get(position);
    holder.message.setText(message.getMessage());
  }

  @Override
  public int getItemCount() {
    return messages.size();
  }
}
