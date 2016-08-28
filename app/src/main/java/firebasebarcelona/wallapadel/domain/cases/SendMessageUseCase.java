package firebasebarcelona.wallapadel.domain.cases;

import firebasebarcelona.wallapadel.data.chat.repository.ChatRepository;
import firebasebarcelona.wallapadel.data.player.repository.PlayerRepository;
import firebasebarcelona.wallapadel.domain.models.Message;
import firebasebarcelona.wallapadel.domain.models.Player;
import javax.inject.Inject;

public class SendMessageUseCase extends AbstractUseCase {
  private final ChatRepository chatRepository;
  private final PlayerRepository playerRepository;
  private String courtId;
  private String message;

  @Inject
  public SendMessageUseCase(ChatRepository chatRepository, PlayerRepository playerRepository) {
    this.chatRepository = chatRepository;
    this.playerRepository = playerRepository;
  }

  @Override
  protected void onRun() {
    Player myPlayer = playerRepository.getMyPlayer();
    if (myPlayer != null) {
      Message msg = new Message.Builder().message(message).userUUID(myPlayer.getId()).avatar(myPlayer.getPhotoUrl()).build();
      chatRepository.sendMessage(courtId, msg);
    } else {
      throw new RuntimeException("Not player logged");
    }
  }

  public void execute(String courtId, String message) {
    this.courtId = courtId;
    this.message = message;
    launch();
  }
}
