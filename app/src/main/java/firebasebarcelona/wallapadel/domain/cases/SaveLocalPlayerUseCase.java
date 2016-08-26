package firebasebarcelona.wallapadel.domain.cases;

import firebasebarcelona.wallapadel.data.player.repository.PlayerRepository;
import firebasebarcelona.wallapadel.domain.models.Player;
import javax.inject.Inject;

public class SaveLocalPlayerUseCase extends AbstractUseCase {
  private final PlayerRepository playerRepository;
  private Player player;
  private Callback callback;

  @Inject
  public SaveLocalPlayerUseCase(PlayerRepository playerRepository) {
    this.playerRepository = playerRepository;
  }

  @Override
  public void run() {
    playerRepository.storeMyPlayer(player);
    launchOnMainThread(new Runnable() {
      @Override
      public void run() {
        callback.onSaveLocalPlayerSuccess(player);
      }
    });
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
