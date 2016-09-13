package firebasebarcelona.firepadel.data.mappers;

import firebasebarcelona.firepadel.data.models.PlayerData;
import firebasebarcelona.firepadel.domain.models.Player;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton public class PlayersDataMapper {
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

  public Player map(PlayerData data) {
    Player player =
    new Player.Builder().id(data.getUuid()).email(data.getEmail()).name(data.getName()).photoUrl(data.getAvatar()).build();
    return player;
  }

  public PlayerData map(Player player) {
    PlayerData data = new PlayerData();
    data.setUuid(player.getId());
    data.setAvatar(player.getPhotoUrl());
    data.setEmail(player.getEmail());
    data.setName(player.getName());
    return data;
  }
}
