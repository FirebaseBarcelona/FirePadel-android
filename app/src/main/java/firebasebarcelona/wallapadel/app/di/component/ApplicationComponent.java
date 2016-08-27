package firebasebarcelona.wallapadel.app.di.component;

import com.google.firebase.database.FirebaseDatabase;
import dagger.Component;
import firebasebarcelona.wallapadel.app.di.module.ApplicationModule;
import firebasebarcelona.wallapadel.data.chat.repository.ChatRepository;
import firebasebarcelona.wallapadel.data.courts.repository.CourtRepository;
import firebasebarcelona.wallapadel.data.player.repository.PlayerRepository;
import javax.inject.Singleton;

@Component(modules = ApplicationModule.class) @Singleton public interface ApplicationComponent {
  FirebaseDatabase getFirebaseDatabase();
  CourtRepository getCourtRepository();
  PlayerRepository getPlayerRepository();
  ChatRepository getChatRepository();
}
