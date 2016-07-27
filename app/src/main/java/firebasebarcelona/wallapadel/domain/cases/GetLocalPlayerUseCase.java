package firebasebarcelona.wallapadel.domain.cases;

import javax.inject.Inject;

import firebasebarcelona.wallapadel.app.PadelApplication;
import firebasebarcelona.wallapadel.domain.models.Player;

public class GetLocalPlayerUseCase extends AbstractUseCase {
  private Callback callback;

  @Inject
  public GetLocalPlayerUseCase() {
  }

  @Override
  public void run() {
    Player player = PadelApplication.getInstance().getLocalPlayer();
    if (player != null) {
      callback.onGetLocalPlayerSuccess(player);
    } else {
      callback.onGetLocalPlayerError();
    }

  }

  public void execute(Callback callback) {
    this.callback = callback;
    run();
  }

  public interface Callback {
    void onGetLocalPlayerSuccess(Player player);
    void onGetLocalPlayerError();
  }
}
