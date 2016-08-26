package firebasebarcelona.wallapadel.domain.models;

public class Player {
  private final String id;
  private final String name;
  private final String photoUrl;
  private final String email;

  private Player(Builder builder) {
    id = builder.id;
    name = builder.name;
    photoUrl = builder.photoUrl;
    email = builder.email;
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

  public String getEmail() {
    return email;
  }

  public static final class Builder {
    private String id;
    private String name;
    private String photoUrl;
    private String email;

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

    public Builder email(String val) {
      email = val;
      return this;
    }

    public Player build() {return new Player(this);}
  }
}
