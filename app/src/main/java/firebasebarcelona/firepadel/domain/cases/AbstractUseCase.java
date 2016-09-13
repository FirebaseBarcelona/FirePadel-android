package firebasebarcelona.firepadel.domain.cases;

public abstract class AbstractUseCase implements Runnable {
  protected void launchOnMainThread(Runnable runnable) {
    runnable.run();
  }

  protected void launch() {
    run();
  }

  @Override
  public final void run() {
    onRun();
  }

  protected abstract void onRun();
}
