package firebasebarcelona.wallapadel.ui.models;

public class MessageViewModel {
  private final String message;
  private final String userUUID;

  public MessageViewModel(String message, String userUUID) {
    this.message = message;
    this.userUUID = userUUID;
  }

  public String getMessage() {
    return message;
  }
}
