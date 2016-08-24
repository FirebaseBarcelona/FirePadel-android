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
  private List<Court> courts;
  private Player localPlayer = null;
  private ApplicationComponent applicationComponent;

  @Override
  public void onCreate() {
    super.onCreate();
    instance = this;
    doInjection();
    createFakeData();
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

  public List<Court> getCourts() {
    return courts;
  }

  public Player getLocalPlayer() {
    return localPlayer;
  }

  private void createFakeData() {
    Player p1c1 = new Player.Builder().id("p1c1").name("Quique Álvarez").photoUrl(
    "https://lh3.googleusercontent.com/ZasSTHvPHAskUf-Na2X8-F3oBRD54rdogOUcp-ZCZWJy9186dk7EQx2oEYDGjsYDaDQrk1DQ").build();
    List<Player> p1 = new ArrayList<>();
    p1.add(p1c1);
    Court c1 = new Court.Builder().id("c1").players(p1).build();
    Player p1c2 = new Player.Builder().id("p1c2").name("Quique Álvarez").photoUrl(
    "https://lh3.googleusercontent.com/ZasSTHvPHAskUf-Na2X8-F3oBRD54rdogOUcp-ZCZWJy9186dk7EQx2oEYDGjsYDaDQrk1DQ").build();
    List<Player> p2 = new ArrayList<>();
    p2.add(p1c2);
    Court c2 = new Court.Builder().id("c2").players(p2).build();
    courts = new ArrayList<>();
    courts.add(c1);
    courts.add(c2);
  }

  public void setLocalPlayer(Player localPlayer) {
    this.localPlayer = localPlayer;
  }

  @Nullable
  public Court addLocalPlayerToCourt(String courtId, Player player) {
    Court result = null;
    for (Court court : courts) {
      if (courtId.equals(court.getId()) && court.getPlayers().size() < 4) {
        court.getPlayers().add(player);
        result = court;
      }
    }
    return result;
  }
}
