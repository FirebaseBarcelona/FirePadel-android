package firebasebarcelona.wallapadel.data.chat.repository.callbacks;

import firebasebarcelona.wallapadel.data.models.MessageData;
import java.util.List;

public interface OnMessagesDataReadyCallback {
  void onMessagesReady(List<MessageData> messages);
}
