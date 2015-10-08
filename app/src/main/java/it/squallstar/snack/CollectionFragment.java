package it.squallstar.snack;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import it.squallstar.snack.adapters.ArticlesAdapter;
import it.squallstar.snack.helpers.Globals;
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

        view.setBackgroundColor(Color.parseColor(mCollection.color));

        ListView articlesList = (ListView) view.findViewById(R.id.articles_list);

        ArticlesAdapter adapter = new ArticlesAdapter(getContext(), mCollection.articles);
        articlesList.setAdapter(adapter);

        return view;
    }
}
