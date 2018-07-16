package com.example.saurabh.todolistcustom.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.saurabh.todolistcustom.Table.TodoTable;

/**
 * Created by Saurabh on 6/17/2017.
 */

public class
MySqliteOpenHelper extends SQLiteOpenHelper {

    Context context;

    public MySqliteOpenHelper(Context context) {
        super(context, "todo.db", null, 1);
        this.context = context;
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        Toast.makeText(context, "onCreate", Toast.LENGTH_SHORT).show();
        TodoTable.createTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Toast.makeText(context, "Onupgrade", Toast.LENGTH_SHORT).show();
        TodoTable.updateTable(db);
    }
}
