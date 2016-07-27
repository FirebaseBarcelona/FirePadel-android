package firebasebarcelona.wallapadel.ui.models;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import firebasebarcelona.wallapadel.domain.models.Court;

public class CourtViewModelMapper {
    @Inject PlayerViewModelMapper playerViewModelMapper;

    @Inject
    public CourtViewModelMapper() {
    }

    public Court mapToDomain(CourtViewModel source) {
        return new Court.Builder().id(source.getId()).players(playerViewModelMapper.mapToDomain(source.getPlayers())).build();
    }

    public CourtViewModel map(Court source) {
        return new CourtViewModel.Builder().id(source.getId()).players(playerViewModelMapper.map(source.getPlayers())).build();
    }

    public List<CourtViewModel> map(List<Court> source) {
        List<CourtViewModel> result = new ArrayList<>();
        for (Court court : source) {
            result.add(map(court));
        }
        return result;
    }
}
