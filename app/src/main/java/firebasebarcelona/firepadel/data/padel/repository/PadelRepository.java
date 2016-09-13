package firebasebarcelona.firepadel.data.padel.repository;

import firebasebarcelona.firepadel.data.padel.datasource.PadelCloudDataSource;
import firebasebarcelona.firepadel.data.padel.repository.callbacks.OnGetNextDateForBookingData;
import firebasebarcelona.firepadel.domain.cases.GetNextDateForCourtBookingUseCase;
import javax.inject.Inject;

public class PadelRepository {
  private final PadelCloudDataSource padelCloudDataSource;

  @Inject
  public PadelRepository(PadelCloudDataSource padelCloudDataSource) {
    this.padelCloudDataSource = padelCloudDataSource;
  }

  public void getNextDateForBookingCourts(final GetNextDateForCourtBookingUseCase.Callback callback) {
    padelCloudDataSource.getNextDateForBookingCourts(new OnGetNextDateForBookingData() {
      @Override
      public void onDateReady(long timestamp) {
        callback.onNextDateReady(timestamp);
      }
    });
  }
}
