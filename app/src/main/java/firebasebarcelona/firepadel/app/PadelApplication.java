package firebasebarcelona.firepadel.app;

import android.app.Application;
import firebasebarcelona.firepadel.app.di.component.ApplicationComponent;
import firebasebarcelona.firepadel.app.di.component.DaggerApplicationComponent;
import firebasebarcelona.firepadel.app.di.module.ApplicationModule;
import firebasebarcelona.firepadel.domain.models.Player;

public class PadelApplication extends Application {
  private static PadelApplication instance;
  private Player localPlayer = null;
  private ApplicationComponent applicationComponent;

  @Override
  public void onCreate() {
    super.onCreate();
    instance = this;
    doInjection();
  }

  private void doInjection() {
    applicationComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule()).build();
  }

  public ApplicationComponent getApplicationComponent() {
    return applicationComponent;
  }

  public static PadelApplication getInstance() {
    return instance;
  }

  public Player getLocalPlayer() {
    return localPlayer;
  }

  public void setLocalPlayer(Player localPlayer) {
    this.localPlayer = localPlayer;
  }
}
