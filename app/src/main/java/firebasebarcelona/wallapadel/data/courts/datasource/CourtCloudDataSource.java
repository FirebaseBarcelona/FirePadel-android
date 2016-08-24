package firebasebarcelona.wallapadel.data.courts.datasource;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import firebasebarcelona.wallapadel.data.mappers.CourtsFirebaseMapper;
import firebasebarcelona.wallapadel.domain.cases.GetCourtsUseCase;
import firebasebarcelona.wallapadel.domain.models.Court;
import firebasebarcelona.wallapadel.domain.models.Player;
import java.util.List;
import javax.inject.Inject;

public class CourtCloudDataSource {
  private final FirebaseDatabase firebaseDatabase;
  private final CourtsFirebaseMapper mapper;
  private static final String COURT_NODE = "/courts";

  @Inject
  public CourtCloudDataSource(FirebaseDatabase firebaseDatabase, CourtsFirebaseMapper mapper) {
    this.firebaseDatabase = firebaseDatabase;
    this.mapper = mapper;
  }

  public Court addPlayertoCourt(String id, Player player) {
    return null;
  }

  public void getCourts(final GetCourtsUseCase.Callback callback) {
    Query query = firebaseDatabase.getReference(COURT_NODE);
    query.addListenerForSingleValueEvent(new ValueEventListener() {
      @Override
      public void onDataChange(DataSnapshot snapshot) {
        List<Court> courts = mapper.map(snapshot);
        callback.onGetCourtsSuccess(courts);
      }

      @Override
      public void onCancelled(DatabaseError error) {
      }
    });
  }
}
