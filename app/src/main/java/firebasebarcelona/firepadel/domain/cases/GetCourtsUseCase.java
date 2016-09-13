package firebasebarcelona.firepadel.domain.cases;

import firebasebarcelona.firepadel.data.courts.repository.CourtRepository;
import firebasebarcelona.firepadel.domain.cases.callbacks.OnGetCourtsCallback;
import firebasebarcelona.firepadel.domain.models.Court;
import java.lang.ref.WeakReference;
import java.util.List;
import javax.inject.Inject;

public class GetCourtsUseCase extends AbstractUseCase {
  private final CourtRepository courtRepository;
  private WeakReference<OnGetCourtsCallback> callbackReference;

  @Inject
  public GetCourtsUseCase(CourtRepository courtRepository) {
    this.courtRepository = courtRepository;
  }

  public void execute(OnGetCourtsCallback callback) {
    this.callbackReference = new WeakReference<>(callback);
    run();
  }

  @Override
  protected void onRun() {
    courtRepository.getCourts(new OnGetCourtsCallback() {
      @Override
      public void onGetCourtsSuccess(List<Court> courts) {
        OnGetCourtsCallback callback = GetCourtsUseCase.this.callbackReference.get();
        if (callback != null) {
          callback.onGetCourtsSuccess(courts);
        }
      }
    });
  }
}
