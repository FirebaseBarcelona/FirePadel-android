package firebasebarcelona.firepadel.data.courts.repository;

import firebasebarcelona.firepadel.data.courts.datasource.CourtCloudDataSource;
import firebasebarcelona.firepadel.data.courts.repository.callbacks.GetCourtsDataCallback;
import firebasebarcelona.firepadel.data.courts.repository.callbacks.OnCourtModifiedCallback;
import firebasebarcelona.firepadel.data.mappers.CourtsDataMapper;
import firebasebarcelona.firepadel.data.mappers.PlayersDataMapper;
import firebasebarcelona.firepadel.data.models.CourtData;
import firebasebarcelona.firepadel.data.models.PlayerData;
import firebasebarcelona.firepadel.domain.cases.callbacks.GetCourtCallback;
import firebasebarcelona.firepadel.domain.cases.callbacks.OnGetCourtsCallback;
import firebasebarcelona.firepadel.domain.models.Court;
import firebasebarcelona.firepadel.domain.models.Player;
import java.lang.ref.WeakReference;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import rx.Observable;
import rx.functions.Func1;

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

  public void addPlayerToCourt(String id, Player player) {
    PlayerData playerData = playersDataMapper.map(player);
    courtCloudDataSource.addPlayerToCourt(id, playerData);
  }

  public void getCourts(final OnGetCourtsCallback callback) {
    courtCloudDataSource.getCourts(new GetCourtsDataCallback() {
      @Override
      public void onGetCourtsSuccess(List<CourtData> courts) {
        callback.onGetCourtsSuccess(courtsDataMapper.map(courts));
      }
    });
  }

  public void getCourt(String courtId, final GetCourtCallback callback) {
    courtCloudDataSource.getCourt(courtId, new OnCourtModifiedCallback() {
      @Override
      public void onGetCourtSuccess(CourtData court) {
        callback.onGetCourtSuccess(courtsDataMapper.map(court));
      }
    });
  }

  public void removePlayerFromCourt(String id, Player player) {
    PlayerData playerData = playersDataMapper.map(player);
    courtCloudDataSource.removePlayerFromCourt(id, playerData);
  }

  public Observable<List<Court>> subscribeToCourts() {
    return courtCloudDataSource.subscribeToCourts().map(new Func1<List<CourtData>, List<Court>>() {
      @Override
      public List<Court> call(List<CourtData> courts) {
        return courtsDataMapper.map(courts);
      }
    });
  }

  public void subscribeToCourts(final OnGetCourtsCallback callback) {
    final WeakReference<OnGetCourtsCallback> callbackWeakReference = new WeakReference<>(callback);
    courtCloudDataSource.subscribeToCourts(new GetCourtsDataCallback() {
      @Override
      public void onGetCourtsSuccess(List<CourtData> courts) {
        OnGetCourtsCallback courtCallback = callbackWeakReference.get();
        if (courtCallback != null) {
          callback.onGetCourtsSuccess(courtsDataMapper.map(courts));
        }
      }
    });
  }
}
