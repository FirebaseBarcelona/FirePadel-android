package firebasebarcelona.wallapadel.data.mappers;

import com.google.firebase.database.DataSnapshot;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class PadelDataFirebaseMapper {
  @Inject
  public PadelDataFirebaseMapper() {
  }

  public long mapNextDateForBooking(DataSnapshot snapshot) {
    return snapshot.getValue(Long.class);
  }
}
