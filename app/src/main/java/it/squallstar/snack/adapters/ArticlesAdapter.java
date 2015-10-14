package it.squallstar.snack.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import it.squallstar.snack.CollectionFragment;
import it.squallstar.snack.R;
import it.squallstar.snack.helpers.Dates;
import it.squallstar.snack.helpers.Globals;
import it.squallstar.snack.helpers.ScaleToFitWidthHeightTransform;
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

        item.setBackgroundColor(Color.parseColor(article.color));

        TextView articleTitle = (TextView) item.findViewById(R.id.article_title);
        TextView articleHeaderTitle = (TextView) item.findViewById(R.id.article_header_title);
        TextView articleHeaderAuthor = (TextView) item.findViewById(R.id.article_header_author);
        TextView articleHeaderDate = (TextView) item.findViewById(R.id.article_header_date);
        TextView articleLink = (TextView) item.findViewById(R.id.article_link);
        ImageView articleImage = (ImageView) item.findViewById(R.id.article_image);

        articleLink.setText(article.domain);
        articleHeaderDate.setText(Dates.getTimeAgo(article.datepublish.getTime()));
        articleHeaderAuthor.setText(article.source_title);

        TextView articleContent = (TextView) item.findViewById(R.id.article_content);
        articleContent.setText(article.content);

        if (article.image_url != null) {
            // Preview with image
            // Header: image, author, date
            // Body: title, content, link
            articleHeaderTitle.setVisibility(View.GONE);
            articleHeaderAuthor.setVisibility(View.VISIBLE);

            articleTitle.setText(article.title);
            articleTitle.setVisibility(View.VISIBLE);

            WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);

            Picasso
                    .with(mContext)
                    .load(article.image_url)
                    .transform(new ScaleToFitWidthHeightTransform(size.x, false))
                    .into(articleImage, new Callback() {

                        @Override
                        public void onSuccess() {

                        }

                        @Override
                        public void onError() {
                        }
                    });

        } else {
            // Preview without image
            // Header: color, title, date
            // Body: content, link
            articleHeaderTitle.setVisibility(View.VISIBLE);
            articleHeaderTitle.setText(article.title);

            articleTitle.setVisibility(View.GONE);
            articleHeaderAuthor.setVisibility(View.GONE);
        }

        return item;
    }
}