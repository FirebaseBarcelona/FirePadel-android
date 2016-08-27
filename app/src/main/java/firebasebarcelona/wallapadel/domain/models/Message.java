package firebasebarcelona.wallapadel.domain.models;

public class Message {
  private final String message;
  private final String userUUID;

  private Message(Builder builder) {
    message = builder.message;
    userUUID = builder.userUUID;
  }

  public String getMessage() {
    return message;
  }

  public String getUserUUID() {
    return userUUID;
  }

  public static final class Builder {
    private String message;
    private String userUUID;

    public Builder() {
    }

    public Builder message(String val) {
      message = val;
      return this;
    }

    public Builder userUUID(String val) {
      userUUID = val;
      return this;
    }

    public Message build() {
      return new Message(this);
    }
  }
}
