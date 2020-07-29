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

public class hodoptions extends AppCompatActivity {
    Button btn1,btn2;
    SQLiteDatabase db;
    TextView tv,tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hodoptions);
        btn1=(Button)findViewById(R.id.btnstats);
        btn2=(Button)findViewById(R.id.btndetails);
        tv=(TextView)findViewById(R.id.tv);
        tv1=(TextView)findViewById(R.id.tvdep);
        Intent i=getIntent();
        final int id=i.getIntExtra("fac_id",0);

        String name=Integer.toString(id);
        tv.setText("Welcome "+name);

        db = openOrCreateDatabase("campusDB", Context.MODE_PRIVATE, null);
        Cursor c=db.rawQuery("SELECT dept from faculty where fac_id='"+id+"' ",null);
        c.moveToFirst();
        final String dept=c.getString(0);
        tv1.setText("Department: "+dept);
       // Toast.makeText(getApplicationContext(),"The dept is "+dept,Toast.LENGTH_LONG).show();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(hodoptions.this,csepd.class);
                i.putExtra("dep_key",dept);
                startActivity(i);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(hodoptions.this,hod.class);
                intent.putExtra("fac_id",id);
                startActivity(intent);
            }
        });
    }
}
