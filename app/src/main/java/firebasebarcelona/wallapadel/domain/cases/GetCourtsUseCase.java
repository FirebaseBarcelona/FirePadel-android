package firebasebarcelona.wallapadel.domain.cases;

import firebasebarcelona.wallapadel.domain.models.Court;
import java.util.List;
import javax.inject.Inject;

public class GetCourtsUseCase extends AbstractUseCase {
  private Callback callback;

  @Inject
  public GetCourtsUseCase() {
  }

  public void execute(Callback callback) {
    this.callback = callback;
    run();
  }

  @Override
  public void run() {
    //TODO mock list and call callback
  }

  interface Callback {
    void onGetCourtsSuccess(List<Court> courts);
  }
}
