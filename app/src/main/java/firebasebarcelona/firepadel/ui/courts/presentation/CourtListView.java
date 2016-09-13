package firebasebarcelona.firepadel.ui.courts.presentation;

import firebasebarcelona.firepadel.ui.models.CourtViewModel;
import firebasebarcelona.firepadel.ui.models.PlayerViewModel;
import java.util.Date;
import java.util.List;

public interface CourtListView {
  void showCourts(List<CourtViewModel> courts);
  void updateCourt(CourtViewModel court);
  void showLoginProcess();
  void loginWithGoogle();
  void renderLocalPlayer(PlayerViewModel myPlayer);
  void openChat(String courtId);
  void renderAddPlayerToCourtError();
  void renderDateForBooking(Date date);
}
