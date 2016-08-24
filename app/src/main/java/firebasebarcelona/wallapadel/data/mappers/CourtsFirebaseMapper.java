package firebasebarcelona.wallapadel.data.mappers;

import android.util.Log;
import com.google.firebase.database.DataSnapshot;
import firebasebarcelona.wallapadel.app.PadelApplication;
import firebasebarcelona.wallapadel.domain.models.Court;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton public class CourtsFirebaseMapper {
  
  @Inject
  public CourtsFirebaseMapper() {
  }

  public List<Court> map(DataSnapshot snapshot) {
    List<Court> courts = new ArrayList<>();
    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
      Log.d("Snapshot", dataSnapshot.toString());
    }
    return PadelApplication.getInstance().getCourts();
  }
}
