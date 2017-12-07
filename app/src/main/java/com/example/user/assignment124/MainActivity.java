package com.example.user.assignment124;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //creating mDataBaseHelper object to access DataBaseHelper class
    public DataBaseHelper mDataBaseHelper;

    //creating object of edittext to store the data
    EditText sname,sphone,sdob;

    //creating list object to populate the data in listview
    public ListView mList;

    //creating arraylist to store the data
    public ArrayList<Employee> mArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setting reference to mlist with layout listview
        mList = findViewById(R.id.list);

        //initializing the arraylist
        mArrayList = new ArrayList<>();

        //show method is to show the data in listview by setting custom adapter to it, defined below
        show();

    }


    //method to add option menu in the mainactivity
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        //inflating the menu by creating Menuinflater object
        MenuInflater inflater = getMenuInflater();

        //setting reference to inflater with menu layout file
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    //this method is called when any item of option menu is selected
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        //this method is to add data in list and populate it on listview , defined below
        addDatainList();
        return true;
    }

    //creating object of View to show the dialog box
    public View view;

    //method to add data in list and saving it into database
    @SuppressLint("ResourceType")
    private void addDatainList()
    {

        //inflating dialog box
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();

        //setting reference to view with dialog layout file
        view = inflater.inflate(R.layout.dialog,null);

        builder.setView(view);

        //when save button is clicked this method is called to save the data in database
        builder.setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {


                //setting refernce to edittext object with layout edittext
                 sname = (EditText) view.findViewById(R.id.name);
                 sphone = (EditText) view.findViewById(R.id.phone);
                 sdob = (EditText) view.findViewById(R.id.birth);

                 //getting data from edittext and storing data of edittext into string object
                String mname =sname.getText().toString();
                String mphone =sphone.getText().toString();
                String mdob = sdob.getText().toString();

                //initialising databasehelper object
                mDataBaseHelper = new DataBaseHelper(MainActivity.this,Constants.DATABASE_NAME,null,Constants.DATABASE_VERSION);


                //checking if all the informations filled correctly
                if (!mname.isEmpty() && !mphone.isEmpty() && !mdob.isEmpty())
                {
                        ContentValues contentValues = new ContentValues();
                        contentValues.put(Constants.NAME, mname);
                        contentValues.put(Constants.PHONE_NUMBER, mphone);
                        contentValues.put(Constants.DATE_OF_BIRTH, mdob);

                        //adding data into database
                        mDataBaseHelper.insertEmployee(contentValues);


                    //getting data from database and storing it into arraylist
                    mArrayList=mDataBaseHelper.getAllEmployee();

                    //dismissing dialog box
                    dialogInterface.dismiss();
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Please fill details correctly",Toast.LENGTH_LONG).show();
                }

                //calling show method to show the saved data in listview
                show();

            }
        });
        //when cancel button is getting clicked this method is getting called
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                //dismissing dialog box when calcel button is getting clicked
                dialogInterface.dismiss();
            }
        });

        builder.show();
    }

    //show method to show the data in listview
    public void show()
    {
        //creating customadapter object and passing array to customadapter class
        CustomAdapter customAdapter = new CustomAdapter(MainActivity.this,mArrayList);

        //setting adapter to arraylist
        mList.setAdapter(customAdapter);

    }

}
//end of class
