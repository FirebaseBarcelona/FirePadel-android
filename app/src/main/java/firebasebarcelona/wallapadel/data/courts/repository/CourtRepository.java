package firebasebarcelona.wallapadel.data.courts.repository;

import firebasebarcelona.wallapadel.data.courts.datasource.CourtCloudDataSource;
import firebasebarcelona.wallapadel.data.courts.repository.callbacks.OnCourtModifiedCallback;
import firebasebarcelona.wallapadel.data.courts.repository.callbacks.OnRemovePlayerFromCourtCallback;
import firebasebarcelona.wallapadel.data.mappers.CourtsDataMapper;
import firebasebarcelona.wallapadel.data.mappers.PlayersDataMapper;
import firebasebarcelona.wallapadel.data.models.CourtData;
import firebasebarcelona.wallapadel.data.models.PlayerData;
import firebasebarcelona.wallapadel.domain.cases.GetCourtsUseCase;
import firebasebarcelona.wallapadel.domain.cases.callbacks.GetCourtCallback;
import firebasebarcelona.wallapadel.domain.models.Player;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton public class CourtRepository {
  private final CourtCloudDataSource courtCloudDataSource;
  private final CourtsDataMapper courtsDataMapper;
  private final PlayersDataMapper playersDataMapper;

  @Inject
  public CourtRepository(CourtCloudDataSource courtCloudDataSource, CourtsDataMapper courtsDataMapper,
                        PlayersDataMapper playersDataMapper) {
    this.courtCloudDataSource = courtCloudDataSource;
    this.courtsDataMapper = courtsDataMapper;
    this.playersDataMapper = playersDataMapper;
  }

  public void addPlayerToCourt(String id, Player player, final GetCourtCallback callback) {
    PlayerData playerData = playersDataMapper.map(player);
    courtCloudDataSource.addPlayerToCourt(id, playerData, new OnCourtModifiedCallback() {
      @Override
      public void onGetCourtSuccess(CourtData court) {
        callback.onGetCourtSuccess(courtsDataMapper.map(court));
      }
    });
  }

  public void getCourts(GetCourtsUseCase.Callback callback) {
    courtCloudDataSource.getCourts(callback);
  }

  public void getCourt(String courtId, final GetCourtCallback callback) {
    courtCloudDataSource.getCourt(courtId, new OnCourtModifiedCallback() {
      @Override
      public void onGetCourtSuccess(CourtData court) {
        callback.onGetCourtSuccess(courtsDataMapper.map(court));
      }
    });
  }

  public void removePlayerFromCourt(String id, Player player, final OnRemovePlayerFromCourtCallback callback) {
    PlayerData playerData = playersDataMapper.map(player);
    courtCloudDataSource.removePlayerFromCourt(id, playerData, new OnCourtModifiedCallback() {
      @Override
      public void onGetCourtSuccess(CourtData court) {
        callback.onRemovePlayerFromCourtSuccess(courtsDataMapper.map(court));
      }
    });
  }
}
