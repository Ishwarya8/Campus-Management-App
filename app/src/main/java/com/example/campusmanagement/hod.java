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

public class hod extends AppCompatActivity {
    SQLiteDatabase db;
    Button btnlog;
    TextView tvd,nam,nam1,pac,pac1,tota,plac,nplac,av,cnam,cnam1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hod);
        tvd=(TextView)findViewById(R.id.tv);
        nam=(TextView)findViewById(R.id.name);
        nam1=(TextView)findViewById(R.id.name1);
        pac=(TextView)findViewById(R.id.pack);
        pac1=(TextView)findViewById(R.id.pack1);
        tota=(TextView)findViewById(R.id.total);
        plac=(TextView)findViewById(R.id.place);
        nplac=(TextView)findViewById(R.id.nplace);
        av=(TextView)findViewById(R.id.avg);
        cnam=(TextView)findViewById(R.id.cname);
        cnam1=(TextView)findViewById(R.id.cname1);
        btnlog=(Button)findViewById(R.id.btnlog);

        btnlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(hod.this,Login.class);
                startActivity(intent);
            }
        });


        db = openOrCreateDatabase("campusDB", Context.MODE_PRIVATE, null);
        Intent i=getIntent();
        int id=i.getIntExtra("fac_id",0);
     //   Toast.makeText(getApplicationContext(),"Fac id" +id,Toast.LENGTH_LONG).show();

        Cursor c=db.rawQuery("SELECT dept from faculty where fac_id='"+id+"' ",null);
        c.moveToFirst();
        String dept=c.getString(0);

        tvd.setText(dept+" Placement Details 2019-2020");

        Cursor c1=db.rawQuery("SELECT name,cmp_name,package from placed_details where package=(SELECT MAX(package) FROM placed_details) AND dept='"+dept+"'",null);
        c1.moveToFirst();
        String n=c1.getString(0);
        nam.setText(n);
        String comp=c1.getString(1);
        cnam.setText(comp);
        int p=c1.getInt(2);
        String pa=Integer.toString(p);
        pac.setText(pa);
        Cursor c2=db.rawQuery("SELECT name,cmp_name,package from placed_details where package=(SELECT MIN(package) FROM placed_details) AND dept='"+dept+"'",null);
        c2.moveToFirst();
        String n1=c2.getString(0);
        nam1.setText(n1);
        String comp1=c2.getString(1);
        cnam1.setText(comp1);
        int p1=c2.getInt(2);
        String pa1=Integer.toString(p1);
        pac1.setText(pa1);
        Cursor c3=db.rawQuery("SELECT COUNT(name),AVG(package) from placed_details where dept='"+dept+"'",null);
        c3.moveToFirst();
        int t=c3.getInt(0);
        String t1=Integer.toString(t);
        tota.setText(t1);
        int a=c3.getInt(1);
        String a1=Integer.toString(a);
        av.setText(a1);
       Cursor c4=db.rawQuery("SELECT COUNT(name) from placed_details where dept='"+dept+"' AND cmp_id IS NULL",null);
       c4.moveToFirst();
        int npla=c4.getInt(0);
        String npla1=Integer.toString(npla);
        nplac.setText(npla1);
        int pla=t-npla;
        String pla1=Integer.toString(pla);
        plac.setText(pla1);



    }
}
