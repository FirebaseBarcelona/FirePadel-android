package firebasebarcelona.wallapadel.domain.cases.callbacks;

import firebasebarcelona.wallapadel.domain.models.Court;
import java.util.List;

public interface OnGetCourtsCallback {
  void onGetCourtsSuccess(List<Court> courts);
}
