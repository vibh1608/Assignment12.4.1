package com.example.user.assignment124;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by user on 04-12-2017.
 */

//customadapter class which is extending baseadapter class
public class CustomAdapter extends BaseAdapter {

    public Context mContext;
    public ArrayList<Employee> mList;
    public LayoutInflater mLayoutinflator;

    public  CustomAdapter(Context MainActivityClass, ArrayList<Employee> employees)
    {
        //initialising context with mainactivity class
        mContext=MainActivityClass;

        //initialising mlist with employees arraylist
        mList=employees;

        //getting layout inflator service enabled
        mLayoutinflator = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return (long) i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        //setting refernce to view object with row layout
        view = mLayoutinflator.inflate(R.layout.row, viewGroup ,false);

        //storing name in textview
        TextView name = view.findViewById(R.id.name_tv);
        name.setText("Name : "+mList.get(i).getmName());

        //storing phone detail in textview
        TextView phone = view.findViewById(R.id.phone_tv);
        phone.setText("Phone Number : "+mList.get(i).getmPhone());

        //storing dateofbirth detail in textview
        TextView dateofbirth = view.findViewById(R.id.birth_tv);
        dateofbirth.setText("Date of Birth : "+mList.get(i).getmDOB());

        return view;
    }
}
