package firebasebarcelona.firepadel.ui.models;

import java.util.List;

public class CourtViewModel {
    private String id;
    private List<PlayerViewModel> players;

    private CourtViewModel(Builder builder) {
        setId(builder.id);
        setPlayers(builder.players);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<PlayerViewModel> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerViewModel> players) {
        this.players = players;
    }

    public static final class Builder {
        private String id;
        private List<PlayerViewModel> players;

        public Builder() {
        }

        public Builder id(String val) {
            id = val;
            return this;
        }

        public Builder players(List<PlayerViewModel> val) {
            players = val;
            return this;
        }

        public CourtViewModel build() {
            return new CourtViewModel(this);
        }
    }
}
