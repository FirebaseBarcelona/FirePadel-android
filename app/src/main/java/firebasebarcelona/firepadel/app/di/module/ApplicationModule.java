package firebasebarcelona.firepadel.app.di.module;

import com.google.firebase.database.FirebaseDatabase;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
public class ApplicationModule {
  @Provides
  @Singleton
  FirebaseDatabase provideFirebaseDatabase(){
    return FirebaseDatabase.getInstance();
  }
}
