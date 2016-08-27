package firebasebarcelona.wallapadel.ui.courts.presentation;

import firebasebarcelona.wallapadel.ui.models.CourtViewModel;
import firebasebarcelona.wallapadel.ui.models.PlayerViewModel;
import java.util.List;

public interface CourtListView {
  void showCourts(List<CourtViewModel> courts);
  void updateCourt(CourtViewModel court);
  void showLoginProcess();
  void loginWithGoogle();
  void setMyPlayer(PlayerViewModel myPlayer);
}
