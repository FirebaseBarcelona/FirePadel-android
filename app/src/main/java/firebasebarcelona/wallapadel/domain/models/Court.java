package firebasebarcelona.wallapadel.domain.models;

import java.util.List;

public class Court {
  private String id;
  private List<Player> players;

  private Court(Builder builder) {
    id = builder.id;
    players = builder.players;
  }

  public String getId() {
    return id;
  }

  public List<Player> getPlayers() {
    return players;
  }

  public static final class Builder {

    private String id;
    private List<Player> players;

    public Builder() {}

    public Builder id(String val) {
      id = val;
      return this;
    }

    public Builder players(List<Player> val) {
      players = val;
      return this;
    }

    public Court build() {return new Court(this);}
  }
}
