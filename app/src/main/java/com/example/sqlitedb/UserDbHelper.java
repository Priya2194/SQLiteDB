package com.example.sqlitedb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class UserDbHelper extends SQLiteOpenHelper
{

    private static final String DATABASE_NAME = "Priya.DB";
    private static final int DATABASE_VERSION = 1;
    private static final String CREATE_QUERY =
            "CREATE TABLE "+ UserContract.NewUserInfo.TABLE_NAME+"("+ UserContract.NewUserInfo.USER_NAME+" TEXT,"+
                    UserContract.NewUserInfo.USER_MOB+" TEXT,"+ UserContract.NewUserInfo.USER_EMAIl+" TEXT);";

    public UserDbHelper(@Nullable Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.e("DATABASE OPERATION", "Database Created / Opened...");

    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(CREATE_QUERY);
        Log.e("DATABASE OPERATION", "Table Created");
    }


    public void addInformations(String name, String mob, String email, SQLiteDatabase db){
        ContentValues contentValues = new ContentValues();
        contentValues.put(UserContract.NewUserInfo.USER_NAME, name);
        contentValues.put(UserContract.NewUserInfo.USER_MOB, mob);
        contentValues.put(UserContract.NewUserInfo.USER_EMAIl, email);
        db.insert(UserContract.NewUserInfo.TABLE_NAME, null, contentValues);
        Log.e("DATABASE OPERATION", "One row inserted...");
    }

    public Cursor getInformations(SQLiteDatabase db){
        Cursor cursor;
        String projections[] = {UserContract.NewUserInfo.USER_NAME, UserContract.NewUserInfo.USER_MOB, UserContract.NewUserInfo.USER_EMAIl};
        cursor = db.query(UserContract.NewUserInfo.TABLE_NAME, projections, null, null, null, null, null);
        return cursor;
    }

    public Cursor getContact(String user_name, SQLiteDatabase sqLiteDatabase){
        String[] projections = {UserContract.NewUserInfo.USER_MOB, UserContract.NewUserInfo.USER_EMAIl};
        String selection = UserContract.NewUserInfo.USER_NAME+" LIKE ?";
        String[] selection_args = {user_name};
        Cursor cursor = sqLiteDatabase.query(UserContract.NewUserInfo.TABLE_NAME, projections, selection, selection_args, null, null, null);
        return cursor;
    }

    public void deleteInformations(String user_name, SQLiteDatabase sqLiteDatabase){
        String selection = UserContract.NewUserInfo.USER_NAME+" LIKE ?";
        String[] selection_args = {user_name};
        sqLiteDatabase.delete(UserContract.NewUserInfo.TABLE_NAME, selection, selection_args);
    }

    public int updateInformations(String old_name, String new_name, String new_mobile, String new_email, SQLiteDatabase sqLiteDatabase){
        ContentValues contentValues = new ContentValues();
        contentValues.put(UserContract.NewUserInfo.USER_NAME, new_name);
        contentValues.put(UserContract.NewUserInfo.USER_MOB, new_mobile);
        contentValues.put(UserContract.NewUserInfo.USER_EMAIl, new_email);
        String selection = UserContract.NewUserInfo.USER_NAME+" LIKE ?";
        String[] selection_args = {old_name};
        int count = sqLiteDatabase.update(UserContract.NewUserInfo.TABLE_NAME, contentValues, selection, selection_args);
        return count;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {

    }
}
