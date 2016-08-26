package firebasebarcelona.wallapadel.data.models;

public class PlayerData {
  String uuid;
  String avatar;
  String email;
  String name;

  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAvatar() {
    return avatar;
  }

  public String getEmail() {
    return email;
  }

  public String getName() {
    return name;
  }
}
