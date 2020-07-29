package com.example.campusmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
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

import java.text.SimpleDateFormat;

public class details extends AppCompatActivity {
    String name,stud_past,stud_curr,d_past,d_curr;
    int regno1;
    int dcgpa,dgrade;
    SQLiteDatabase db;
    TextView tv,tvcmp,tvpkg,tvcgpa,tvpa,tvca,tv1,tv2,tv3,tvstream;
    String s;
    Button elig;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Intent i=getIntent();
        name=i.getStringExtra("name_key");
        regno1=i.getIntExtra("reg",0);
        elig=(Button)findViewById(R.id.button);
        //Toast.makeText(getApplicationContext(),"The company selected is "+name,Toast.LENGTH_LONG).show();
        db = openOrCreateDatabase("campusDB", Context.MODE_PRIVATE, null);
        tvcmp=(TextView)findViewById(R.id.tvcmp);
        tv=(TextView)findViewById(R.id.tvDate);
        tvstream=(TextView)findViewById(R.id.tvstream);
        tvpkg=(TextView)findViewById(R.id.tvpkg);
        String[] pro={"Online test","Group Discussion","HR Interview","Technical Interview","Onsite Interview"};

        tvcmp.setText(name);
        Cursor c=db.rawQuery("SELECT strftime('%d-%m-%Y',visiting_date) FROM company WHERE cmp_name='"+name+"'", null);
        c.moveToFirst();
        //s=(String)c.getString(0);
        //SimpleDateFormat d=new SimpleDateFormat("dd-mm-yyyy");
        //s=d.format(c.getString(0));
        tv.setText(c.getString(0));

        Cursor cur=db.rawQuery("SELECT package from company where cmp_name='"+name+"'",null);
        cur.moveToFirst();
        tvpkg.setText(cur.getString(0));

        tvcgpa=(TextView)findViewById(R.id.tvcgpa);
        Cursor c1=db.rawQuery("SELECT grade from company where cmp_name='"+name+"'",null);
        c1.moveToFirst();
        dgrade=c1.getInt(0);
        String s=Integer.toString(dgrade);
        tvcgpa.setText(s);

        tvpa=(TextView)findViewById(R.id.tvpa);
        tvca=(TextView)findViewById(R.id.tvca);
        Cursor c3=db.rawQuery("SELECT past,current from company where cmp_name='"+name+"'",null);
        c3.moveToFirst();
        d_past=c3.getString(0);
        d_curr=c3.getString(1);
        tvpa.setText(d_past);
        tvca.setText(d_curr);

        tv1=(TextView)findViewById(R.id.tv1);
        tv2=(TextView)findViewById(R.id.tv2);
        tv3=(TextView)findViewById(R.id.tv3);
        Cursor e=db.rawQuery("SELECT process from company where cmp_name='"+name+"'",null);
        e.moveToFirst();
        String p=e.getString(0);

        Cursor d=db.rawQuery("SELECT stream from company where cmp_name='"+name+"'",null);
        d.moveToFirst();
        String stre=d.getString(0);
        tvstream.setText(stre);

        switch(p){
            case "p1":
                tv1.setText(pro[0]);
                tv2.setText(pro[1]);
                tv3.setText(pro[2]);
                break;
            case "p3":
                tv1.setText(pro[0]);
                tv2.setText(pro[2]);
                tv3.setText(pro[3]);
                break;
            default:
                tv1.setText(pro[0]);
                tv2.setText(pro[3]);
                tv3.setText(pro[2]);
                break;
        }

        elig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor c=db.rawQuery("SELECT cgpa from student where regno='"+regno1+"'",null);
                c.moveToFirst();
                dcgpa=c.getInt(0) ;

                Cursor c2=db.rawQuery("SELECT past,current from student where regno='"+regno1+"'",null);
                c2.moveToFirst();
                stud_past=c2.getString(0);
                stud_curr=c2.getString(1);


                //Toast.makeText(getApplicationContext(),"CGPA is "+d_curr,Toast.LENGTH_LONG).show();
                if((dcgpa>=dgrade)&&(d_curr.equals("No")&&d_past.equals("No")))
                {
                    if(stud_curr.equals("No")&&stud_past.equals("No"))
                    {
                        showMessage("Good!","You are eligible");
                    }
                    else{
                        showMessage("Sorry!","You are not eligible");
                    }
                }
                else if((dcgpa>=dgrade)&&(d_curr.equals("Yes")&&d_past.equals("Yes")))
                {
                    showMessage("Good!","You are eligible");
                }
                else if((dcgpa>=dgrade)&&(d_curr.equals("No")&&d_past.equals("Yes")))
                {
                    if((stud_curr.equals("No")&&stud_past.equals("No"))||(stud_past.equals("Yes")&&stud_curr.equals("No"))) {
                        showMessage("Good!","You are eligible");
                    }
                    else {
                        showMessage("Sorry!","You are not eligible");
                    }
                }
                else if((dcgpa>=dgrade)&&(d_past.equals("No")&&d_curr.equals("Yes"))){
                    if((stud_curr.equals("No")&&stud_past.equals("No"))||(stud_past.equals("No")&&stud_curr.equals("Yes"))){
                        showMessage("Good!","You are eligble");
                    }
                    else{
                        showMessage("Sorry!","You are not eligible");
                    }
                }
                else{
                    showMessage("Sorry!","You are not eligible");
                }
            }
        });
    }
    public void showMessage(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}