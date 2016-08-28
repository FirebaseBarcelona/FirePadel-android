package firebasebarcelona.wallapadel.ui.models;

import firebasebarcelona.wallapadel.domain.models.Message;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class MessagesViewModelMapper {
  @Inject
  public MessagesViewModelMapper() {
  }

  public List<MessageViewModel> map(List<Message> messages) {
    List<MessageViewModel> result = new ArrayList<>();
    for (Message message : messages) {
      result.add(map(message));
    }
    return result;
  }

  private MessageViewModel map(Message message) {
    return new MessageViewModel(message.getMessage(), message.getUserUUID(), message.getAvatar());
  }
}
