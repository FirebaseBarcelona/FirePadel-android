package firebasebarcelona.firepadel.data.models;

import java.util.List;

public class CourtData {
  private String id;
  private List<PlayerData> users;

  public void setId(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }

  public List<PlayerData> getPlayers() {
    return users;
  }

  public void setPlayers(List<PlayerData> players) {
    this.users = players;
  }
}
