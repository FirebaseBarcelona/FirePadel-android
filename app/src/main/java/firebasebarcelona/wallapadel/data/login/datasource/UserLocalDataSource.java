package firebasebarcelona.wallapadel.data.login.datasource;

import firebasebarcelona.wallapadel.data.models.UserData;
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

