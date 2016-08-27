package firebasebarcelona.wallapadel.data.models;

public class MessageData {
  private String message;
  private String uuid;
  private String avatar;
  private String name;

  public MessageData() {
  }

  private MessageData(Builder builder) {
    message = builder.message;
    uuid = builder.uuid;
    avatar = builder.avatar;
    name = builder.name;
  }

  public String getAvatar() {
    return avatar;
  }

  public String getName() {
    return name;
  }

  public String getUuid() {
    return uuid;
  }

  public String getMessage() {
    return message;
  }

  public static final class Builder {
    private String message;
    private String uuid;
    private String avatar;
    private String name;

    public Builder() {
    }

    public Builder message(String val) {
      message = val;
      return this;
    }

    public Builder uuid(String val) {
      uuid = val;
      return this;
    }

    public Builder avatar(String val) {
      avatar = val;
      return this;
    }

    public Builder name(String val) {
      name = val;
      return this;
    }

    public MessageData build() {
      return new MessageData(this);
    }
  }
}
