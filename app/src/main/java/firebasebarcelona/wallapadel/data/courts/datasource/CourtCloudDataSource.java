package firebasebarcelona.wallapadel.data.courts.datasource;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import firebasebarcelona.wallapadel.data.courts.repository.callbacks.OnCourtModifiedCallback;
import firebasebarcelona.wallapadel.data.mappers.CourtsDataMapper;
import firebasebarcelona.wallapadel.data.mappers.CourtsFirebaseMapper;
import firebasebarcelona.wallapadel.data.mappers.PlayersDataMapper;
import firebasebarcelona.wallapadel.data.models.CourtData;
import firebasebarcelona.wallapadel.data.models.PlayerData;
import firebasebarcelona.wallapadel.domain.cases.GetCourtsUseCase;
import java.util.List;
import javax.inject.Inject;

public class CourtCloudDataSource {
  private final CourtsFirebaseMapper firebaseMapper;
  private final CourtsDataMapper mapperData;
  private final PlayersDataMapper playersDataMapper;
  private static final String COURT_NODE = "/courts";
  private final DatabaseReference reference;

  @Inject
  public CourtCloudDataSource(FirebaseDatabase firebaseDatabase, CourtsFirebaseMapper firebaseMapper, CourtsDataMapper mapperData,
                             PlayersDataMapper playersDataMapper) {
    this.firebaseMapper = firebaseMapper;
    this.mapperData = mapperData;
    this.playersDataMapper = playersDataMapper;
    reference = firebaseDatabase.getReference(COURT_NODE);
  }

  public void removePlayerFromCourt(final String courtId, final PlayerData playerData,
                                   final OnCourtModifiedCallback onCourtModifiedCallback) {
    reference.child("court" + courtId).child("users").child(playerData.getUuid()).removeValue(
    new DatabaseReference.CompletionListener() {
      @Override
      public void onComplete(DatabaseError error, DatabaseReference reference) {
        getCourt(courtId, onCourtModifiedCallback);
      }
    });
  }

  public void addPlayerToCourt(final String courtId, PlayerData playerData, final OnCourtModifiedCallback getCourtCallback) {
    reference.child("court" + courtId).child("users").child(playerData.getUuid()).setValue(playerData,
    new DatabaseReference.CompletionListener() {
      @Override
      public void onComplete(DatabaseError error, DatabaseReference reference) {
        getCourt(courtId, getCourtCallback);
      }
    });
  }

  public void getCourt(String courtId, final OnCourtModifiedCallback callback) {
    Query query = reference.child("court" + courtId);
    query.addListenerForSingleValueEvent(new ValueEventListener() {
      @Override
      public void onDataChange(DataSnapshot snapshot) {
        CourtData courtData = firebaseMapper.mapCourt(snapshot);
        callback.onGetCourtSuccess(courtData);
      }

      @Override
      public void onCancelled(DatabaseError error) {
      }
    });
  }

  public void getCourts(final GetCourtsUseCase.Callback callback) {
    Query query = reference;
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
