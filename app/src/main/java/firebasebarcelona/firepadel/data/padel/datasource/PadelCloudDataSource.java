package firebasebarcelona.firepadel.data.padel.datasource;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import firebasebarcelona.firepadel.data.mappers.PadelDataFirebaseMapper;
import firebasebarcelona.firepadel.data.padel.repository.callbacks.OnGetNextDateForBookingData;
import javax.inject.Inject;

public class PadelCloudDataSource {
  private final DatabaseReference database;
  private final PadelDataFirebaseMapper mapper;

  @Inject
  public PadelCloudDataSource(FirebaseDatabase database, PadelDataFirebaseMapper mapper) {
    this.mapper = mapper;
    this.database = database.getReference();
  }

  public void getNextDateForBookingCourts(final OnGetNextDateForBookingData callback) {
    database.child("datebookings").addListenerForSingleValueEvent(new ValueEventListener() {
      @Override
      public void onDataChange(DataSnapshot snapshot) {
        callback.onDateReady(mapper.mapNextDateForBooking(snapshot));
      }

      @Override
      public void onCancelled(DatabaseError error) {
      }
    });
  }
}
