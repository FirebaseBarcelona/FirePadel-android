package firebasebarcelona.wallapadel.domain.cases;

import firebasebarcelona.wallapadel.data.padel.repository.PadelRepository;
import javax.inject.Inject;

public class GetNextDateForCourtBookingUseCase extends AbstractUseCase {
  private final PadelRepository padelRepository;
  private Callback callback;

  @Inject
  public GetNextDateForCourtBookingUseCase(PadelRepository padelRepository) {
    this.padelRepository = padelRepository;
  }

  public void execute(Callback callback) {
    this.callback = callback;
    launch();
  }

  @Override
  protected void onRun() {
    padelRepository.getNextDateForBookingCourts(new Callback() {
      @Override
      public void onNextDateReady(long timestamp) {
        callback.onNextDateReady(timestamp);
      }
    });
  }

  public interface Callback {
    void onNextDateReady(long timestamp);
  }
}
