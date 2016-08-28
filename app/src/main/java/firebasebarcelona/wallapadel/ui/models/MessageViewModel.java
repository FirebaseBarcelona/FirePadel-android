package firebasebarcelona.wallapadel.ui.models;

public class MessageViewModel {
  private String message;
  private String userUUID;
  private String avatar;

  public MessageViewModel(String message, String userUUID, String avatar) {
    this.message = message;
    this.userUUID = userUUID;
    this.avatar = avatar;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getUserUUID() {
    return userUUID;
  }

  public void setUserUUID(String userUUID) {
    this.userUUID = userUUID;
  }

  public String getMessage() {
    return message;
  }

  public String getAvatar() {
    return avatar;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }
}
