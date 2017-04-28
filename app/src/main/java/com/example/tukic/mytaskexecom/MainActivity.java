package com.example.tukic.mytaskexecom;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import Adapter.MyAdapter;
import DB.DAO;
import Model.Articles;

public class MainActivity extends AppCompatActivity {
    private DAO datasource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Articles");
       // setSupportActionBar(toolbar);

        datasource=new DAO(this);
        datasource.open();


        ArrayList<Articles> list=datasource.getAllArticles();

        MyAdapter adapter = new MyAdapter(this,list);
        ListView listView= (ListView) findViewById(R.id.listViewMain);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                                    long arg3)
            {

                Articles value = (Articles) adapter.getItemAtPosition(position);
                Intent intent = new Intent(getApplicationContext(),ArticleActivity.class);
                intent.putExtra("ID",value.getId());
                startActivity(intent);
            }
        });



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AddArticles.class);
                startActivity(intent);
            }
        });

    }
}
