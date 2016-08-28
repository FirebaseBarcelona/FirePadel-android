package firebasebarcelona.wallapadel.domain.cases;

import firebasebarcelona.wallapadel.data.courts.repository.CourtRepository;
import firebasebarcelona.wallapadel.domain.models.Court;
import firebasebarcelona.wallapadel.domain.models.Player;
import javax.inject.Inject;

public class RemovePlayerFromCourtUseCase extends AbstractUseCase {
  private final CourtRepository courtRepository;
  private String courtId;
  private Player player;
  private Callback callback;

  @Inject
  public RemovePlayerFromCourtUseCase(CourtRepository courtRepository) {
    this.courtRepository = courtRepository;
  }

  public void execute(String courtId, Player player, Callback callback) {
    this.courtId = courtId;
    this.player = player;
    this.callback = callback;
    run();
  }

  @Override
  protected void onRun() {
    courtRepository.removePlayerFromCourt(courtId, player);
  }

  public interface Callback {
    void onRemovePlayerFromCourtSuccess(Court court);
  }
}
