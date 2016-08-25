package firebasebarcelona.wallapadel.data.courts.datasource;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import firebasebarcelona.wallapadel.data.courts.repository.GetCourtCallback;
import firebasebarcelona.wallapadel.data.mappers.CourtsDataMapper;
import firebasebarcelona.wallapadel.data.mappers.CourtsFirebaseMapper;
import firebasebarcelona.wallapadel.data.mappers.PlayersDataMapper;
import firebasebarcelona.wallapadel.data.models.CourtData;
import firebasebarcelona.wallapadel.domain.cases.GetCourtsUseCase;
import firebasebarcelona.wallapadel.domain.models.Court;
import firebasebarcelona.wallapadel.domain.models.Player;
import java.util.List;
import javax.inject.Inject;

public class CourtCloudDataSource {
  private final FirebaseDatabase firebaseDatabase;
  private final CourtsFirebaseMapper firebaseMapper;
  private final CourtsDataMapper mapperData;
  private final PlayersDataMapper playersDataMapper;
  private static final String COURT_NODE = "/courts";

  @Inject
  public CourtCloudDataSource(FirebaseDatabase firebaseDatabase, CourtsFirebaseMapper firebaseMapper,
                             CourtsDataMapper mapperData, PlayersDataMapper playersDataMapper) {
    this.firebaseDatabase = firebaseDatabase;
    this.firebaseMapper = firebaseMapper;
    this.mapperData = mapperData;
    this.playersDataMapper = playersDataMapper;
  }

  public void addPlayertoCourt(String id, Player player, GetCourtCallback getCourtCallback) {
    DatabaseReference reference = firebaseDatabase.getReference(COURT_NODE);
    reference.child("court"+id).child("users").push().setValue(playersDataMapper.map(player));
    getCourt(id, getCourtCallback);
  }

  public void getCourt(String courtId, final GetCourtCallback callback) {
    Query query = firebaseDatabase.getReference(COURT_NODE).child("court"+courtId);
    query.addListenerForSingleValueEvent(new ValueEventListener() {
      @Override
      public void onDataChange(DataSnapshot snapshot) {
        CourtData courtData = firebaseMapper.mapCourt(snapshot);
        callback.onGetCourtSuccess(mapperData.map(courtData));
      }

      @Override
      public void onCancelled(DatabaseError error) {
      }
    });
  }

  public void getCourts(final GetCourtsUseCase.Callback callback) {
    Query query = firebaseDatabase.getReference(COURT_NODE);
    query.addListenerForSingleValueEvent(new ValueEventListener() {
      @Override
      public void onDataChange(DataSnapshot snapshot) {
        List<CourtData> courts = firebaseMapper.map(snapshot);
        callback.onGetCourtsSuccess(mapperData.map(courts));
      }

      @Override
      public void onCancelled(DatabaseError error) {
      }
    });
  }
}
