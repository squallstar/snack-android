package it.squallstar.snack.helpers;

import android.content.Context;
import android.graphics.Point;
import android.media.Image;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import it.squallstar.snack.R;

/**
 * Created by nicholas on 09/10/2015.
 */
public class ImageSwitcher {
    private Context mContext;
    private ViewSwitcher mView;
    private ImageView mFirstImageView;
    private ImageView mSecondImageView;
    private ImageView currentImageView;

    public ImageSwitcher (Context ctx, ViewSwitcher view) {
        mView = view;
        mContext = ctx;

        mFirstImageView = (ImageView) mView.findViewById(R.id.first_image);
        mSecondImageView = (ImageView) mView.findViewById(R.id.second_image);
    }

    public void setImage (String url) {
        currentImageView = mView.getDisplayedChild() == 0 ? mSecondImageView : mFirstImageView;

        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        Picasso
                .with(mContext)
                .load(url)
                .transform(new ScaleToFitWidthHeightTransform(size.x, false))
                .into(currentImageView, new Callback() {

                    @Override
                    public void onSuccess() {
                        if (mView.getDisplayedChild() == 0) {
                            mView.showNext();
                        } else {
                            mView.showPrevious();
                        }
                    }

                    @Override
                    public void onError() {
                    }
                });
    }
}
