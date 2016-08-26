package firebasebarcelona.wallapadel.data.courts.repository;

import firebasebarcelona.wallapadel.data.courts.datasource.CourtCloudDataSource;
import firebasebarcelona.wallapadel.data.courts.repository.callbacks.OnCourtModifiedCallback;
import firebasebarcelona.wallapadel.data.courts.repository.callbacks.OnRemovePlayerFromCourtCallback;
import firebasebarcelona.wallapadel.data.mappers.CourtsDataMapper;
import firebasebarcelona.wallapadel.data.models.CourtData;
import firebasebarcelona.wallapadel.domain.cases.GetCourtsUseCase;
import firebasebarcelona.wallapadel.domain.cases.callbacks.GetCourtCallback;
import firebasebarcelona.wallapadel.domain.models.Player;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton public class CourtRepository {
  private final CourtCloudDataSource courtCloudDataSource;
  private final CourtsDataMapper courtsDataMapper;

  @Inject
  public CourtRepository(CourtCloudDataSource courtCloudDataSource, CourtsDataMapper courtsDataMapper) {
    this.courtCloudDataSource = courtCloudDataSource;
    this.courtsDataMapper = courtsDataMapper;
  }

  public void addPlayerToCourt(String id, Player player, final GetCourtCallback callback) {
    courtCloudDataSource.addPlayerToCourt(id, player, new OnCourtModifiedCallback() {
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
    courtCloudDataSource.removePlayerFromCourt(id, player, new OnCourtModifiedCallback() {
      @Override
      public void onGetCourtSuccess(CourtData court) {
        callback.onRemovePlayerFromCourtSuccess(courtsDataMapper.map(court));
      }
    });
  }
}
