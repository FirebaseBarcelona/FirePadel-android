package firebasebarcelona.firepadel.domain.cases;

import firebasebarcelona.firepadel.data.courts.repository.CourtRepository;
import firebasebarcelona.firepadel.domain.cases.callbacks.OnGetCourtsCallback;
import firebasebarcelona.firepadel.domain.models.Court;
import firebasebarcelona.firepadel.domain.models.Player;
import java.util.List;
import javax.inject.Inject;

public class IsPlayerInAnotherCourtUseCase extends AbstractUseCase {
  private final CourtRepository courtRepository;
  private String courtId;
  private Player player;
  private Callback callback;

  @Inject
  public IsPlayerInAnotherCourtUseCase(CourtRepository courtRepository) {
    this.courtRepository = courtRepository;
  }

  public void execute(String courtId, Player player, Callback callback) {
    this.courtId = courtId;
    this.player = player;
    this.callback = callback;
    launch();
  }

  @Override
  protected void onRun() {
    courtRepository.getCourts(new OnGetCourtsCallback() {
      @Override
      public void onGetCourtsSuccess(List<Court> courts) {
        boolean playerIsInCourt = false;
        for (Court court : courts) {
          if (!court.getId().equals(courtId)) {
            List<Player> players = court.getPlayers();
            for (Player courtPlayer : players) {
              if (courtPlayer.getId().equals(player.getId())) {
                playerIsInCourt = true;
                launchOnMainThread(new Runnable() {
                  @Override
                  public void run() {
                    callback.onPlayerIsInSomeCourt();
                  }
                });
                break;
              }
            }
          }
          if (playerIsInCourt) {
            break;
          }
        }
        if (!playerIsInCourt) {
          launchOnMainThread(new Runnable() {
            @Override
            public void run() {
              callback.onPlayerIsNotInAnyCourt();
            }
          });
        }
      }
    });
  }

  public interface Callback {
    void onPlayerIsInSomeCourt();
    void onPlayerIsNotInAnyCourt();
  }
}
