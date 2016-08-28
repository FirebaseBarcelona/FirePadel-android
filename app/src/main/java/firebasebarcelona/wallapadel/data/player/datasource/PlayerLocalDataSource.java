package firebasebarcelona.wallapadel.data.player.datasource;

import android.support.annotation.Nullable;
import firebasebarcelona.wallapadel.data.models.PlayerData;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class PlayerLocalDataSource {
  private PlayerData playerData;
  private Map<String, PlayerData> players;

  @Inject
  public PlayerLocalDataSource() {
    players = new HashMap<>();
  }

  @Nullable
  public PlayerData getMyPlayer(){
    return playerData;
  }

  public void storeMyPlayer(PlayerData playerData){
    this.playerData = playerData;
  }

  public void storePlayer(PlayerData playerData){
    players.put(playerData.getUuid(), playerData);
  }

  public PlayerData getPlayer(String uuid) {
    return players.get(uuid);
  }
}
