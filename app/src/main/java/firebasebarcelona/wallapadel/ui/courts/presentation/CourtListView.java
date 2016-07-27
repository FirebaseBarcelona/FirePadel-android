package firebasebarcelona.wallapadel.ui.courts.presentation;

import java.util.List;

import firebasebarcelona.wallapadel.ui.models.CourtViewModel;

public interface CourtListView {
    void showCourts(List<CourtViewModel> courts);
    void updateCourt(CourtViewModel court);
    void showLoginProcess();
    void loginWithGoogle();
}
