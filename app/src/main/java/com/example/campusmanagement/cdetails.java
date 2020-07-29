package com.example.campusmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class cdetails extends AppCompatActivity {
    Button cts;
    Button tcs;
    Button zoho;
    Button accenture;
    TextView tv;
    Button amazon;
    Button gs;
    Button citi;
    Button info;
    int regno;
    SQLiteDatabase db;
    String s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cdetails);
        cts=(Button)findViewById(R.id.btncts);
        tcs=(Button)findViewById(R.id.btntcs);
        zoho=(Button)findViewById(R.id.btnzoho);
        accenture=(Button)findViewById(R.id.btnacc);
        amazon=(Button)findViewById(R.id.btnamaz);
        gs=(Button)findViewById(R.id.btngs);
        citi=(Button)findViewById(R.id.btnciti);
        info=(Button)findViewById(R.id.btninfo);
        tv=(TextView)findViewById(R.id.tvname);
        db = openOrCreateDatabase("campusDB", Context.MODE_PRIVATE, null);
        Intent i=getIntent();
        regno=i.getIntExtra("fac_key",0);
        //  Toast.makeText(getApplicationContext(),"Reg no is "+regno,Toast.LENGTH_LONG).show();
       // Cursor c=db.rawQuery("select name from student where regno='"+regno+"'",null);
        //c.moveToFirst();
        //s=c.getString(0);
        // Toast.makeText(getApplicationContext(),"Name: "+s,Toast.LENGTH_LONG).show();
        tv.setText("Welcome "+regno);
        cts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(cdetails.this,comp_info.class);
                i.putExtra("name_key","CTS");
            //    i.putExtra("reg",regno);
                startActivity(i);
            }
        });
        tcs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(cdetails.this,comp_info.class);
                i.putExtra("name_key","TCS");
            //    i.putExtra("reg",regno);
                startActivity(i);
            }
        });
        zoho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(cdetails.this,comp_info.class);
                i.putExtra("name_key","Zoho");
            //    i.putExtra("reg",regno);
                startActivity(i);
            }
        });
        accenture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(cdetails.this,comp_info.class);
                i.putExtra("name_key","Accenture");
            //    i.putExtra("reg",regno);
                startActivity(i);
            }
        });
        citi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(cdetails.this,comp_info.class);
                i.putExtra("name_key","Citibank");
            //    i.putExtra("reg",regno);
                startActivity(i);
            }
        });
        amazon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(cdetails.this,comp_info.class);
                i.putExtra("name_key","Amazon");
            //    i.putExtra("reg",regno);
                startActivity(i);
            }
        });
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(cdetails.this,comp_info.class);
                i.putExtra("name_key","Infosys");
            //    i.putExtra("reg",regno);
                startActivity(i);
            }
        });
        gs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(cdetails.this,comp_info.class);
                i.putExtra("name_key","Goldman Sachs");
             //   i.putExtra("reg",regno);
                startActivity(i);
            }
        });

    }
}
