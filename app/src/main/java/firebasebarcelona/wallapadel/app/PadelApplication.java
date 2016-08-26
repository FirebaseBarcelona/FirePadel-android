package firebasebarcelona.wallapadel.app;

import android.app.Application;
import android.support.annotation.Nullable;
import firebasebarcelona.wallapadel.app.di.component.ApplicationComponent;
import firebasebarcelona.wallapadel.app.di.component.DaggerApplicationComponent;
import firebasebarcelona.wallapadel.app.di.module.ApplicationModule;
import firebasebarcelona.wallapadel.domain.models.Court;
import firebasebarcelona.wallapadel.domain.models.Player;
import java.util.ArrayList;
import java.util.List;

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
