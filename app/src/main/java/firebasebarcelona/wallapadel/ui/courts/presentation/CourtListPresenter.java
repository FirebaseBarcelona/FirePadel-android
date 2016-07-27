package firebasebarcelona.wallapadel.ui.courts.presentation;

import java.util.List;

import javax.inject.Inject;

import firebasebarcelona.wallapadel.domain.cases.AddPlayerToCourtUseCase;
import firebasebarcelona.wallapadel.domain.cases.GetCourtsUseCase;
import firebasebarcelona.wallapadel.domain.cases.RemovePlayerFromCourtUseCase;
import firebasebarcelona.wallapadel.domain.models.Court;
import firebasebarcelona.wallapadel.ui.models.CourtViewModel;
import firebasebarcelona.wallapadel.ui.models.CourtViewModelMapper;

public class CourtListPresenter {
    private final AddPlayerToCourtUseCase addPlayerToCourtUseCase;
    private final GetCourtsUseCase getCourtsUseCase;
    private final RemovePlayerFromCourtUseCase removePlayerFromCourtUseCase;
    private final CourtViewModelMapper courtViewModelMapper;
    private final CourtListView courtListView;

    @Inject
    public CourtListPresenter(AddPlayerToCourtUseCase addPlayerToCourtUseCase,
                              GetCourtsUseCase getCourtsUseCase,
                              RemovePlayerFromCourtUseCase removePlayerFromCourtUseCase,
                              CourtViewModelMapper courtViewModelMapper,
                              CourtListView courtListView) {
        this.addPlayerToCourtUseCase = addPlayerToCourtUseCase;
        this.getCourtsUseCase = getCourtsUseCase;
        this.removePlayerFromCourtUseCase = removePlayerFromCourtUseCase;
        this.courtViewModelMapper = courtViewModelMapper;
        this.courtListView = courtListView;
    }

    public void requestCourts() {
        getCourtsUseCase.execute(new GetCourtsUseCase.Callback() {
            @Override
            public void onGetCourtsSuccess(List<Court> courts) {
                List<CourtViewModel> courtViewModels = courtViewModelMapper.map(courts);
                courtListView.showCourts(courtViewModels);
            }
        });
    }
}
