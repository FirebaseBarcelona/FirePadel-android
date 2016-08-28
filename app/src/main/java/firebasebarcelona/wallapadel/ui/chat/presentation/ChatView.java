package firebasebarcelona.wallapadel.ui.chat.presentation;

import firebasebarcelona.wallapadel.ui.models.MessageViewModel;
import firebasebarcelona.wallapadel.ui.models.PlayerViewModel;
import java.util.List;

public interface ChatView {
  void updateMessages(List<MessageViewModel> messages);
  void clearMessageToBeSend();
  void renderList(PlayerViewModel map);
}
