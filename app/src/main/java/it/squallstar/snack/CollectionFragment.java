package it.squallstar.snack;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import it.squallstar.snack.adapters.ArticlesAdapter;
import it.squallstar.snack.helpers.Globals;
import it.squallstar.snack.helpers.ImageSwitcher;
import it.squallstar.snack.helpers.VerticalViewPager;
import it.squallstar.snack.models.Article;
import it.squallstar.snack.models.Collection;

/**
 * Created by nicholas on 08/10/2015.
 */
public class CollectionFragment extends Fragment {
    public static String POSITION_KEY = "1";

    private Collection mCollection;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int collectionIdx = getArguments().getInt(POSITION_KEY);
        mCollection = Globals.collections.get(collectionIdx);

        View view = inflater.inflate(R.layout.fragment_collection, container, false);

        TextView txt = (TextView) view.findViewById(R.id.collection_name);
        txt.setText(mCollection.name);

        view.setBackgroundColor(Color.parseColor(mCollection.color.replace("#", "#AA")));

        VerticalViewPager articlesList = (VerticalViewPager) view.findViewById(R.id.view_pager);

        ArticlesAdapter adapter = new ArticlesAdapter(getContext(), mCollection.articles);
        articlesList.setAdapter(adapter);

        articlesList.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Article currentArticle = mCollection.articles.get(position);

                if (currentArticle.image_url != null) {
                    Globals.bgSwitcher.setImage(currentArticle.image_url);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        return view;
    }
}
