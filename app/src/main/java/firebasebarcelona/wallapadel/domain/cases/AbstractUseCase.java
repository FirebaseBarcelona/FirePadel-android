package firebasebarcelona.wallapadel.domain.cases;

public abstract class AbstractUseCase implements Runnable {
  protected void launchOnMainThread(Runnable runnable) {
    runnable.run();
  }

  protected void launch() {
    run();
  }
}
