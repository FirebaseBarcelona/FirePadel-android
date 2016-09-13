package firebasebarcelona.firepadel.data.login.repository;

import firebasebarcelona.firepadel.data.login.datasource.UserLocalDataSource;
import firebasebarcelona.firepadel.data.models.UserDataMapper;
import firebasebarcelona.firepadel.domain.models.User;
import javax.inject.Inject;

public class UserRepository {
  private final UserLocalDataSource userLocalDataSource;
  private final UserDataMapper mapper;

  @Inject
  public UserRepository(UserLocalDataSource userLocalDataSource, UserDataMapper mapper) {
    this.userLocalDataSource = userLocalDataSource;
    this.mapper = mapper;
  }

  public User getLoggedUser(){
    return mapper.map(userLocalDataSource.getLoggedUser());
  }
}
