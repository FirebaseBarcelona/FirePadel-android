package firebasebarcelona.wallapadel.domain.models;

public class Player {
  private String id;
  private String name;
  private String photoUrl;

  private Player(Builder builder) {
    id = builder.id;
    name = builder.name;
    photoUrl = builder.photoUrl;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getPhotoUrl() {
    return photoUrl;
  }

  public static final class Builder {

    private String id;
    private String name;
    private String photoUrl;

    public Builder() {}

    public Builder id(String val) {
      id = val;
      return this;
    }

    public Builder name(String val) {
      name = val;
      return this;
    }

    public Builder photoUrl(String val) {
      photoUrl = val;
      return this;
    }

    public Player build() {return new Player(this);}
  }
}
