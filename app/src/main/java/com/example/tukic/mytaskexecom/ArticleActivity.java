package com.example.tukic.mytaskexecom;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import Adapter.MyAdapter;
import Adapter.MyAdapterArticle;
import DB.DAO;
import Model.Article;
import Model.Articles;

public class ArticleActivity extends AppCompatActivity {
    private DAO datasource;
    Articles articles;
    Article article;
    EditText name;
    EditText price;
    CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);








        checkBox=(CheckBox)findViewById(R.id.cBoxAc);
        name=(EditText) findViewById(R.id.nameUpdate);




        Intent intent = getIntent();
        long articlesID = intent.getLongExtra("ID",0);
        datasource=new DAO(this);
        datasource.open();
        articles = datasource.getArticles(articlesID);


///*
//        long articeID = intent.getLongExtra("ID",0);
//
//        article = datasource.getArticle(articeID);
//        name.setText(article.getName());
//        price.setText(article.getPrice());
//        checkBox.setChecked(article.getIsDone());
//*/

        name.setText(articles.getName());
        checkBox.setChecked(articles.getIsActive());


        Button button=(Button) findViewById(R.id.btnDelete);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                datasource.deleteArticles(articles);
                Intent i = new Intent(ArticleActivity.this,MainActivity.class);
                startActivity(i);
            }
        });




        Button update=(Button) findViewById(R.id.btnUpdate);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!name.getText().toString().equals("")) {
                    articles.setName(name.getText().toString());
                    articles.setActive(checkBox.isChecked());

                    datasource.updateArticles(articles);

                    Intent intentB = new Intent(ArticleActivity.this, MainActivity.class);
                    startActivity(intentB);

                }else{
                    Toast.makeText(ArticleActivity.this, "Polje Name ne sme biti prazno", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ArrayList<Article> list=datasource.getAllArticle();

        MyAdapterArticle adapter = new MyAdapterArticle(this,list);
        ListView listView= (ListView) findViewById(R.id.listViewArticle);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                                    long arg3)
            {

                Article value = (Article) adapter.getItemAtPosition(position);
                Intent intent = new Intent(getApplicationContext(),ArticleActivity.class);
                intent.putExtra("ID",value.getId());
                startActivity(intent);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab1);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ArticleActivity.this,AddArticle.class);
                startActivity(intent);
            }
        });




        ArrayList<Article> list1=datasource.getAllArticle();

        MyAdapterArticle adapter1 = new MyAdapterArticle(this,list1);
        ListView listView1= (ListView) findViewById(R.id.listViewArticle);
        listView.setAdapter(adapter1);
        adapter1.notifyDataSetChanged();

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> adapter1, View v, int position,
                                    long arg3)
            {

                Article value = (Article) adapter1.getItemAtPosition(position);
                Intent intent = new Intent(getApplicationContext(),DeleteUpdateArticle.class);
                intent.putExtra("ID",value.getId());
                startActivity(intent);
            }
        });

    }
}
