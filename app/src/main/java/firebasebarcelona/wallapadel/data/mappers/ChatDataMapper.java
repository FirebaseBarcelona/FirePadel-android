package firebasebarcelona.wallapadel.data.mappers;

import firebasebarcelona.wallapadel.data.models.MessageData;
import firebasebarcelona.wallapadel.data.player.repository.PlayerRepository;
import firebasebarcelona.wallapadel.domain.models.Message;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class ChatDataMapper {
  private final PlayerRepository playerRepository;

  @Inject
  public ChatDataMapper(PlayerRepository playerRepository) {
    this.playerRepository = playerRepository;
  }

  public MessageData map(Message message) {
    MessageData messageData =
    new MessageData.Builder().message(message.getMessage()).uuid(message.getUserUUID()).avatar(message.getAvatar()).build();
    return messageData;
  }

  public Message map(MessageData messageData) {
    Message message = new Message.Builder().message(messageData.getMessage()).userUUID(messageData.getUuid()).avatar(
    messageData.getAvatar()).build();
    return message;
  }

  public List<Message> map(List<MessageData> messages) {
    List<Message> messageList = new ArrayList<>();
    for (MessageData message : messages) {
      messageList.add(map(message));
    }
    return messageList;
  }
}
