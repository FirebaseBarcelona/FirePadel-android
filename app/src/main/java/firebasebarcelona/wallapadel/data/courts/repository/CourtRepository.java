package firebasebarcelona.wallapadel.data.courts.repository;

import firebasebarcelona.wallapadel.data.courts.datasource.CourtCloudDataSource;
import firebasebarcelona.wallapadel.domain.cases.GetCourtsUseCase;
import firebasebarcelona.wallapadel.domain.models.Court;
import firebasebarcelona.wallapadel.domain.models.Player;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class CourtRepository {
  private final CourtCloudDataSource courtCloudDataSource;

  @Inject
  public CourtRepository(CourtCloudDataSource courtCloudDataSource) {
    this.courtCloudDataSource = courtCloudDataSource;
  }

  public Court addPlayerToCourt(String id, Player player) {
    return courtCloudDataSource.addPlayertoCourt(id, player);
  }

  public void getCourts(GetCourtsUseCase.Callback callback) {
    courtCloudDataSource.getCourts(callback);
  }
}
