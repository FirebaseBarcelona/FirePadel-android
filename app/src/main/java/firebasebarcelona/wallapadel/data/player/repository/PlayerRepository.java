package firebasebarcelona.wallapadel.data.player.repository;

import android.support.annotation.Nullable;
import firebasebarcelona.wallapadel.data.mappers.PlayersDataMapper;
import firebasebarcelona.wallapadel.data.models.PlayerData;
import firebasebarcelona.wallapadel.data.player.datasource.PlayerLocalDataSource;
import firebasebarcelona.wallapadel.domain.models.Player;
import javax.inject.Inject;

public class PlayerRepository {
  private final PlayerLocalDataSource playerLocalDataSource;
  private final PlayersDataMapper playersDataMapper;

  @Inject
  public PlayerRepository(PlayerLocalDataSource playerLocalDataSource, PlayersDataMapper playersDataMapper) {
    this.playerLocalDataSource = playerLocalDataSource;
    this.playersDataMapper = playersDataMapper;
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
}
