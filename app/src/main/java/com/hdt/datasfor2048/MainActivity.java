package com.hdt.datasfor2048;

import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
//    private Context friendContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        try {
//            friendContext = this.createPackageContext("com.hdt.a2048",
//                    Context.CONTEXT_IGNORE_SECURITY);
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        }
        MySqlHelper mySqlHelper = new MySqlHelper(this, "db2048.db", null, 1);
        SQLiteDatabase db = mySqlHelper.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put("cards", "1111");
//        values.put("user_score", 0);
//        values.put("time",0);
//        values.put("name", "state1");
//        db.insert("gamestate", "id", values);
        Cursor cursor = db.query("gamestate", null, null, null, null, null, "user_score desc");//列名称  倒排序
        while (cursor.moveToNext()) {
            int nameIndex = cursor.getColumnIndex("name");
            int scoreIndex = cursor.getColumnIndex("user_score");
            int idIndex = cursor.getColumnIndex("id");
            int cardsIndex = cursor.getColumnIndex("cards");
            String name = cursor.getString(nameIndex);
            String cards = cursor.getString(cardsIndex);
            int score = cursor.getInt(scoreIndex);
            int id = cursor.getInt(idIndex);
            Log.e("bbb", name + " " + cards + " " + String.valueOf(score) + " " + String.valueOf(id));
        }
//        Cursor cursor = db.query("charts", null, null, null, null, null, "user_score desc");//列名称  倒排序
//        while (cursor.moveToNext()) {
//            int nameIndex = cursor.getColumnIndex("user_name");
//            int scoreIndex = cursor.getColumnIndex("user_score");
//            int idIndex = cursor.getColumnIndex("id");
//            int timeIndex = cursor.getColumnIndex("time");
//            String time = cursor.getString(timeIndex);
//            String name = cursor.getString(nameIndex);
//            int score = cursor.getInt(scoreIndex);
//            int id = cursor.getInt(idIndex);
//            Log.e("aaa",time+name+String.valueOf(score)+String.valueOf(id));
//        }
        db.close();
    }
}
