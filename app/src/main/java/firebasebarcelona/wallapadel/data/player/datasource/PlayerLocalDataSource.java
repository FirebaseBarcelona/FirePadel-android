package firebasebarcelona.wallapadel.data.player.datasource;

import android.support.annotation.Nullable;
import firebasebarcelona.wallapadel.data.models.PlayerData;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class PlayerLocalDataSource {
  private PlayerData playerData;

  @Inject
  public PlayerLocalDataSource() {
  }

  @Nullable
  public PlayerData getMyPlayer(){
    return playerData;
  }

  public void storeMyPlayer(PlayerData playerData){
    this.playerData = playerData;
  }
}
