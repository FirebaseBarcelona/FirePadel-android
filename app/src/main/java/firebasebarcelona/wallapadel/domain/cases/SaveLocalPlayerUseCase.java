package firebasebarcelona.wallapadel.domain.cases;

import firebasebarcelona.wallapadel.app.PadelApplication;
import firebasebarcelona.wallapadel.domain.models.Player;
import javax.inject.Inject;

public class SaveLocalPlayerUseCase extends AbstractUseCase {
  private Player player;
  private Callback callback;

  @Inject
  public SaveLocalPlayerUseCase() {
  }

  @Override
  public void run() {
    PadelApplication.getInstance().setLocalPlayer(player);
  }

  public void execute(Player player, Callback callback) {
    this.player = player;
    this.callback = callback;
    run();
  }

  public interface Callback {
    void onSaveLocalPlayerSuccess(Player player);
  }
}
