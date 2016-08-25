package firebasebarcelona.wallapadel.data.mappers;

import com.google.firebase.database.DataSnapshot;
import firebasebarcelona.wallapadel.data.models.CourtData;
import firebasebarcelona.wallapadel.data.models.PlayerData;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton public class CourtsFirebaseMapper {

  @Inject
  public CourtsFirebaseMapper() {
  }

  public List<CourtData> map(DataSnapshot snapshot) {
    List<CourtData> courts = new ArrayList<>();
    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
      CourtData courtData = new CourtData();
      List<PlayerData> players = new ArrayList<>();
      for (DataSnapshot playersSnapshot : dataSnapshot.child("users").getChildren()) {
        PlayerData playerData = playersSnapshot.getValue(PlayerData.class);
        players.add(playerData);
      }
      courtData.setId(String.valueOf(dataSnapshot.child("id").getValue()));
      courtData.setPlayers(players);
      courts.add(courtData);
    }
    return courts;
  }
}
