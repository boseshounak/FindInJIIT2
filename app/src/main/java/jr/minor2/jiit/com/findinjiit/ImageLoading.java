package jr.minor2.jiit.com.findinjiit;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import ss.com.bannerslider.ImageLoadingService;

public class ImageLoading implements ImageLoadingService {
    Context context;

    public ImageLoading(Context context) {

        this.context = context;
    }

    @Override
    public void loadImage(String url, ImageView imageView) {

    }

    @Override
    public void loadImage(int resource, ImageView imageView) {
        Picasso.with(context).load(R.drawable.bannerpic1).into(imageView);
    }

    @Override
    public void loadImage(String url, int placeHolder, int errorDrawable, ImageView imageView) {
        Picasso.with(context).load(url).placeholder(placeHolder).error(errorDrawable).into(imageView);
    }
}
