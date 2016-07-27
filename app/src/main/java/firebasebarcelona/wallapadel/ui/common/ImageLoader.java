package firebasebarcelona.wallapadel.ui.common;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.CircleTransformation;

import javax.inject.Inject;

public class ImageLoader {
    @Inject
    public ImageLoader() {
    }

    public void loadImage(Context context, final ImageView imageView, String url) {
        Glide.with(context).load(url).transform(new CircleTransformation(context)).into(imageView);
    }
}
