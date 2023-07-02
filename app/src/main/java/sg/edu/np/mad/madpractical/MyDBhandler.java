package sg.edu.np.mad.madpractical;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

public class MyDBhandler extends SQLiteOpenHelper {
    String title;
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "User3.db";
    public static final String TABLE_USERS = "users";
    public static final String COLUMN_USERNAME = "Username";
    public static final String COLUMN_DESCRIPTION = "Description";

    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_FOLLOWED = "FollowStatus";

    public MyDBhandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "(" + COLUMN_USERNAME + " TEXT," + COLUMN_DESCRIPTION + " TEXT," + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_FOLLOWED + " BOOLEAN" + ")";
        Log.i(title, CREATE_USERS_TABLE);
        db.execSQL(CREATE_USERS_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
        db.close();
    }

    public void addUser(User userData){ //UserData is a class, an object with all the parameters
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, userData.getName());
        values.put(COLUMN_DESCRIPTION, userData.getDescription());
        values.put(COLUMN_ID, userData.getId());
        values.put(COLUMN_FOLLOWED, userData.isFollowed());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_USERS, null, values);
        Log.i(title, "Inserted/Created User" + values.toString());
        db.close();
    }

    public User findUser(int id){
        String query = "SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_ID + " = \"" + id + "\"";
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        User user = new User();

        if (cursor.moveToFirst()){
            user.setName(cursor.getString(1));
            user.setDescription(cursor.getString(2));
            user.setId(cursor.getInt(3));
            user.setFollowed(cursor.getWantsAllOnMoveCalls());
            cursor.close();
        }
        else{
            user = null;
        }

        db.close();
        return user;
    }


    public ArrayList<User> getUsers(){
        ArrayList<User> userDBList = new ArrayList<User>();
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USERS, null);
        while (cursor.moveToNext()){
            Log.i(title, "getData");

            User user = new User();
            user.name = cursor.getString(0);
            user.description = cursor.getString(1);
            user.id = cursor.getInt(2);
            user.followed = Boolean.parseBoolean(cursor.getString(3));
            userDBList.add(user);
        }

        //return userDBList;

        return userDBList;
    }

    public int rowCount()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USERS, null);
        return cursor.getCount();
    }

    public void updateUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("UPDATE User SET Followed = \""+ user.followed +"\" " +  "WHERE id = \""+ user.id +"\"");
        db.close();
    }
}
