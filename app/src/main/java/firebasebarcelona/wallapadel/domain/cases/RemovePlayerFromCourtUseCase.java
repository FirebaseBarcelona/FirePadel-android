package firebasebarcelona.wallapadel.domain.cases;

import firebasebarcelona.wallapadel.domain.models.Court;
import firebasebarcelona.wallapadel.domain.models.Player;
import javax.inject.Inject;

public class RemovePlayerFromCourtUseCase extends AbstractUseCase {
  private String courtId;
  private Player player;
  private Callback callback;

  @Inject
  public RemovePlayerFromCourtUseCase() {
  }

  void execute(String courtId, Player player, Callback callback) {
    this.courtId = courtId;
    this.player = player;
    this.callback = callback;
    run();
  }

  @Override
  public void run() {
    //TODO
  }

  interface Callback {
    void onRemovePlayerFromCourtSuccess(Court court);
  }
}
