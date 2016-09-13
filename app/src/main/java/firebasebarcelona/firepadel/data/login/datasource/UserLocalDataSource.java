package firebasebarcelona.firepadel.data.login.datasource;

import firebasebarcelona.firepadel.data.models.UserData;
import javax.inject.Inject;

public class UserLocalDataSource {
  private UserData loggedUser;

  @Inject
  public UserLocalDataSource() {
  }

  public UserData getLoggedUser() {
    return loggedUser;
  }
}

