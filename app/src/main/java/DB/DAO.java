package DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import Model.Article;
import Model.Articles;

/**
 * Created by Tukic on 4/26/2017.
 */

public class DAO {

    private SQLiteDatabase database;
    private Database dbHelper;
    private String[] allColumns = {Database.COLUMN_ID, Database.COLUMN_NAME, Database.COLUMN_IS_ACTIVE};
    private String[] allCols = {Database.COL_ID, Database.COL_NAME, Database.COL_PRICE, Database.COL_IS_DONE};

    public DAO(Context context) {
        dbHelper = new Database(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Articles createArticles(String name) {
        ContentValues values = new ContentValues();
        values.put(Database.COLUMN_NAME, name);
        long insertId = database.insert(Database.TABLE_TASK, null, values);
        Cursor cursor = database.query(Database.TABLE_TASK, allColumns, Database.COLUMN_ID + " = " + insertId, null, null, null, null);
        cursor.moveToFirst();
        Articles newArticles = cursorToTask(cursor);
        cursor.close();
        return newArticles;
    }

    public Article createArticle(String name, String price) {
        ContentValues values = new ContentValues();
        values.put(Database.COL_PRICE, price);
        values.put(Database.COL_NAME, name);
        long insertId = database.insert(Database.TABLE_ARTICLE, null, values);
        Cursor cursor = database.query(Database.TABLE_ARTICLE, allCols, Database.COL_ID + " = " + insertId, null, null, null, null);
        cursor.moveToFirst();
        Article newArticle = cursorToTaskArticle(cursor);
        cursor.close();
        return newArticle;
    }

    public void deleteArticles(Articles articles) {
        long id = articles.getId();
        database.delete(Database.TABLE_TASK, Database.COLUMN_ID + " = " + id, null);
    }

    public void deleteArticle(Article article) {
        long id = article.getId();
        database.delete(Database.TABLE_ARTICLE, Database.COL_ID + " = " + id, null);
    }

    public void updateArticles(Articles articles) {
        long id = articles.getId();
        ContentValues values = new ContentValues();
        values.put(Database.COLUMN_NAME, articles.getName());
        values.put(Database.COLUMN_IS_ACTIVE, articles.getIsActive());

        database.update(Database.TABLE_TASK, values, Database.COLUMN_ID + " = " + id, null);
    }

    public void updateArticle(Article article) {
        long id = article.getId();
        ContentValues values = new ContentValues();
        values.put(Database.COL_NAME, article.getName());
        values.put(Database.COL_IS_DONE, article.getIsDone());

        database.update(Database.TABLE_ARTICLE, values, Database.COL_ID + " = " + id, null);
    }

    public Articles getArticles(long id) {
        Cursor cursor = database.query(Database.TABLE_TASK, allColumns, "_id = " + id, null, null, null, null);
        Articles articles = new Articles();
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            articles = cursorToTask(cursor);
            cursor.close();
        }
        return articles;
    }

    public Article getArticle(long id){
        Cursor cursor = database.query(Database.TABLE_ARTICLE, allCols, "_id = " + id, null, null, null, null);
        Article article = new Article();
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            article = cursorToTaskArticle(cursor);
            cursor.close();
        }
        return article;
    }



    public ArrayList<Articles> getAllArticles() {
        ArrayList<Articles> articles = new ArrayList<Articles>();
        Cursor cursor = database.query(Database.TABLE_TASK, allColumns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Articles articles1 = cursorToTask(cursor);
            articles.add(articles1);
            cursor.moveToNext();
        }
        cursor.close();
        return articles;
    }

    public ArrayList<Article> getAllArticle(){
        ArrayList<Article> article = new ArrayList<Article>();
        Cursor cursor = database.query(Database.TABLE_ARTICLE, allCols, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Article article1 = cursorToTaskArticle(cursor);
            article.add(article1);
            cursor.moveToNext();
        }
        cursor.close();
        return article;
    }

    private Articles cursorToTask(Cursor cursor) {
        Articles articles = new Articles();
        articles.setId(cursor.getLong(0));
        articles.setName(cursor.getString(1));
        articles.setActive(cursor.getString(2).equals("1"));
        return articles;
    }

    private Article cursorToTaskArticle(Cursor cursor) {
        Article article = new Article();
        article.setId(cursor.getLong(0));
        article.setName(cursor.getString(1));
        article.setPrice(cursor.getInt(2));
        article.setDone(cursor.getString(3).equals("1"));
        return article;
    }



}
