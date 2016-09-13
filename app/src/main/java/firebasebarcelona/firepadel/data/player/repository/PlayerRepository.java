package firebasebarcelona.firepadel.data.player.repository;

import android.support.annotation.Nullable;
import firebasebarcelona.firepadel.data.courts.repository.CourtRepository;
import firebasebarcelona.firepadel.data.mappers.PlayersDataMapper;
import firebasebarcelona.firepadel.data.models.PlayerData;
import firebasebarcelona.firepadel.data.player.datasource.PlayerLocalDataSource;
import firebasebarcelona.firepadel.domain.cases.GetPlayersFromCourtUseCase;
import firebasebarcelona.firepadel.domain.cases.callbacks.GetCourtCallback;
import firebasebarcelona.firepadel.domain.models.Court;
import firebasebarcelona.firepadel.domain.models.Player;
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
