package it.squallstar.snack.helpers;

/**
 * Created by nicholas on 14/10/2015.
 */
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class ProximaNovaCondensedView extends TextView {

    public ProximaNovaCondensedView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public ProximaNovaCondensedView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ProximaNovaCondensedView(Context context) {
        super(context);
        init();
    }

    private void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(),
                "fonts/ProximaNovaCond-Light.ttf");
        setTypeface(tf);
    }
}