package firebasebarcelona.firepadel.domain.cases;

import firebasebarcelona.firepadel.data.player.repository.PlayerRepository;
import firebasebarcelona.firepadel.domain.models.Player;
import java.lang.ref.WeakReference;
import java.util.List;
import javax.inject.Inject;

public class GetPlayersFromCourtUseCase extends AbstractUseCase {
  private final PlayerRepository playerRepository;
  private String courtId;
  private WeakReference<Callback> callbackRef;

  @Inject
  public GetPlayersFromCourtUseCase(PlayerRepository playerRepository) {
    this.playerRepository = playerRepository;
  }

  public void execute(String courtId, Callback callback) {
    this.courtId = courtId;
    this.callbackRef = new WeakReference<>(callback);
  }

  @Override
  protected void onRun() {
    playerRepository.getPlayers(courtId, new Callback() {
      @Override
      public void onPlayersReady(List<Player> players) {
        Callback callback = callbackRef.get();
        if (callback != null) {
          callback.onPlayersReady(players);
        }
      }
    });
  }

  public interface Callback {
    void onPlayersReady(List<Player> players);
  }
}
