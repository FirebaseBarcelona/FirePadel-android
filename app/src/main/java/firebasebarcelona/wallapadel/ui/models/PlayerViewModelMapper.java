package firebasebarcelona.wallapadel.ui.models;

import firebasebarcelona.wallapadel.domain.models.Player;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class PlayerViewModelMapper {
  @Inject
  public PlayerViewModelMapper() {
  }

  Player mapToDomain(PlayerViewModel source) {
    return new Player.Builder().id(source.getId()).name(source.getName()).photoUrl(source.getPhotoUrl()).build();
  }

  public PlayerViewModel map(Player source) {
    return new PlayerViewModel.Builder().id(source.getId()).email(source.getEmail()).name(source.getName()).photoUrl(
    source.getPhotoUrl()).build();
  }

  List<Player> mapToDomain(List<PlayerViewModel> source) {
    List<Player> result = new ArrayList<>();
    for (PlayerViewModel playerViewModel : source) {
      result.add(mapToDomain(playerViewModel));
    }
    return result;
  }

  List<PlayerViewModel> map(List<Player> source) {
    List<PlayerViewModel> result = new ArrayList<>();
    for (Player player : source) {
      result.add(map(player));
    }
    return result;
  }

  public Player map(PlayerViewModel source) {
    return new Player.Builder().id(source.getId()).email(source.getEmail()).name(source.getName()).photoUrl(
    source.getPhotoUrl()).build();
  }
}
