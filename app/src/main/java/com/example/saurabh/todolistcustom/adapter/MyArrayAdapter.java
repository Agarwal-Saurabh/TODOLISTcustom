package com.example.saurabh.todolistcustom.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.saurabh.todolistcustom.R;
import com.example.saurabh.todolistcustom.pojo.Pojo;

import java.util.ArrayList;

/**
 * Created by Saurabh on 6/13/2017.
 */

public class MyArrayAdapter extends ArrayAdapter {
    private Context context;
    private int layoutres;
    private ArrayList<Pojo> arraylist;
    private LayoutInflater inflator;

    public MyArrayAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<Pojo> arraylist) {
        super(context, resource, arraylist);

        this.context = context;
        layoutres = resource;
        this.arraylist = arraylist;

        inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = inflator.inflate(layoutres, null);

        TextView imageView = (TextView) view.findViewById(R.id.circle1);
        TextView taskView = (TextView) view.findViewById(R.id.task1);
        TextView dateView = (TextView) view.findViewById(R.id.date1);
        TextView timeView = (TextView) view.findViewById(R.id.time1);

        Pojo Pojo = arraylist.get(position);

        taskView.setText(Pojo.getTask());
        dateView.setText(Pojo.getDate());
        timeView.setText(Pojo.getTime());
        imageView.setText(Pojo.getImgres());

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "this is image", Toast.LENGTH_SHORT).show();
            }
        });

        Log.d("1234", "getView: "+position +"     "+Pojo);

        return view;
    }



}
