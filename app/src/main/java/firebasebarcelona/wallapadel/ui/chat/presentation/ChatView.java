package firebasebarcelona.wallapadel.ui.chat.presentation;

import firebasebarcelona.wallapadel.ui.models.MessageViewModel;
import java.util.List;

public interface ChatView {
  void updateMessages(List<MessageViewModel> messages);
}
