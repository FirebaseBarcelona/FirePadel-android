package firebasebarcelona.wallapadel.data.models;

public class UserData {
  private String userName;
  private String userId;
  private int state;

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public int getState() {
    return state;
  }

  public void setState(int state) {
    this.state = state;
  }

  private UserData(Builder builder) {
    userName = builder.userName;
    userId = builder.userId;
    state = builder.state;
  }

  public static final class Builder {
    private String userName;
    private String userId;
    private int state;

    public Builder() {
    }

    public Builder userName(String val) {
      userName = val;
      return this;
    }

    public Builder userId(String val) {
      userId = val;
      return this;
    }

    public Builder state(int val) {
      state = val;
      return this;
    }

    public UserData build() {
      return new UserData(this);
    }
  }
}
