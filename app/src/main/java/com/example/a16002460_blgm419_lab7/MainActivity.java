package com.example.a16002460_blgm419_lab7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button bEkle, bListele;
    EditText eAd,eSoyad,eTel;
    DB_SQLite my_db = new DB_SQLite(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bEkle = findViewById(R.id.button);
        bListele = findViewById(R.id.button2);
        eAd = findViewById(R.id.editTextTextPersonName);
        eSoyad = findViewById(R.id.editTextTextPersonName2);
        eTel = findViewById(R.id.editTextTextPersonName3);

        bListele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                startActivity(intent);
            }
        });

        bEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Ad,Soyad;
                int Tel;
                Ad = eAd.getText().toString();
                Soyad = eSoyad.getText().toString();
                Tel = Integer.parseInt(eTel.getText()+"");
                SaveDB("Persons", Ad,Soyad,Tel);
            }
        });
    }
    protected void SaveDB(String Table,String AD,String Soyad,int phone){

        SQLiteDatabase db=my_db.getWritableDatabase();

        ContentValues cv1=new ContentValues();

        cv1.put("Name",AD);
        cv1.put("Surname",Soyad);
        cv1.put("Phone",phone);

        db.insertOrThrow(Table,null,cv1);
        db.close();


        Toast.makeText(this, "Database Ekleme Başarılı.", Toast.LENGTH_SHORT).show();

    }
}