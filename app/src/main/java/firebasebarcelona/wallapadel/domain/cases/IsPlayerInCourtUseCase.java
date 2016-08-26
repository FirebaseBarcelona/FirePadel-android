package firebasebarcelona.wallapadel.domain.cases;

import firebasebarcelona.wallapadel.data.courts.repository.CourtRepository;
import firebasebarcelona.wallapadel.domain.cases.callbacks.GetCourtCallback;
import firebasebarcelona.wallapadel.domain.cases.callbacks.IsPlayerInCourtCallback;
import firebasebarcelona.wallapadel.domain.models.Court;
import firebasebarcelona.wallapadel.domain.models.Player;
import java.util.List;
import javax.inject.Inject;

public class IsPlayerInCourtUseCase extends AbstractUseCase {
  private final CourtRepository courtRepository;
  private String courtId;
  private Player player;
  private IsPlayerInCourtCallback callback;

  @Inject
  public IsPlayerInCourtUseCase(CourtRepository courtRepository) {
    this.courtRepository = courtRepository;
  }

  @Override
  public void run() {
    courtRepository.getCourt(courtId, new GetCourtCallback() {
      @Override
      public void onGetCourtSuccess(Court court) {
        List<Player> courtPlayers = court.getPlayers();
        checkIfPlayerInCourt(player, courtPlayers);
      }
    });
  }

  private void checkIfPlayerInCourt(Player player, List<Player> players) {
    boolean isPlayerInCourt = false;
    for (Player courtPlayer : players) {
      if (courtPlayer.getId().equals(player.getId())) {
        callback.playerInCourt(player);
        isPlayerInCourt = true;
        break;
      }
    }
    if (!isPlayerInCourt) {
      callback.playerNotInCourt(player);
    }
  }

  public void execute(String courtId, Player player, IsPlayerInCourtCallback callback) {
    this.courtId = courtId;
    this.player = player;
    this.callback = callback;
    run();
  }
}
