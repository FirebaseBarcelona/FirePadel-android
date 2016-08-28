package firebasebarcelona.wallapadel.domain.cases.callbacks;

import firebasebarcelona.wallapadel.domain.models.Player;

public interface IsPlayerInCourtCallback {
  void playerInCourt(Player player);
  void playerNotInCourt(Player player);
  void onLoginRequired();
}
