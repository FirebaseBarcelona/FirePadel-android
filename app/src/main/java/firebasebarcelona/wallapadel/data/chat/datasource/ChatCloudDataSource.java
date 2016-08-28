package firebasebarcelona.wallapadel.data.chat.datasource;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import firebasebarcelona.wallapadel.data.chat.repository.callbacks.OnMessagesDataReadyCallback;
import firebasebarcelona.wallapadel.data.mappers.ChatMessagesFirebaseMapper;
import firebasebarcelona.wallapadel.data.mappers.CourtsFirebaseMapper;
import firebasebarcelona.wallapadel.data.models.MessageData;
import javax.inject.Inject;

public class ChatCloudDataSource {
  private static final String COURT_NODE = "/courts";
  private final DatabaseReference database;
  private final ChatMessagesFirebaseMapper mapper;
  private final CourtsFirebaseMapper firebaseMapper;

  @Inject
  public ChatCloudDataSource(FirebaseDatabase firebaseDatabase, ChatMessagesFirebaseMapper mapper,
                            CourtsFirebaseMapper courtsFirebaseMapper) {
    this.mapper = mapper;
    firebaseMapper = courtsFirebaseMapper;
    database = firebaseDatabase.getReference(COURT_NODE);
  }

  public void getChatMessages(String courtId, final OnMessagesDataReadyCallback callback) {
    database.child(firebaseMapper.getCourtId(courtId)).child("messages").addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(DataSnapshot snapshot) {
        callback.onMessagesReady(mapper.mapList(snapshot));
      }

      @Override
      public void onCancelled(DatabaseError error) {
      }
    });
  }

  public void sendMessage(String courtId, MessageData message) {
    database.child(firebaseMapper.getCourtId(courtId)).child("messages").push().setValue(message);
  }
}
