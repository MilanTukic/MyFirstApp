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
import Model.Articles;

/**
 * Created by Tukic on 4/26/2017.
 */

public class MyAdapter extends ArrayAdapter<Articles> {

    private final Context context;
    private final List<Articles> articles;



    public MyAdapter(Context context, ArrayList<Articles> values) {
        super(context, R.layout.rowlayout,values);
        this.context = context;
        this.articles = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Articles current=articles.get(position);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View View = inflater.inflate(R.layout.rowlayout, parent, false);
        TextView name = (TextView) View.findViewById(R.id.firstLine);
        ImageView image = (ImageView) View.findViewById(R.id.icon);


        name.setText(articles.get(position).getName());

        // change the icon

        if (!current.getIsActive()) {
            image.setImageResource(R.drawable.n_done);
            View.setBackgroundColor(Color.parseColor("#D8D8D8"));
        } else {
            image.setImageResource(R.drawable.done);
            View.setBackgroundColor(Color.parseColor("#6E6E6E"));
        }
        return View;
    }
}
