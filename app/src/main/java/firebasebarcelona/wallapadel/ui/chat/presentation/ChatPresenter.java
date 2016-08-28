package firebasebarcelona.wallapadel.ui.chat.presentation;

import firebasebarcelona.wallapadel.domain.cases.GetChatMessagesByCourtIdUseCase;
import firebasebarcelona.wallapadel.domain.cases.SendMessageUseCase;
import firebasebarcelona.wallapadel.domain.models.Message;
import firebasebarcelona.wallapadel.ui.models.MessagesViewModelMapper;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;

public class ChatPresenter {
  private final ChatView view;
  private final GetChatMessagesByCourtIdUseCase getChatMessagesByCourtIdUseCase;
  private final MessagesViewModelMapper mapper;

  private GetChatMessagesByCourtIdUseCase.OnMessagesReadyCallback callback;
  private String courtId;
  private SendMessageUseCase sendMessageUseCase;

  @Inject
  public ChatPresenter(ChatView view, GetChatMessagesByCourtIdUseCase getChatMessagesByCourtIdUseCase,
                      MessagesViewModelMapper mapper, SendMessageUseCase sendMessageUseCase) {
    this.view = view;
    this.getChatMessagesByCourtIdUseCase = getChatMessagesByCourtIdUseCase;
    this.mapper = mapper;
    this.sendMessageUseCase = sendMessageUseCase;
  }

  public void requestToChat(String courtId) {
    if (callback == null) {
      callback = new GetChatMessagesByCourtIdUseCase.OnMessagesReadyCallback() {
        @Override
        public void onMessageReady(List<Message> messages) {
          messages = invert(messages);
          view.updateMessages(mapper.map(messages));
        }
      };
      this.courtId = courtId;
      getChatMessagesByCourtIdUseCase.execute(courtId, callback);
    }
  }

  private List<Message> invert(List<Message> messages) {
    List<Message> messagesToBeInverted = new ArrayList<>(messages);
    Collections.reverse(messagesToBeInverted);
    return messagesToBeInverted;
  }

  public void onSendMessageClick(String message) {
    sendMessageUseCase.execute(courtId, message);
    view.clearMessageToBeSend();
  }
}
