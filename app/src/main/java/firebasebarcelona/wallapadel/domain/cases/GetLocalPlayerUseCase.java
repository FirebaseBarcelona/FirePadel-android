package firebasebarcelona.wallapadel.domain.cases;

import javax.inject.Inject;

import firebasebarcelona.wallapadel.domain.models.Player;

public class GetLocalPlayerUseCase extends AbstractUseCase {
  private Callback callback;

  @Inject
  public GetLocalPlayerUseCase() {
  }

  @Override
  public void run() {
    //TODO
  }

  public void execute(Callback callback) {
    this.callback = callback;
    run();
  }

  interface Callback {
    void onGetLocalPlayerSuccess(Player player);
  }
}
