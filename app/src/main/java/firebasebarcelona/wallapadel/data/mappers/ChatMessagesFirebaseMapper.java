package firebasebarcelona.wallapadel.data.mappers;

import com.google.firebase.database.DataSnapshot;
import firebasebarcelona.wallapadel.data.models.MessageData;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class ChatMessagesFirebaseMapper {
  @Inject
  public ChatMessagesFirebaseMapper() {
  }

  public List<MessageData> mapList(DataSnapshot snapshot) {
    List<MessageData> result = new ArrayList<>();
    for (DataSnapshot childSnapshot : snapshot.getChildren()) {
      result.add(map(childSnapshot));
    }
    return result;
  }

  public MessageData map(DataSnapshot snapshot) {
    MessageData messageData = snapshot.getValue(MessageData.class);
    return messageData;
  }
}
