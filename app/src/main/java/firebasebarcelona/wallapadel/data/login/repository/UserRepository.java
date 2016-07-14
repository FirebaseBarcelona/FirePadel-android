package firebasebarcelona.wallapadel.data.login.repository;

import firebasebarcelona.wallapadel.data.login.datasource.UserLocalDataSource;
import firebasebarcelona.wallapadel.data.models.UserDataMapper;
import firebasebarcelona.wallapadel.domain.models.User;
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
