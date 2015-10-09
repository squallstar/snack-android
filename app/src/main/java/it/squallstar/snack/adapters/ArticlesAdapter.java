package it.squallstar.snack.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import it.squallstar.snack.CollectionFragment;
import it.squallstar.snack.R;
import it.squallstar.snack.helpers.Globals;
import it.squallstar.snack.models.Article;

/**
 * Created by nicholas on 08/10/2015.
 */
public class ArticlesAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<Article> mArticles;

    public ArticlesAdapter(Context c, ArrayList<Article> articles) {
        this.mContext = c;
        this.mArticles = articles;
    }

    public int getCount() {
        return mArticles.size();
    }

    public Article getItem(int position) {
        return mArticles.get(position);
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View item;

        Article article = getItem(position);

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            item = inflater.inflate(R.layout.list_article, parent, false);
        } else {
            item = (View) convertView;
        }

        TextView articleTitle = (TextView) item.findViewById(R.id.article_title);
        articleTitle.setText(article.title);

        TextView articleContent = (TextView) item.findViewById(R.id.article_content);
        articleContent.setText(article.content);

        articleContent.setVisibility(article.content != null && article.content.length() > 0 ? View.VISIBLE : View.GONE);

        if (article.image_url != null) {
            Globals.bgSwitcher.setImage(article.image_url);
        }

        return item;
    }
}