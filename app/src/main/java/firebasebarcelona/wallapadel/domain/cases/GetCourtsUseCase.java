package firebasebarcelona.wallapadel.domain.cases;

import android.support.annotation.NonNull;

import firebasebarcelona.wallapadel.app.PadelApplication;
import firebasebarcelona.wallapadel.domain.models.Court;
import firebasebarcelona.wallapadel.domain.models.Player;

import java.util.ArrayList;
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
    List<Court> courts = PadelApplication.getInstance().getCourts();
    callback.onGetCourtsSuccess(courts);
  }

  public interface Callback {
    void onGetCourtsSuccess(List<Court> courts);
  }
}
