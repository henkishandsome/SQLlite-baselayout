package com.example.app1210;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends BaseActivity{
    private static final String TAG = "MainActivity";
    private TextView tv_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: 执行");
        if (null!=savedInstanceState) {
            //Log.d(TAG, "onCreate"+savedInstanceState.getString("Item3"));
            Log.d(TAG, savedInstanceState.getString("Item3"));
        }
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_item:
                Toast.makeText(MainActivity.this,"111",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this,SecondActivity.class));
                break;
            case R.id.remove_item:
                Toast.makeText(MainActivity.this,"222",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this,SQLiteActivity.class));
                break;
            case R.id.update_item:
                Toast.makeText(MainActivity.this,"333",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this,LitePalActivity.class));
                break;
            case R.id.call_item:
                startActivity(new Intent(MainActivity.this,CallActivity.class));
            default:
                break;
        }
        return true;
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        String Item3="4444";
        savedInstanceState.putString("Item3",Item3);
        Log.d(TAG, Item3);
    }

//    @Override
//    public   void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//        String Item3="4444";
//        savedInstanceState.putString("Item3",Item3);
//    }
}