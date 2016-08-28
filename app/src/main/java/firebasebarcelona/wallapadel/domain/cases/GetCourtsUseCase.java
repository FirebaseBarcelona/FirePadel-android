package firebasebarcelona.wallapadel.domain.cases;

import firebasebarcelona.wallapadel.data.courts.repository.CourtRepository;
import firebasebarcelona.wallapadel.domain.models.Court;
import java.lang.ref.WeakReference;
import java.util.List;
import javax.inject.Inject;

public class GetCourtsUseCase extends AbstractUseCase {
  private final CourtRepository courtRepository;
  private WeakReference<Callback> callbackReference;

  @Inject
  public GetCourtsUseCase(CourtRepository courtRepository) {
    this.courtRepository = courtRepository;
  }

  public void execute(Callback callback) {
    this.callbackReference = new WeakReference<>(callback);
    run();
  }

  @Override
  protected void onRun() {
    courtRepository.getCourts(new Callback() {
      @Override
      public void onGetCourtsSuccess(List<Court> courts) {
        Callback callback = GetCourtsUseCase.this.callbackReference.get();
        if (callback != null) {
          callback.onGetCourtsSuccess(courts);
        }
      }
    });
  }

  public interface Callback {
    void onGetCourtsSuccess(List<Court> courts);
  }
}
