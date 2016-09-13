package firebasebarcelona.firepadel.data.chat.repository;

import firebasebarcelona.firepadel.data.chat.datasource.ChatCloudDataSource;
import firebasebarcelona.firepadel.data.chat.repository.callbacks.OnMessagesDataReadyCallback;
import firebasebarcelona.firepadel.data.mappers.ChatDataMapper;
import firebasebarcelona.firepadel.data.models.MessageData;
import firebasebarcelona.firepadel.domain.cases.GetChatMessagesByCourtIdUseCase;
import firebasebarcelona.firepadel.domain.models.Message;
import java.util.List;
import javax.inject.Inject;
import rx.Observable;
import rx.functions.Func1;

public class ChatRepository {
  private final ChatCloudDataSource chatCloudDataSource;
  private final ChatDataMapper mapper;

  @Inject
  public ChatRepository(ChatCloudDataSource chatCloudDataSource, ChatDataMapper mapper) {
    this.chatCloudDataSource = chatCloudDataSource;
    this.mapper = mapper;
  }

  public void getMessages(String courtId, final GetChatMessagesByCourtIdUseCase.OnMessagesReadyCallback callback) {
    chatCloudDataSource.getChatMessages(courtId, new OnMessagesDataReadyCallback() {
      @Override
      public void onMessagesReady(List<MessageData> messages) {
        callback.onMessageReady(mapper.map(messages));
      }
    });
  }

  public void sendMessage(String courtId, Message message) {
    chatCloudDataSource.sendMessage(courtId, mapper.map(message));
  }

  public Observable<List<Message>> subscribeToChat(String courtId) {
    return chatCloudDataSource.subscribeToChat(courtId).map(
    new Func1<List<MessageData>, List<Message>>() {
      @Override
      public List<Message> call(List<MessageData> messageDatas) {
        return mapper.map(messageDatas);
      }
    });
  }
}
