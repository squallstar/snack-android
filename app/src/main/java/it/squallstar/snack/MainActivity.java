package it.squallstar.snack;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

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
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);

        setContentView(R.layout.activity_main);

        Core.getApiClient().getArticles(300, new Callback<ArrayList<Article>>() {
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

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            if (hasFocus) {
                getWindow().getDecorView()
                        .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
            }
        }
    }

    private void showView () {
        CollectionsAdapter adapter = new CollectionsAdapter(getSupportFragmentManager());

        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);

    }

}
