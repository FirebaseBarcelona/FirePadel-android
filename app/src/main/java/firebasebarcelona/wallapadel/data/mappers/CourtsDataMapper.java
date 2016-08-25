package firebasebarcelona.wallapadel.data.mappers;

import firebasebarcelona.wallapadel.data.models.CourtData;
import firebasebarcelona.wallapadel.domain.models.Court;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton public class CourtsDataMapper {
  private final PlayersDataMapper playersMapper;

  @Inject
  public CourtsDataMapper(PlayersDataMapper mapper) {
    playersMapper = mapper;
  }

  public List<Court> map(List<CourtData> courts) {
    List<Court> result = new ArrayList<>();
    for (CourtData court : courts) {
      Court courtDomain = map(court);
      result.add(courtDomain);
    }
    return result;
  }

  public Court map(CourtData data) {
    Court courtDomain = new Court.Builder().id(data.getId()).players(playersMapper.map(data.getPlayers())).build();
    return courtDomain;
  }
}
