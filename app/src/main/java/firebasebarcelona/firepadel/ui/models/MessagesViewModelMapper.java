package firebasebarcelona.firepadel.ui.models;

import firebasebarcelona.firepadel.domain.models.Message;
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
