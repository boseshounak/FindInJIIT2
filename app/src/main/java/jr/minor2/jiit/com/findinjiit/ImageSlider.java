package jr.minor2.jiit.com.findinjiit;

import ss.com.bannerslider.adapters.SliderAdapter;
import ss.com.bannerslider.viewholder.ImageSlideViewHolder;

public class ImageSlider extends SliderAdapter {
    @Override
    public int getItemCount() {
        return 3;
    }

    @Override
    public void onBindImageSlide(int position, ImageSlideViewHolder imageSlideViewHolder) {
        switch (position){
            case 1: imageSlideViewHolder.bindImageSlide("http://www.jiit.ac.in/sites/default/files/1.jpg"); break;
            case 2: imageSlideViewHolder.bindImageSlide("http://jiit.ac.in/sites/default/files/3_0.jpg"); break;
            case 3: imageSlideViewHolder.bindImageSlide("http://jiit.ac.in/sites/default/files/5_0.jpg"); break;
        }
    }
}
