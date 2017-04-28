package DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Tukic on 4/26/2017.
 */

public class Database extends SQLiteOpenHelper {

    public static final String TABLE_TASK = "task";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_IS_ACTIVE = "isActive";



    public static final String TABLE_ARTICLE = "article";
    public static final String COL_ID = "_id";
    public static final String COL_NAME = "name";
    public static final String COL_PRICE = "price";
    public static final String COL_IS_DONE = "isDone";
    public static final String COL_TASK_ID = "task_id";




    private static final String DATABASE_NAME = "task1.db";
    private static final int DATABASE_VERSION = 145;

    private static final String DATABASE_CREATE = "create table " + TABLE_TASK + "( " + COLUMN_ID + " integer primary key autoincrement, " + COLUMN_NAME + " text not null,"
            + COLUMN_IS_ACTIVE + " boolean not null default 0);";



    private static final String DATABASE_CREATE_ARTICLE = "create table " + TABLE_ARTICLE + "( " + COL_ID + " integer primary key autoincrement, "
            + COL_NAME + " text not null,"
            + COL_PRICE + " integer not null,"
            + COL_IS_DONE + " boolean not null default 0);"
            + COL_TASK_ID + "integer not null" +
            "FOREIGN KEY(" + COL_TASK_ID + ") REFERENCES " + TABLE_TASK + "(" + COLUMN_ID + ") );";



    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(DATABASE_CREATE);
        db.execSQL(DATABASE_CREATE_ARTICLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.w(Database.class.getName(),
                "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASK);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ARTICLE);
        onCreate(db);
    }

}
