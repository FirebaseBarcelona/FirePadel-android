package firebasebarcelona.firepadel.data.chat.repository.callbacks;

import firebasebarcelona.firepadel.data.models.MessageData;
import java.util.List;

public interface OnMessagesDataReadyCallback {
  void onMessagesReady(List<MessageData> messages);
}
