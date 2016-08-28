package firebasebarcelona.wallapadel.ui.chat.presentation;

import firebasebarcelona.wallapadel.domain.cases.GetChatMessagesByCourtIdUseCase;
import firebasebarcelona.wallapadel.domain.cases.GetLocalPlayerUseCase;
import firebasebarcelona.wallapadel.domain.cases.SendMessageUseCase;
import firebasebarcelona.wallapadel.domain.models.Message;
import firebasebarcelona.wallapadel.domain.models.Player;
import firebasebarcelona.wallapadel.ui.models.MessagesViewModelMapper;
import firebasebarcelona.wallapadel.ui.models.PlayerViewModelMapper;
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
  private final GetLocalPlayerUseCase getLocalPlayerUseCase;
  private PlayerViewModelMapper playerMapper;
  private Player myPlayer;

  @Inject
  public ChatPresenter(ChatView view, GetChatMessagesByCourtIdUseCase getChatMessagesByCourtIdUseCase,
                      MessagesViewModelMapper mapper, SendMessageUseCase sendMessageUseCase, PlayerViewModelMapper playerMapper,
                      GetLocalPlayerUseCase getLocalPlayerUseCase) {
    this.view = view;
    this.getChatMessagesByCourtIdUseCase = getChatMessagesByCourtIdUseCase;
    this.mapper = mapper;
    this.sendMessageUseCase = sendMessageUseCase;
    this.getLocalPlayerUseCase = getLocalPlayerUseCase;
    this.playerMapper = playerMapper;
  }

  public void requestToChat(String courtId) {
    if (callback == null) {
      fetchLocalPlayer();
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

  private void fetchLocalPlayer() {
    getLocalPlayerUseCase.execute(new GetLocalPlayerUseCase.Callback() {
      @Override
      public void onGetLocalPlayerSuccess(Player player) {
        myPlayer = player;
        view.renderList(playerMapper.map(player));
      }

      @Override
      public void onGetLocalPlayerError() {
      }
    });
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
