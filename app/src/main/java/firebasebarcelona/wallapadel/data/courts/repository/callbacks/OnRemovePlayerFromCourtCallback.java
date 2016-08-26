package firebasebarcelona.wallapadel.data.courts.repository.callbacks;

import firebasebarcelona.wallapadel.domain.models.Court;

public interface OnRemovePlayerFromCourtCallback {
  void onRemovePlayerFromCourtSuccess(Court court);
}
