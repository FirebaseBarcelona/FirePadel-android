package firebasebarcelona.firepadel.data.models;

import firebasebarcelona.firepadel.domain.models.User;
import javax.inject.Inject;

public class UserDataMapper {
  @Inject
  public UserDataMapper() {
  }

  public User map(UserData user) {
    return new User.Builder().userId(user.getUserId()).userName(user.getUserName()).build();
  }
}
