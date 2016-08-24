package firebasebarcelona.wallapadel.app.di.component;

import com.google.firebase.database.FirebaseDatabase;
import dagger.Component;
import firebasebarcelona.wallapadel.app.di.module.ApplicationModule;
import firebasebarcelona.wallapadel.data.courts.repository.CourtRepository;
import javax.inject.Singleton;

@Component(modules = ApplicationModule.class)
@Singleton
public interface ApplicationComponent {
  FirebaseDatabase getFirebaseDatabase();
  CourtRepository getCourtRepository();
}
