package com.example.app1210;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.app1210.bean.Book;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.List;

public class LitePalActivity extends AppCompatActivity {
private Button btn_create_database2,add_data2,delete_data2,update_data2,query_data2;
    private static final String TAG = "LitePalActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lite_pal);
        btn_create_database2=findViewById(R.id.btn_create_database2);
        add_data2=findViewById(R.id.add_data2);
        delete_data2=findViewById(R.id.delete_data2);
        update_data2=findViewById(R.id.update_data2);
        query_data2=findViewById(R.id.query_data2);

        btn_create_database2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Connector.getDatabase();
            }
        });

        add_data2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();
                book.setName("The Da Vinci Code");
                book.setAuthor("xhf");
                book.setPages(345);
                book.setPrice(88.88);
                book.save();
            }
        });

        update_data2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book=new Book();
                book.setPrice(77.77);
                book.setPages(543);
                book.updateAll("name = ? and author = ?","The Da Vinci Code","xhf");
            }
        });

        delete_data2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataSupport.deleteAll(Book.class,"price<?","15");
            }
        });

        query_data2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Book> books=DataSupport.findAll(Book.class);
                for (Book book: books) {
                    Log.d(TAG, "book name is " + book.getName());
                    Log.d(TAG, "book author is " + book.getAuthor());
                    Log.d(TAG, "book pages is " + book.getPages());
                    Log.d(TAG, "book price is " + book.getPrice());
                }
            }
        });
    }
}