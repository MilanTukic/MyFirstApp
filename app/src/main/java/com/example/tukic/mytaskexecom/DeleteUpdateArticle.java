package com.example.tukic.mytaskexecom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import DB.DAO;
import Model.Article;
import Model.Articles;

public class DeleteUpdateArticle extends AppCompatActivity {
    private DAO datasource;
    Article article;
    EditText name;
    EditText price;
    CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_update_article);


        checkBox=(CheckBox)findViewById(R.id.cBox);
        name=(EditText) findViewById(R.id.DeleteUpdate);




        Intent intent = getIntent();
        long articlesID = intent.getLongExtra("ID",0);
        datasource=new DAO(this);
        datasource.open();
        article = datasource.getArticle(articlesID);


///*
//        long articeID = intent.getLongExtra("ID",0);
//
//        article = datasource.getArticle(articeID);
//        name.setText(article.getName());
//        price.setText(article.getPrice());
//        checkBox.setChecked(article.getIsDone());
//*/

        name.setText(article.getName());
        checkBox.setChecked(article.getIsDone());


        Button button=(Button) findViewById(R.id.btnDeleteArticle);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                datasource.deleteArticle(article);
                Intent i = new Intent(DeleteUpdateArticle.this,ArticleActivity.class);
                startActivity(i);
            }
        });




        Button update=(Button) findViewById(R.id.btnUpdateArticle);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!name.getText().toString().equals("")) {
                    article.setName(name.getText().toString());
                    article.setDone(checkBox.isChecked());

                    datasource.updateArticle(article);

                    Intent intentB = new Intent(DeleteUpdateArticle.this, ArticleActivity.class);
                    startActivity(intentB);

                }else{
                    Toast.makeText(DeleteUpdateArticle.this, "Polje Name ne sme biti prazno", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
