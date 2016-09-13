package firebasebarcelona.firepadel.domain.cases;

import firebasebarcelona.firepadel.app.rx.AbsSubscriber;
import firebasebarcelona.firepadel.data.courts.repository.CourtRepository;
import firebasebarcelona.firepadel.domain.models.Court;
import java.util.List;
import javax.inject.Inject;
import rx.Observable;

public class SubscribeToCourtsUseCase extends AbstractSubscriberUseCase {
  private final CourtRepository courtRepository;

  @Inject
  public SubscribeToCourtsUseCase(CourtRepository courtRepository) {
    this.courtRepository = courtRepository;
  }

  public void execute(AbsSubscriber<List<Court>> subscriber) {
    super.subscribe(subscriber);
  }

  @Override
  protected Observable getObservable() {
    return courtRepository.subscribeToCourts();
  }
}
