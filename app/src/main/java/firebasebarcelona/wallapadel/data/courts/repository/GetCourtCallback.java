package firebasebarcelona.wallapadel.data.courts.repository;

import firebasebarcelona.wallapadel.domain.models.Court;

public interface GetCourtCallback {
  void onGetCourtSuccess(Court court);
}
