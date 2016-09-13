package firebasebarcelona.firepadel.ui.chat.presentation;

import firebasebarcelona.firepadel.ui.models.MessageViewModel;
import firebasebarcelona.firepadel.ui.models.PlayerViewModel;
import java.util.List;

public interface ChatView {
  void updateMessages(List<MessageViewModel> messages);
  void clearMessageToBeSend();
  void renderList(PlayerViewModel map);
}
