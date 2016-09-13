package firebasebarcelona.firepadel.ui.courts.presentation;

import firebasebarcelona.firepadel.app.rx.AbsSubscriber;
import firebasebarcelona.firepadel.domain.cases.AddPlayerToCourtUseCase;
import firebasebarcelona.firepadel.domain.cases.GetNextDateForCourtBookingUseCase;
import firebasebarcelona.firepadel.domain.cases.IsPlayerInCourtUseCase;
import firebasebarcelona.firepadel.domain.cases.RemovePlayerFromCourtUseCase;
import firebasebarcelona.firepadel.domain.cases.SaveLocalPlayerUseCase;
import firebasebarcelona.firepadel.domain.cases.SubscribeToCourtsUseCase;
import firebasebarcelona.firepadel.domain.cases.callbacks.IsPlayerInCourtCallback;
import firebasebarcelona.firepadel.domain.exceptions.PlayerInAnotherCourtException;
import firebasebarcelona.firepadel.domain.models.Court;
import firebasebarcelona.firepadel.domain.models.Player;
import firebasebarcelona.firepadel.ui.models.CourtViewModel;
import firebasebarcelona.firepadel.ui.models.CourtViewModelMapper;
import firebasebarcelona.firepadel.ui.models.PlayerViewModel;
import firebasebarcelona.firepadel.ui.models.PlayerViewModelMapper;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;

public class CourtListPresenter {
  private final AddPlayerToCourtUseCase addPlayerToCourtUseCase;
  private final SaveLocalPlayerUseCase saveLocalPlayerUseCase;
  private final CourtViewModelMapper courtViewModelMapper;
  private final PlayerViewModelMapper playerViewModelMapper;
  private final CourtListView courtListView;
  private boolean pendingAddLocalPlayer = false;
  private String pendingCourtId;
  private IsPlayerInCourtUseCase isPlayerInCourtUseCase;
  private RemovePlayerFromCourtUseCase removePlayerFromCourtUseCase;
  private final SubscribeToCourtsUseCase subscribeToCourtsUseCase;
  private final GetNextDateForCourtBookingUseCase getNextDateForCourtBookingUseCase;

  @Inject
  public CourtListPresenter(AddPlayerToCourtUseCase addPlayerToCourtUseCase, SaveLocalPlayerUseCase saveLocalPlayerUseCase,
                           CourtViewModelMapper courtViewModelMapper, PlayerViewModelMapper playerViewModelMapper,
                           CourtListView courtListView, IsPlayerInCourtUseCase isPlayerInCourtUseCase,
                           RemovePlayerFromCourtUseCase removePlayerFromCourtUseCase,
                           SubscribeToCourtsUseCase subscribeToCourtsUseCase,
                           GetNextDateForCourtBookingUseCase getNextDateForCourtBookingUseCase) {
    this.addPlayerToCourtUseCase = addPlayerToCourtUseCase;
    this.saveLocalPlayerUseCase = saveLocalPlayerUseCase;
    this.courtViewModelMapper = courtViewModelMapper;
    this.playerViewModelMapper = playerViewModelMapper;
    this.courtListView = courtListView;
    this.isPlayerInCourtUseCase = isPlayerInCourtUseCase;
    this.removePlayerFromCourtUseCase = removePlayerFromCourtUseCase;
    this.subscribeToCourtsUseCase = subscribeToCourtsUseCase;
    this.getNextDateForCourtBookingUseCase = getNextDateForCourtBookingUseCase;
  }

  public void subscribeToCourts() {
    subscribeToCourtsUseCase.execute(new AbsSubscriber<List<Court>>() {
      @Override
      public void onNext(List<Court> courts) {
        List<CourtViewModel> courtViewModels = courtViewModelMapper.map(courts);
        courtListView.showCourts(courtViewModels);
      }
    });
  }

  public void unsubscribeToCourts() {
    subscribeToCourtsUseCase.unsuscribe();
  }

  public void setLocalPlayer(PlayerViewModel playerViewModel) {
    courtListView.renderLocalPlayer(playerViewModel);
    Player player = playerViewModelMapper.map(playerViewModel);
    saveLocalPlayerUseCase.execute(player, new SaveLocalPlayerUseCase.Callback() {
      @Override
      public void onSaveLocalPlayerSuccess(Player player) {
        if (pendingAddLocalPlayer) {
          pendingAddLocalPlayer = false;
          requestAddLocalPlayerToCourt(pendingCourtId);
        }
      }
    });
  }

  public void requestAddLocalPlayerToCourt(final String courtId) {
    isPlayerInCourtUseCase.execute(courtId, new IsPlayerInCourtCallback() {
      @Override
      public void playerInCourt(Player player) {
        removePlayerFromCourt(player, courtId);
      }

      @Override
      public void playerNotInCourt(Player player) {
        addPlayerToCourt(player, courtId);
      }

      @Override
      public void onLoginRequired() {
        pendingCourtId = courtId;
        pendingAddLocalPlayer = true;
        courtListView.loginWithGoogle();
      }
    });
  }

  private void addPlayerToCourt(Player player, String courtId) {
    addPlayerToCourtUseCase.execute(courtId, player, new AddPlayerToCourtUseCase.Callback() {
      @Override
      public void onAddPlayerToCourtSuccess(Court court) {
        CourtViewModel courtViewModel = courtViewModelMapper.map(court);
        courtListView.updateCourt(courtViewModel);
      }

      @Override
      public void onAddPlayerToCourtFailed(PlayerInAnotherCourtException exception) {
        courtListView.renderAddPlayerToCourtError();
      }
    });
  }

  private void removePlayerFromCourt(Player player, String courtId) {
    removePlayerFromCourtUseCase.execute(courtId, player, new RemovePlayerFromCourtUseCase.Callback() {
      @Override
      public void onRemovePlayerFromCourtSuccess(Court court) {
        CourtViewModel courtViewModel = courtViewModelMapper.map(court);
        courtListView.updateCourt(courtViewModel);
      }
    });
  }

  public void requestToChat(String courtId) {
    courtListView.openChat(courtId);
  }

  public void requestDateForBooking() {
    getNextDateForCourtBookingUseCase.execute(new GetNextDateForCourtBookingUseCase.Callback() {
      @Override
      public void onNextDateReady(long timestamp) {
        Date date = new Date(timestamp);
        courtListView.renderDateForBooking(date);
      }
    });
  }
}
