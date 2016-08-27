package firebasebarcelona.wallapadel.ui.courts.presentation;

import firebasebarcelona.wallapadel.domain.cases.AddPlayerToCourtUseCase;
import firebasebarcelona.wallapadel.domain.cases.GetCourtsUseCase;
import firebasebarcelona.wallapadel.domain.cases.GetLocalPlayerUseCase;
import firebasebarcelona.wallapadel.domain.cases.IsPlayerInCourtUseCase;
import firebasebarcelona.wallapadel.domain.cases.RemovePlayerFromCourtUseCase;
import firebasebarcelona.wallapadel.domain.cases.SaveLocalPlayerUseCase;
import firebasebarcelona.wallapadel.domain.cases.callbacks.IsPlayerInCourtCallback;
import firebasebarcelona.wallapadel.domain.models.Court;
import firebasebarcelona.wallapadel.domain.models.Player;
import firebasebarcelona.wallapadel.ui.models.CourtViewModel;
import firebasebarcelona.wallapadel.ui.models.CourtViewModelMapper;
import firebasebarcelona.wallapadel.ui.models.PlayerViewModel;
import firebasebarcelona.wallapadel.ui.models.PlayerViewModelMapper;
import java.util.List;
import javax.inject.Inject;

public class CourtListPresenter {
  private final AddPlayerToCourtUseCase addPlayerToCourtUseCase;
  private final GetCourtsUseCase getCourtsUseCase;
  private final GetLocalPlayerUseCase getLocalPlayerUseCase;
  private final SaveLocalPlayerUseCase saveLocalPlayerUseCase;
  private final CourtViewModelMapper courtViewModelMapper;
  private final PlayerViewModelMapper playerViewModelMapper;
  private final CourtListView courtListView;
  private boolean pendingAddLocalPlayer = false;
  private String pendingCourtId;
  private IsPlayerInCourtUseCase isPlayerInCourtUseCase;
  private RemovePlayerFromCourtUseCase removePlayerFromCourtUseCase;

  @Inject
  public CourtListPresenter(AddPlayerToCourtUseCase addPlayerToCourtUseCase, GetCourtsUseCase getCourtsUseCase,
                           GetLocalPlayerUseCase getLocalPlayerUseCase, SaveLocalPlayerUseCase saveLocalPlayerUseCase,
                           CourtViewModelMapper courtViewModelMapper, PlayerViewModelMapper playerViewModelMapper,
                           CourtListView courtListView, IsPlayerInCourtUseCase isPlayerInCourtUseCase,
                           RemovePlayerFromCourtUseCase removePlayerFromCourtUseCase) {
    this.addPlayerToCourtUseCase = addPlayerToCourtUseCase;
    this.getCourtsUseCase = getCourtsUseCase;
    this.getLocalPlayerUseCase = getLocalPlayerUseCase;
    this.saveLocalPlayerUseCase = saveLocalPlayerUseCase;
    this.courtViewModelMapper = courtViewModelMapper;
    this.playerViewModelMapper = playerViewModelMapper;
    this.courtListView = courtListView;
    this.isPlayerInCourtUseCase = isPlayerInCourtUseCase;
    this.removePlayerFromCourtUseCase = removePlayerFromCourtUseCase;
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

  public void setLocalPlayer(PlayerViewModel playerViewModel) {
    courtListView.setMyPlayer(playerViewModel);
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
    getLocalPlayerUseCase.execute(new GetLocalPlayerUseCase.Callback() {
      @Override
      public void onGetLocalPlayerSuccess(Player player) {
        isPlayerInCourtUseCase.execute(courtId, player, new IsPlayerInCourtCallback() {
          @Override
          public void playerInCourt(Player player) {
            removePlayerFromCourtUseCase.execute(courtId, player, new RemovePlayerFromCourtUseCase.Callback() {
              @Override
              public void onRemovePlayerFromCourtSuccess(Court court) {
                CourtViewModel courtViewModel = courtViewModelMapper.map(court);
                courtListView.updateCourt(courtViewModel);
              }
            });
          }

          @Override
          public void playerNotInCourt(Player player) {
            addPlayerToCourtUseCase.execute(courtId, player, new AddPlayerToCourtUseCase.Callback() {
              @Override
              public void onAddPlayerToCourtSuccess(Court court) {
                CourtViewModel courtViewModel = courtViewModelMapper.map(court);
                courtListView.updateCourt(courtViewModel);
              }
            });
          }
        });
      }

      @Override
      public void onGetLocalPlayerError() {
        pendingCourtId = courtId;
        pendingAddLocalPlayer = true;
        courtListView.loginWithGoogle();
      }
    });
  }

  public void requestToChat(String courtId) {
  }
}
