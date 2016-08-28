package firebasebarcelona.wallapadel.data.player.repository;

import android.support.annotation.Nullable;
import firebasebarcelona.wallapadel.data.courts.repository.CourtRepository;
import firebasebarcelona.wallapadel.data.mappers.PlayersDataMapper;
import firebasebarcelona.wallapadel.data.models.PlayerData;
import firebasebarcelona.wallapadel.data.player.datasource.PlayerLocalDataSource;
import firebasebarcelona.wallapadel.domain.cases.GetPlayersFromCourtUseCase;
import firebasebarcelona.wallapadel.domain.cases.callbacks.GetCourtCallback;
import firebasebarcelona.wallapadel.domain.models.Court;
import firebasebarcelona.wallapadel.domain.models.Player;
import javax.inject.Inject;

public class PlayerRepository {
  private final PlayerLocalDataSource playerLocalDataSource;
  private final PlayersDataMapper playersDataMapper;
  private final CourtRepository courtRepository;

  @Inject
  public PlayerRepository(PlayerLocalDataSource playerLocalDataSource, PlayersDataMapper playersDataMapper,
                         CourtRepository courtRepository) {
    this.playerLocalDataSource = playerLocalDataSource;
    this.playersDataMapper = playersDataMapper;
    this.courtRepository = courtRepository;
  }

  @Nullable
  public Player getMyPlayer() {
    PlayerData playerData = playerLocalDataSource.getMyPlayer();
    Player player = null;
    if (playerData != null) {
      player = playersDataMapper.map(playerData);
    }
    return player;
  }

  public void storeMyPlayer(Player player) {
    playerLocalDataSource.storeMyPlayer(playersDataMapper.map(player));
  }

  public void getPlayers(String courtId, final GetPlayersFromCourtUseCase.Callback callback) {
    courtRepository.getCourt(courtId, new GetCourtCallback() {
      @Override
      public void onGetCourtSuccess(Court court) {
        callback.onPlayersReady(court.getPlayers());
      }
    });
  }
}
