package firebasebarcelona.firepadel.domain.cases;

import firebasebarcelona.firepadel.app.rx.AbsSubscriber;
import firebasebarcelona.firepadel.data.chat.repository.ChatRepository;
import firebasebarcelona.firepadel.domain.models.Message;
import java.util.List;
import javax.inject.Inject;
import rx.Observable;

public class SubscribeToChatByCourtIdUseCase extends AbstractSubscriberUseCase {
  private final ChatRepository chatRepository;
  private String courtId;

  @Inject
  public SubscribeToChatByCourtIdUseCase(ChatRepository chatRepository) {
    this.chatRepository = chatRepository;
  }

  public void execute(String courtId, AbsSubscriber<List<Message>> subscriber) {
    this.courtId = courtId;
    super.subscribe(subscriber);
  }

  @Override
  protected Observable getObservable() {
    return chatRepository.subscribeToChat(courtId);
  }
}
