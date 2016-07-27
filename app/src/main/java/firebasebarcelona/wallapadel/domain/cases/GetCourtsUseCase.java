package firebasebarcelona.wallapadel.domain.cases;

import android.support.annotation.NonNull;

import firebasebarcelona.wallapadel.domain.models.Court;
import firebasebarcelona.wallapadel.domain.models.Player;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class GetCourtsUseCase extends AbstractUseCase {
  private Callback callback;

  @Inject
  public GetCourtsUseCase() {
  }

  public void execute(Callback callback) {
    this.callback = callback;
    run();
  }

  @Override
  public void run() {
    List<Court> courts = generateFakeCourts();
    callback.onGetCourtsSuccess(courts);
  }

  @NonNull
  private List<Court> generateFakeCourts() {
    Player p1c1 = new Player.Builder().id("p1c1").name("Quique Álvarez").photoUrl("https://plus.google.com/u/0/photos/105371334854876774516/albums/profile/6250822619252484178").build();
    List<Player> p1 = new ArrayList<>();
    p1.add(p1c1);
    Court c1 = new Court.Builder().id("c1").players(p1).build();
    Player p1c2 = new Player.Builder().id("p1c2").name("Quique Álvarez").photoUrl("https://plus.google.com/u/0/photos/105371334854876774516/albums/profile/6250822619252484178").build();
    List<Player> p2 = new ArrayList<>();
    p2.add(p1c2);
    Court c2 = new Court.Builder().id("c2").players(p2).build();
    List<Court> courts = new ArrayList<>();
    courts.add(c1);
    courts.add(c2);
    return courts;
  }

  public interface Callback {
    void onGetCourtsSuccess(List<Court> courts);
  }
}
