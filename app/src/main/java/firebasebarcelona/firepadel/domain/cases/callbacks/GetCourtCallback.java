package firebasebarcelona.firepadel.domain.cases.callbacks;

import firebasebarcelona.firepadel.domain.models.Court;

public interface GetCourtCallback {
  void onGetCourtSuccess(Court court);
}
