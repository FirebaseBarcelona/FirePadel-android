package firebasebarcelona.firepadel.domain.cases.callbacks;

import firebasebarcelona.firepadel.domain.models.Court;
import java.util.List;

public interface OnGetCourtsCallback {
  void onGetCourtsSuccess(List<Court> courts);
}
