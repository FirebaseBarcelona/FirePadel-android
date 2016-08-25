package firebasebarcelona.wallapadel.data.courts.repository;

import firebasebarcelona.wallapadel.data.courts.datasource.CourtCloudDataSource;
import firebasebarcelona.wallapadel.domain.cases.GetCourtsUseCase;
import firebasebarcelona.wallapadel.domain.models.Player;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton public class CourtRepository {
  private final CourtCloudDataSource courtCloudDataSource;

  @Inject
  public CourtRepository(CourtCloudDataSource courtCloudDataSource) {
    this.courtCloudDataSource = courtCloudDataSource;
  }

  public void addPlayerToCourt(String id, Player player, GetCourtCallback callback) {
    courtCloudDataSource.addPlayertoCourt(id, player, callback);
  }

  public void getCourts(GetCourtsUseCase.Callback callback) {
    courtCloudDataSource.getCourts(callback);
  }
}
