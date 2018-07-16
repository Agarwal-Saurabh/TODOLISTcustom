package com.example.saurabh.todolistcustom.Table;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.Selection;

/**
 * Created by Saurabh on 6/17/2017.
 */

public class TodoTable {

    public static final String TableName = "todotable";
    public static final String ID = "id";
    public static final String TASK = "task";
    public static final String TIME = "time";
    public static final String DATE = "date";

    private static final String CreateQuery ="CREATE TABLE `todotable` (\n" +
            "\t`id`\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,\n" +
            "\t`task`\tTEXT NOT NULL,\n" +
            "\t`date`\tTEXT NOT NULL,\n" +
            "\t`time`\tTEXT NOT NULL\n" +
            ");";
    public static void createTable(SQLiteDatabase db)
    {
        db.execSQL(CreateQuery);
    }

    public static void updateTable(SQLiteDatabase db)
    {
        String DeleteQuery = "drop table if exist" + TableName;
        db.execSQL(DeleteQuery);
    }

    public static long insert(SQLiteDatabase db , ContentValues cv)
    {
        return db.insert(TableName,null,cv);
    }
    public static Cursor select(SQLiteDatabase db, String[] columns, String selection)
    {
        return db.query(TableName,columns,selection,null,null,null,null);
    }

    public static int delete(SQLiteDatabase db, String selection)
    {
        return db.delete(TableName,selection,null);
    }

    public static int update(SQLiteDatabase db,ContentValues cv,String selection)
    {
        return db.update(TableName,cv,selection,null);
    }
}
