package firebasebarcelona.wallapadel.domain.cases;

import firebasebarcelona.wallapadel.data.courts.repository.CourtRepository;
import firebasebarcelona.wallapadel.domain.cases.callbacks.GetCourtCallback;
import firebasebarcelona.wallapadel.domain.models.Court;
import firebasebarcelona.wallapadel.domain.models.Player;
import javax.inject.Inject;

public class AddPlayerToCourtUseCase extends AbstractUseCase {
  private final CourtRepository courtRepository;
  private String courtId;
  private Player player;
  private Callback callback;

  @Inject
  public AddPlayerToCourtUseCase(CourtRepository courtRepository) {
    this.courtRepository = courtRepository;
  }

  public void execute(String courtId, Player player, Callback callback) {
    this.courtId = courtId;
    this.player = player;
    this.callback = callback;
    run();
  }

  @Override
  public void run() {
    courtRepository.addPlayerToCourt(courtId, player, new GetCourtCallback() {
      @Override
      public void onGetCourtSuccess(final Court court) {
        launchOnMainThread(new Runnable() {
          @Override
          public void run() {
            callback.onAddPlayerToCourtSuccess(court);
          }
        });
      }
    });
  }

  public interface Callback {
    void onAddPlayerToCourtSuccess(Court court);
  }
}
