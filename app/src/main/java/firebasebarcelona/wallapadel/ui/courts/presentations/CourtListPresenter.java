package firebasebarcelona.wallapadel.ui.courts.presentations;

import javax.inject.Inject;

import firebasebarcelona.wallapadel.domain.cases.AddPlayerToCourtUseCase;
import firebasebarcelona.wallapadel.domain.cases.GetCourtsUseCase;
import firebasebarcelona.wallapadel.domain.cases.RemovePlayerFromCourtUseCase;

public class CourtListPresenter {
    private final AddPlayerToCourtUseCase addPlayerToCourtUseCase;
    private final GetCourtsUseCase getCourtsUseCase;
    private final RemovePlayerFromCourtUseCase removePlayerFromCourtUseCase;
    private final CourtListView courtListView;

    @Inject
    public CourtListPresenter(AddPlayerToCourtUseCase addPlayerToCourtUseCase,
                              GetCourtsUseCase getCourtsUseCase,
                              RemovePlayerFromCourtUseCase removePlayerFromCourtUseCase,
                              CourtListView courtListView) {
        this.addPlayerToCourtUseCase = addPlayerToCourtUseCase;
        this.getCourtsUseCase = getCourtsUseCase;
        this.removePlayerFromCourtUseCase = removePlayerFromCourtUseCase;
        this.courtListView = courtListView;
    }
}
