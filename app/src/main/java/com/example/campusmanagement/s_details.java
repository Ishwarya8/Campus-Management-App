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

import org.w3c.dom.Text;

public class s_details extends AppCompatActivity {
    TextView tvname,tvreg,tvdep,tvcgpa,tvpa,tvca;
    SQLiteDatabase db;
    int regno,cgpa;
    String s,r,cg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_details);
        tvname=(TextView)findViewById(R.id.tvname);
        tvreg=(TextView)findViewById(R.id.tvreg);
        tvdep=(TextView)findViewById(R.id.tvdep);
        tvcgpa=(TextView)findViewById(R.id.tvcgpa);
        tvpa=(TextView)findViewById(R.id.tvpa);
        tvca=(TextView)findViewById(R.id.tvca);
        db = openOrCreateDatabase("campusDB", Context.MODE_PRIVATE, null);
        Intent i=getIntent();
        regno=i.getIntExtra("reg",0);
        Cursor c=db.rawQuery("select name,dept,cgpa,past,current from student where regno='"+regno+"'",null);
        c.moveToFirst();
        s=c.getString(0);
        tvname.setText("Welcome "+s);
        r=Integer.toString(regno);
        tvreg.setText(r);
        tvdep.setText(c.getString(1));
        cgpa=c.getInt(2);
        cg=Integer.toString(cgpa);
        tvcgpa.setText(cg);
        tvpa.setText(c.getString(3));
        tvca.setText(c.getString(4));

    }
}
