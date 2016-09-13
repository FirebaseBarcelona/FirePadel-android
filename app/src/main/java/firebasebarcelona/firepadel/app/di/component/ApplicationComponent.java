package firebasebarcelona.firepadel.app.di.component;

import com.google.firebase.database.FirebaseDatabase;
import dagger.Component;
import firebasebarcelona.firepadel.app.di.module.ApplicationModule;
import firebasebarcelona.firepadel.data.chat.repository.ChatRepository;
import firebasebarcelona.firepadel.data.courts.repository.CourtRepository;
import firebasebarcelona.firepadel.data.padel.repository.PadelRepository;
import firebasebarcelona.firepadel.data.player.repository.PlayerRepository;
import firebasebarcelona.firepadel.ui.DpToPxConverse;
import javax.inject.Singleton;

@Component(modules = ApplicationModule.class) @Singleton public interface ApplicationComponent {
  FirebaseDatabase getFirebaseDatabase();
  CourtRepository getCourtRepository();
  PlayerRepository getPlayerRepository();
  ChatRepository getChatRepository();
  DpToPxConverse getDpConversor();
  PadelRepository getPadelRepository();
}
