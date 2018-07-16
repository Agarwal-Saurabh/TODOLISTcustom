package com.example.saurabh.todolistcustom.Activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.saurabh.todolistcustom.R;
import com.example.saurabh.todolistcustom.Table.TodoTable;
import com.example.saurabh.todolistcustom.adapter.MyArrayAdapter;
import com.example.saurabh.todolistcustom.database.MySqliteOpenHelper;
import com.example.saurabh.todolistcustom.pojo.Pojo;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

     Button btn;
    EditText task, date, time;
    ListView listview;
    ArrayList<Pojo> arrayList = new ArrayList<>();
    ArrayList<Integer> idarrayList = new ArrayList<>();
    private MyArrayAdapter arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        arrayAdapter = new MyArrayAdapter(this, R.layout.list_item, arrayList);
        listview.setAdapter(arrayAdapter);


        fetchValues();
    }

    private void fetchValues() {
        arrayList.clear();
        idarrayList.clear();

        MySqliteOpenHelper mysqlite = new MySqliteOpenHelper(this);
        SQLiteDatabase db = mysqlite.getReadableDatabase();

        Cursor cursor = TodoTable.select(db, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                Pojo Pojo = new Pojo();
                int id = cursor.getInt(0);
                Pojo.setImgres(cursor.getString(1).substring(0, 1));
                Pojo.setTask(cursor.getString(1));
                Pojo.setDate(cursor.getString(2));
                Pojo.setTime(cursor.getString(3));

                arrayList.add(Pojo);
                idarrayList.add(id);


            }
            cursor.close();
            db.close();
            arrayAdapter.notifyDataSetChanged();
        }
    }

    private void init() {

        listview = (ListView) findViewById(R.id.listview);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                 int rowId = idarrayList.get(position);
                MySqliteOpenHelper mysqLite = new MySqliteOpenHelper(MainActivity.this);
                SQLiteDatabase db = mysqLite.getReadableDatabase();

                String selection = TodoTable.ID + " = '" + rowId + "'";

               int i = TodoTable.delete(db,selection);
                if(i>0)
                {
                    Toast.makeText(MainActivity.this, "deleted", Toast.LENGTH_SHORT).show();
                }

                fetchValues();
            }
        });

        task = (EditText) findViewById(R.id.task);
        date = (EditText) findViewById(R.id.date);
        time = (EditText) findViewById(R.id.time);

        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                add();

            }
        });

    }

    private void add() {

        String enteredTask = task.getText().toString();
        String enteredTime = time.getText().toString();
        String enteredDate = date.getText().toString();

        if (enteredTask.equals("") || enteredTime.equals("") || enteredDate.equals("")) {
            Toast.makeText(this, "Enter fields", Toast.LENGTH_SHORT).show();
        } else {
            MySqliteOpenHelper mysqliteopenhelper = new MySqliteOpenHelper(this);
            SQLiteDatabase db = mysqliteopenhelper.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(TodoTable.TASK, enteredTask);
            cv.put(TodoTable.TIME, enteredTime);
            cv.put(TodoTable.DATE, enteredDate);


            long l = TodoTable.insert(db, cv);

            if (l > 0) {
                Toast.makeText(this, "added", Toast.LENGTH_SHORT).show();
            }
            db.close();
            fetchValues();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void datePicker(View view)
    {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DATE);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                month++;
                date.setText(dayOfMonth+"/"+month+"/"+year);
            }
        },year,month,day);

        datePickerDialog.show();
    }

    public void timePicker(View view)
    {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);

        long current = System.currentTimeMillis();

        calendar.setTimeInMillis(current);



        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                time.setText(hourOfDay+":"+minute);
            }
        }, hour, minute,false);
        timePickerDialog.show();
    }
}
