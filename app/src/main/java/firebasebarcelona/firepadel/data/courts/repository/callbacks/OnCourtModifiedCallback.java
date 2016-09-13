package firebasebarcelona.firepadel.data.courts.repository.callbacks;

import firebasebarcelona.firepadel.data.models.CourtData;

public interface OnCourtModifiedCallback {
  void onGetCourtSuccess(CourtData court);
}
