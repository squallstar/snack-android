package it.squallstar.snack;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.antonyt.infiniteviewpager.InfiniteViewPager;

import java.util.ArrayList;

import it.squallstar.snack.adapters.CollectionsAdapter;
import it.squallstar.snack.api.Core;
import it.squallstar.snack.helpers.Globals;
import it.squallstar.snack.models.Article;
import it.squallstar.snack.models.Collection;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        Core.getApiClient().getArticles(new Callback<ArrayList<Article>>() {
            @Override
            public void success(ArrayList<Article> articles, Response response) {
                ArrayList<Collection> collections = new ArrayList<Collection>();

                outer:
                for (Article article : articles) {
                    for (Collection collection : collections) {
                        if (collection.name.equals(article.collection)) {
                            collection.articles.add(article);
                            continue outer;
                        }
                    }

                    Collection collection = new Collection();
                    collection.name = article.collection;
                    collection.color = article.color;
                    collection.articles = new ArrayList<Article>();

                    collection.articles.add(article);

                    collections.add(collection);
                }

                Globals.collections = collections;

                showView();
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    private void showView () {
        setContentView(R.layout.activity_main);

        CollectionsAdapter adapter = new CollectionsAdapter(getSupportFragmentManager());

        InfiniteViewPager pager = (InfiniteViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);

    }

}
