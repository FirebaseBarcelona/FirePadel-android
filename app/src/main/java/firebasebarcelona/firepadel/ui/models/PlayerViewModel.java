package firebasebarcelona.firepadel.ui.models;

public class PlayerViewModel {
  private String id;
  private String name;
  private String photoUrl;
  private String email;

  private PlayerViewModel(Builder builder) {
    setId(builder.id);
    setName(builder.name);
    setPhotoUrl(builder.photoUrl);
    setEmail(builder.email);
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPhotoUrl() {
    return photoUrl;
  }

  public void setPhotoUrl(String photoUrl) {
    this.photoUrl = photoUrl;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getEmail() {
    return email;
  }

  public static final class Builder {
    private String id;
    private String name;
    private String photoUrl;
    private String email;

    public Builder() {
    }

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

    public PlayerViewModel build() {
      return new PlayerViewModel(this);
    }
  }
}
