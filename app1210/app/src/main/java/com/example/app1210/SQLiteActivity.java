package com.example.app1210;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.app1210.Util.MyDatabaseHelper;

public class SQLiteActivity extends AppCompatActivity {
    private MyDatabaseHelper dbHelper;
    private Button create_database,add_data,update_data,delete_data,query_data;
    private static final String TAG = "SQLiteActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
        dbHelper=new MyDatabaseHelper(this,"BookStore.db",null,3);
        create_database=findViewById(R.id.btn_create_database);
        create_database.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.getWritableDatabase();
            }
        });

        add_data=findViewById(R.id.add_data);
        add_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                //开始组装第一条数据
                values.put("name","The Da Vinci Code");
                values.put("author","xhf");
                values.put("pages",123);
                values.put("price",12.22);
                db.insert("Book",null,values);
                values.clear();
                //第二条数据
                values.put("name","水水水水水");
                values.put("author","aza");
                values.put("pages",1234);
                values.put("price",12.22);
                db.insert("Book",null,values);
            }
        });

        update_data=findViewById(R.id.update_data);
        update_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("price",10.99);
                db.update("Book",values,"name = ?",new String[] {"The Da Vinci Code"});
            }
        });

        delete_data=findViewById(R.id.delete_data);
        delete_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.delete("Book","pages > ?",new String[] {"500"});
            }
        });

        query_data=findViewById(R.id.query_data);
        query_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                //查询Book表中所有数据
                Cursor cursor=db.query("Book",null,null,null,null,null,null);
                if (cursor.moveToFirst()) {
                    do {
                        //遍历Cursor对象，取出数据并打印
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                        double price = cursor.getDouble(cursor.getColumnIndex("price"));
                        Log.d(TAG, "book name is "+name);
                        Log.d(TAG, "book anthor is "+author);
                        Log.d(TAG, "book pages is "+pages);
                        Log.d(TAG, "book price is "+price);
                    }while (cursor.moveToNext());
                }
                cursor.close();
            }
        });
    }
}