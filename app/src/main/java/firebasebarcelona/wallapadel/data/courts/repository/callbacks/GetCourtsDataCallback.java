package firebasebarcelona.wallapadel.data.courts.repository.callbacks;

import firebasebarcelona.wallapadel.data.models.CourtData;
import java.util.List;

public interface GetCourtsDataCallback {
  void onGetCourtsSuccess(List<CourtData> courts);
}
