package com.example.tukic.mytaskexecom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import DB.DAO;

public class AddArticles extends AppCompatActivity {
    private DAO datasource;
    EditText name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_articles);


        datasource=new DAO(this);
        datasource.open();

        name = (EditText) findViewById(R.id.TheName);

        Button button=(Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                if(!name.getText().toString().equals("")) {
                    String Name = name.getText().toString();
                    datasource.createArticles(Name);
                    Intent intent = new Intent(AddArticles.this, MainActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(AddArticles.this, "Polje Name ne sme biti prazno", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
