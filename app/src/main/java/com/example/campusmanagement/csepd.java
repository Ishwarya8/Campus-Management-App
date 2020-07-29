package com.example.campusmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class csepd extends AppCompatActivity {
    TextView tvdep,tvcts,tvtcs,tvzoho,tvacc,tvamaz,tvciti,tvgs,tvinf,tvnp;
    SQLiteDatabase db;
    int count,cnt1,cnt2,cnt3,cnt4,cnt5,cnt6,cnt7,cnt8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_csepd);
        tvdep=(TextView)findViewById(R.id.tvdep);
        tvcts=(TextView)findViewById(R.id.tvcts);
        tvtcs=(TextView)findViewById(R.id.tvtcs);
        tvzoho=(TextView)findViewById(R.id.tvzoho);
        tvacc=(TextView)findViewById(R.id.tvacc);
        tvamaz=(TextView)findViewById(R.id.tvamaz);
        tvciti=(TextView)findViewById(R.id.tvciti);
        tvgs=(TextView)findViewById(R.id.tvgs);
        tvinf=(TextView)findViewById(R.id.tvinf);
        tvnp=(TextView)findViewById(R.id.tvnp);
        db = openOrCreateDatabase("campusDB", Context.MODE_PRIVATE, null);

        Intent i=getIntent();
        String dept=i.getStringExtra("dep_key");
        tvdep.setText(dept+" Placement Details 2019-2020");
        if(dept.equals("IT")){
            Cursor c=db.rawQuery("SELECT COUNT(name) from placed_details where cmp_name='CTS' and dept='IT'",null);
            c.moveToFirst();
            count=c.getInt(0);
            String s=Integer.toString(count);
            tvcts.setText(s);

            Cursor c1=db.rawQuery("SELECT COUNT(name) from placed_details where cmp_name='TCS' and dept='IT'",null);
            c1.moveToFirst();
            cnt1=c1.getInt(0);
            String s1=Integer.toString(cnt1);
            tvtcs.setText(s1);

            Cursor c2=db.rawQuery("SELECT COUNT(name) from placed_details where cmp_name='Zoho' and dept='IT'",null);
            c2.moveToFirst();
            cnt2=c2.getInt(0);
            String s2=Integer.toString(cnt2);
            tvzoho.setText(s2);

            Cursor c3=db.rawQuery("SELECT COUNT(name) from placed_details where cmp_name='Accenture' and dept='IT'",null);
            c3.moveToFirst();
            cnt3=c3.getInt(0);
            String s3=Integer.toString(cnt3);
            tvacc.setText(s3);

            Cursor c4=db.rawQuery("SELECT COUNT(name) from placed_details where cmp_name='Amazon' and dept='IT'",null);
            c4.moveToFirst();
            cnt4=c4.getInt(0);
            String s4=Integer.toString(cnt4);
            tvamaz.setText(s4);

            Cursor c5=db.rawQuery("SELECT COUNT(name) from placed_details where (cmp_name='CitiBank' or cmp_name='Citibank') and dept='IT'",null);
            c5.moveToFirst();
            cnt5=c5.getInt(0);
            String s5=Integer.toString(cnt5);
            tvciti.setText(s5);

            Cursor c6=db.rawQuery("SELECT COUNT(name) from placed_details where cmp_name='Goldman Sachs' and dept='IT'",null);
            c6.moveToFirst();
            cnt6=c6.getInt(0);
            String s6=Integer.toString(cnt6);
            tvgs.setText(s6);

            Cursor c7=db.rawQuery("SELECT COUNT(name) from placed_details where cmp_name='Infosys' and dept='IT'",null);
            c7.moveToFirst();
            cnt7=c7.getInt(0);
            String s7=Integer.toString(cnt7);
            tvinf.setText(s7);

            Cursor c8=db.rawQuery("SELECT COUNT(name) from placed_details where cmp_name IS NULL and dept='IT'",null);
            c8.moveToFirst();
            cnt8=c8.getInt(0);
            String s8=Integer.toString(cnt8);
            tvnp.setText(s8);
        }
        else{
            Cursor c=db.rawQuery("SELECT COUNT(name) from placed_details where cmp_name='CTS' and dept='CSE'",null);
            c.moveToFirst();
            count=c.getInt(0);
            String s=Integer.toString(count);
            tvcts.setText(s);

            Cursor c1=db.rawQuery("SELECT COUNT(name) from placed_details where cmp_name='TCS' and dept='CSE'",null);
            c1.moveToFirst();
            cnt1=c1.getInt(0);
            String s1=Integer.toString(cnt1);
            tvtcs.setText(s1);

            Cursor c2=db.rawQuery("SELECT COUNT(name) from placed_details where cmp_name='Zoho' and dept='CSE'",null);
            c2.moveToFirst();
            cnt2=c2.getInt(0);
            String s2=Integer.toString(cnt2);
            tvzoho.setText(s2);

            Cursor c3=db.rawQuery("SELECT COUNT(name) from placed_details where cmp_name='Accenture' and dept='CSE'",null);
            c3.moveToFirst();
            cnt3=c3.getInt(0);
            String s3=Integer.toString(cnt3);
            tvacc.setText(s3);

            Cursor c4=db.rawQuery("SELECT COUNT(name) from placed_details where cmp_name='Amazon' and dept='CSE'",null);
            c4.moveToFirst();
            cnt4=c4.getInt(0);
            String s4=Integer.toString(cnt4);
            tvamaz.setText(s4);

            Cursor c5=db.rawQuery("SELECT COUNT(name) from placed_details where (cmp_name='CitiBank' or cmp_name='Citibank') and dept='CSE'",null);
            c5.moveToFirst();
            cnt5=c5.getInt(0);
            String s5=Integer.toString(cnt5);
            tvciti.setText(s5);

            Cursor c6=db.rawQuery("SELECT COUNT(name) from placed_details where cmp_name='Goldman Sachs' and dept='CSE'",null);
            c6.moveToFirst();
            cnt6=c6.getInt(0);
            String s6=Integer.toString(cnt6);
            tvgs.setText(s6);

            Cursor c7=db.rawQuery("SELECT COUNT(name) from placed_details where cmp_name='Infosys' and dept='CSE'",null);
            c7.moveToFirst();
            cnt7=c7.getInt(0);
            String s7=Integer.toString(cnt7);
            tvinf.setText(s7);

            Cursor c8=db.rawQuery("SELECT COUNT(name) from placed_details where cmp_name IS NULL and dept='CSE'",null);
            c8.moveToFirst();
            cnt8=c8.getInt(0);
            String s8=Integer.toString(cnt8);
            tvnp.setText(s8);
        }
    }
}
