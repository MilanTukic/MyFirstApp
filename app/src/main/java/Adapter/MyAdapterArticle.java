package Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tukic.mytaskexecom.R;

import java.util.ArrayList;
import java.util.List;

import Model.Article;

/**
 * Created by Tukic on 4/27/2017.
 */

public class MyAdapterArticle extends ArrayAdapter<Article> {
    private final Context context;
    private final List<Article> article;


    public MyAdapterArticle(Context context, ArrayList<Article> values) {
        super(context, R.layout.rowlayout_article,values);
        this.context = context;
        this.article = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Article current=article.get(position);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View View = inflater.inflate(R.layout.rowlayout_article, parent, false);
        TextView name = (TextView) View.findViewById(R.id.firstLine);
        //TextView price = (TextView) View.findViewById(R.id.secondLine);
        ImageView image = (ImageView) View.findViewById(R.id.icon);


        name.setText(article.get(position).getName());
       // price.setText(article.get(position).getPrice());

        // change the icon

        if (!current.getIsDone()) {
            image.setImageResource(R.drawable.n_done);
            View.setBackgroundColor(Color.parseColor("#D8D8D8"));
        } else {
            image.setImageResource(R.drawable.done);
            View.setBackgroundColor(Color.parseColor("#6E6E6E"));
        }
        return View;
    }
}
