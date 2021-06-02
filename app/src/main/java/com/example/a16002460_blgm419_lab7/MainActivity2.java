package com.example.a16002460_blgm419_lab7;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {
    ListView list;
    ArrayList<String> Arr = new ArrayList<>();
    ArrayAdapter<String> adp;
    DB_SQLite my_db;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        
        list = findViewById(R.id.Listeleme);
        my_db = new DB_SQLite(this);
        ReadDB();
        adp = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,Arr);
        list.setAdapter(adp);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedw = Arr.get(position);

                String[] result = selectedw.split(" ");

                SQLiteDatabase db1 = my_db.getWritableDatabase();
                String[] aa = {result[0]};
                db1.delete("Persons", "Name=?", aa);

                Arr.remove(position);
                adp.notifyDataSetChanged();
                db1.close();


            }
        });
    }
    String[] tableinfo = {"Name", "Surname", "Phone"};
    public void ReadDB() {

        SQLiteDatabase db = my_db.getReadableDatabase();

        Cursor c = db.query("Persons", tableinfo, null, null, null, null, tableinfo[0]);

        while (c.moveToNext()) {

            Arr.add("" + c.getString(0) + " " + c.getString(1) + " " + c.getInt(2));
        }

        db.close();
    }
}