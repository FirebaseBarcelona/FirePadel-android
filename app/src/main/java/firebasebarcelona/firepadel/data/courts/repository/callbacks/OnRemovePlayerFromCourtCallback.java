package firebasebarcelona.firepadel.data.courts.repository.callbacks;

import firebasebarcelona.firepadel.domain.models.Court;

public interface OnRemovePlayerFromCourtCallback {
  void onRemovePlayerFromCourtSuccess(Court court);
}
