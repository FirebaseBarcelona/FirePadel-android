package firebasebarcelona.wallapadel.ui.chat.presentation;

import firebasebarcelona.wallapadel.domain.cases.GetChatMessagesByCourtIdUseCase;
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

  @Inject
  public ChatPresenter(ChatView view, GetChatMessagesByCourtIdUseCase getChatMessagesByCourtIdUseCase, MessagesViewModelMapper mapper) {
    this.view = view;
    this.getChatMessagesByCourtIdUseCase = getChatMessagesByCourtIdUseCase;
    this.mapper = mapper;
  }

  public void requestToChat(String courtId) {
    getChatMessagesByCourtIdUseCase.execute(courtId, new GetChatMessagesByCourtIdUseCase.OnMessagesReadyCallback() {
      @Override
      public void onMessageReady(List<Message> messages) {
        messages = invert(messages);
        view.updateMessages(mapper.map(messages));
      }
    });
  }

  private List<Message> invert(List<Message> messages) {
    List<Message> messagesToBeInverted = new ArrayList<>(messages);
    Collections.reverse(messagesToBeInverted);
    return messagesToBeInverted;
  }
}
