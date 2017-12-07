package com.example.user.assignment124;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by user on 04-12-2017.
 */

public class DataBaseHelper extends SQLiteOpenHelper {
    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {final String CREATE_TABLE = "CREATE TABLE " + Constants.TABLE_NAME + " (" +Constants.ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +Constants.NAME + " TEXT," + Constants.PHONE_NUMBER + " TEXT,"+Constants.DATE_OF_BIRTH + " TEXT)";

    sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME);
        onCreate(sqLiteDatabase);

    }

    //this method is to getting the number of rows already present in the database
    public int getrowcount()
    {
        int rowcount=0;
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.query(false,Constants.TABLE_NAME,null,null,null,null,null,null,null);
        if(cursor!=null)
        {
            cursor.moveToFirst();
            rowcount=cursor.getCount();
        }
        return rowcount;
    }

    //method to insert details in database
    public void insertEmployee(ContentValues contentValues)
    {
        SQLiteDatabase database = this.getWritableDatabase();
        try
        {
             database.insert(Constants.TABLE_NAME,null,contentValues);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    //method to retrive all details in arraylist and returning it to mainactivity class marraylist object
    public ArrayList<Employee> getAllEmployee()
    {
        //initialising arraylist
        ArrayList<Employee> arrayList = new ArrayList<>();

        //creating databse object
        SQLiteDatabase database = this.getReadableDatabase();

        //query to get all data from databse
        String query = "SELECT * FROM "+Constants.TABLE_NAME;

        //storing all data in cursor object
        Cursor cursor = database.rawQuery(query,null);
            if(cursor!=null)
            {
                cursor.moveToFirst();

                //from this do-while loop getting all data from cursor object and storing it into arraylist
                do {
                    Employee employee = new Employee();

                    employee.setmId(cursor.getInt(cursor.getColumnIndex(Constants.ID)));
                    employee.setmName(cursor.getString(cursor.getColumnIndex(Constants.NAME)));
                    employee.setmPhone(cursor.getString(cursor.getColumnIndex(Constants.PHONE_NUMBER)));
                    employee.setmDOB(cursor.getString(cursor.getColumnIndex(Constants.DATE_OF_BIRTH)));

                    arrayList.add(employee);

                }while (cursor.moveToNext());
            }

        //returning arraylist
        return  arrayList;
    }
}
//end of class
