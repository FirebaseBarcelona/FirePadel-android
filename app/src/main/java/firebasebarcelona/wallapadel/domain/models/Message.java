package firebasebarcelona.wallapadel.domain.models;

public class Message {
  private final String message;
  private final String userUUID;
  private final String avatar;

  private Message(Builder builder) {
    message = builder.message;
    userUUID = builder.userUUID;
    avatar = builder.avatar;
  }

  public String getMessage() {
    return message;
  }

  public String getUserUUID() {
    return userUUID;
  }

  public String getAvatar() {
    return avatar;
  }

  public static final class Builder {
    private String message;
    private String userUUID;
    private String avatar;

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

    public Builder avatar(String val) {
      avatar = val;
      return this;
    }

    public Message build() {
      return new Message(this);
    }
  }
}
