package firebasebarcelona.firepadel.data.courts.repository.callbacks;

import firebasebarcelona.firepadel.data.models.CourtData;
import java.util.List;

public interface GetCourtsDataCallback {
  void onGetCourtsSuccess(List<CourtData> courts);
}
