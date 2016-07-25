package firebasebarcelona.wallapadel.ui.models;

public class PlayerViewModel {
    private String id;
    private String name;
    private String photoUrl;

    private PlayerViewModel(Builder builder) {
        setId(builder.id);
        setName(builder.name);
        setPhotoUrl(builder.photoUrl);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public static final class Builder {
        private String id;
        private String name;
        private String photoUrl;

        public Builder() {
        }

        public Builder id(String val) {
            id = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder photoUrl(String val) {
            photoUrl = val;
            return this;
        }

        public PlayerViewModel build() {
            return new PlayerViewModel(this);
        }
    }
}
