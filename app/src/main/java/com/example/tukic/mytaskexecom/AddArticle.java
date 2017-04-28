package com.example.tukic.mytaskexecom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import DB.DAO;

public class AddArticle extends AppCompatActivity {
    private DAO datasource;
    EditText name;
    EditText price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_article);


        datasource=new DAO(this);
        datasource.open();

        name = (EditText) findViewById(R.id.TheNameArticle);
        price = (EditText) findViewById(R.id.ThePriceArticle);


        Button button=(Button) findViewById(R.id.buttonArticle);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                if(!name.getText().toString().equals("")) {
                    String Name = name.getText().toString();
                    String Price = price.getText().toString();
                    datasource.createArticle(Name, Price);
                    Intent intent = new Intent(AddArticle.this, ArticleActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(AddArticle.this, "Polje Name ne sme biti prazno", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
