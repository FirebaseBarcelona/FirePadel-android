package firebasebarcelona.firepadel.data.mappers;

import firebasebarcelona.firepadel.data.models.MessageData;
import firebasebarcelona.firepadel.data.player.repository.PlayerRepository;
import firebasebarcelona.firepadel.domain.models.Message;
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
    new MessageData.Builder().message(message.getMessage().trim()).uuid(message.getUserUUID()).avatar(message.getAvatar()).name(
    message.getName()).build();
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
