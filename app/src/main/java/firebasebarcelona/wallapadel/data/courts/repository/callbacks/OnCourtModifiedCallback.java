package firebasebarcelona.wallapadel.data.courts.repository.callbacks;

import firebasebarcelona.wallapadel.data.models.CourtData;

public interface OnCourtModifiedCallback {
  void onGetCourtSuccess(CourtData court);
}
