package firebasebarcelona.firepadel.domain.cases.callbacks;

import firebasebarcelona.firepadel.domain.models.Player;

public interface IsPlayerInCourtCallback {
  void playerInCourt(Player player);
  void playerNotInCourt(Player player);
  void onLoginRequired();
}
