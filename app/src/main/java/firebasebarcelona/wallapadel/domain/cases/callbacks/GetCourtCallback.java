package firebasebarcelona.wallapadel.domain.cases.callbacks;

import firebasebarcelona.wallapadel.domain.models.Court;

public interface GetCourtCallback {
  void onGetCourtSuccess(Court court);
}
