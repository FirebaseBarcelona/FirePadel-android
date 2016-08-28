package firebasebarcelona.wallapadel.domain.cases;

import firebasebarcelona.wallapadel.app.rx.AbsSubscriber;
import firebasebarcelona.wallapadel.data.chat.repository.ChatRepository;
import firebasebarcelona.wallapadel.domain.models.Message;
import java.lang.ref.WeakReference;
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
