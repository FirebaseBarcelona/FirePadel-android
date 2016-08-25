package firebasebarcelona.wallapadel.data.mappers;

import firebasebarcelona.wallapadel.data.models.PlayerData;
import firebasebarcelona.wallapadel.domain.models.Player;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class PlayersDataMapper {
  @Inject
  public PlayersDataMapper() {
  }

  public List<Player> map(List<PlayerData> users) {
    List<Player> players = new ArrayList<>();
    for (PlayerData playerData : users) {
      players.add(map(playerData));
    }
    return players;
  }

  private Player map(PlayerData data) {
    return new Player.Builder().id(data.getEmail()).name(data.getName()).photoUrl(data.getAvatar()).build();
  }

  public PlayerData map(Player player) {
    PlayerData data = new PlayerData();
    data.setAvatar(player.getPhotoUrl());
    data.setEmail(player.getId());
    data.setName(player.getName());
    return data;
  }
}
