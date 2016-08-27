package firebasebarcelona.wallapadel.data.chat.repository;

import firebasebarcelona.wallapadel.data.chat.datasource.ChatCloudDataSource;
import firebasebarcelona.wallapadel.data.chat.repository.callbacks.OnMessagesDataReadyCallback;
import firebasebarcelona.wallapadel.data.mappers.ChatDataMapper;
import firebasebarcelona.wallapadel.data.models.MessageData;
import firebasebarcelona.wallapadel.domain.cases.GetChatMessagesByCourtIdUseCase;
import java.util.List;
import javax.inject.Inject;

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
}
