package firebasebarcelona.wallapadel.data.courts.datasource;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import firebasebarcelona.wallapadel.data.courts.repository.callbacks.GetCourtsDataCallback;
import firebasebarcelona.wallapadel.data.courts.repository.callbacks.OnCourtModifiedCallback;
import firebasebarcelona.wallapadel.data.mappers.CourtsFirebaseMapper;
import firebasebarcelona.wallapadel.data.models.CourtData;
import firebasebarcelona.wallapadel.data.models.PlayerData;
import java.util.List;
import javax.inject.Inject;

public class CourtCloudDataSource {
  private static final String COURT_NODE = "/courts";
  private final CourtsFirebaseMapper firebaseMapper;
  private final DatabaseReference reference;

  @Inject
  public CourtCloudDataSource(FirebaseDatabase firebaseDatabase, CourtsFirebaseMapper firebaseMapper) {
    this.firebaseMapper = firebaseMapper;
    reference = firebaseDatabase.getReference(COURT_NODE);
  }

  public void removePlayerFromCourt(final String courtId, final PlayerData playerData) {
    reference.child(firebaseMapper.getCourtId(courtId)).child("users").child(playerData.getUuid()).removeValue(
    new DatabaseReference.CompletionListener() {
      @Override
      public void onComplete(DatabaseError error, DatabaseReference reference) {
      }
    });
  }

  public void addPlayerToCourt(final String courtId, PlayerData playerData) {
    reference.child(firebaseMapper.getCourtId(courtId)).child("users").child(playerData.getUuid()).setValue(playerData,
    new DatabaseReference.CompletionListener() {
      @Override
      public void onComplete(DatabaseError error, DatabaseReference reference) {
      }
    });
  }

  public void getCourt(String courtId, final OnCourtModifiedCallback callback) {
    Query query = reference.child(firebaseMapper.getCourtId(courtId));
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

  public void getCourts(final GetCourtsDataCallback callback) {
    Query query = reference;
    query.addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(DataSnapshot snapshot) {
        List<CourtData> courts = firebaseMapper.map(snapshot);
        callback.onGetCourtsSuccess(courts);
      }

      @Override
      public void onCancelled(DatabaseError error) {
      }
    });
  }
}
