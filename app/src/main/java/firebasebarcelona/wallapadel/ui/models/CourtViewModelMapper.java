package firebasebarcelona.wallapadel.ui.models;

import javax.inject.Inject;

import firebasebarcelona.wallapadel.domain.models.Court;

public class CourtViewModelMapper {
    @Inject PlayerViewModelMapper playerViewModelMapper;

    @Inject
    public CourtViewModelMapper() {
    }

    Court mapToDomain(CourtViewModel source) {
        return new Court.Builder().id(source.getId()).players(playerViewModelMapper.mapToDomain(source.getPlayers())).build();
    }

    CourtViewModel map(Court source) {
        return new CourtViewModel.Builder().id(source.getId()).players(playerViewModelMapper.map(source.getPlayers())).build();
    }
}
