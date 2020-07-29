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

public class Student extends AppCompatActivity {
    Button cts;
    Button tcs;
    Button zoho;
    Button accenture;
    TextView tv;
    Button amazon;
    Button gs;
    Button citi;
    Button info;
    Button myDetails;
    Button btn;
    int regno;
    SQLiteDatabase db;
    String s,stud_past,stud_curr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        cts=(Button)findViewById(R.id.btncts);
        tcs=(Button)findViewById(R.id.btntcs);
        zoho=(Button)findViewById(R.id.btnzoho);
        accenture=(Button)findViewById(R.id.btnacc);
        amazon=(Button)findViewById(R.id.btnamaz);
        gs=(Button)findViewById(R.id.btngs);
        citi=(Button)findViewById(R.id.btnciti);
        info=(Button)findViewById(R.id.btninfo);
        btn=(Button)findViewById(R.id.btnlog);
        myDetails=(Button)findViewById(R.id.btndetails);
        tv=(TextView)findViewById(R.id.tvname);
        db = openOrCreateDatabase("campusDB", Context.MODE_PRIVATE, null);
        Intent i=getIntent();
        regno=i.getIntExtra("reg_key",0);
      //  Toast.makeText(getApplicationContext(),"Reg no is "+regno,Toast.LENGTH_LONG).show();
        Cursor c=db.rawQuery("select name from student where regno='"+regno+"'",null);
        c.moveToFirst();
        s=c.getString(0);
       // Toast.makeText(getApplicationContext(),"Name: "+s,Toast.LENGTH_LONG).show();
        tv.setText("Welcome "+s);

        Cursor c2=db.rawQuery("SELECT past,current from student where regno='"+regno+"'",null);
        c2.moveToFirst();
        stud_past=c2.getString(0);
        stud_curr=c2.getString(1);

        if(stud_past.equals("Yes")&&stud_curr.equals("No")){
            Cursor cursor=db.rawQuery("select cmp_name from company where grade<(select cgpa from student where regno='"+regno+"')AND ((past='Yes' AND current='No')||(past='Yes' AND current='Yes'));",null);
            StringBuffer buffer=new StringBuffer();
            while(cursor.moveToNext())
            {
                buffer.append(cursor.getString(0)+"\n");

            }
            showMessage("Companies You Are Eligible For--->", buffer.toString());
        }
        if(stud_past.equals("No")&&stud_curr.equals("Yes")){
            Cursor cursor1=db.rawQuery("select cmp_name from company where grade<(select cgpa from student where regno='"+regno+"')AND ((past='No' AND current='Yes')||(past='Yes' AND current='Yes'));",null);
            StringBuffer buffer=new StringBuffer();
            while(cursor1.moveToNext())
            {
                buffer.append(cursor1.getString(0)+"\n");

            }
            showMessage("Companies You Are Eligible For--->", buffer.toString());
        }
        if(stud_past.equals("Yes")&&stud_curr.equals("Yes")){
            Cursor cursor2=db.rawQuery("select cmp_name from company where grade<(select cgpa from student where regno='"+regno+"')AND (past='Yes' AND current='Yes');",null);
            StringBuffer buffer=new StringBuffer();
            while(cursor2.moveToNext())
            {
                buffer.append(cursor2.getString(0)+"\n");

            }
            showMessage("Companies You Are Eligible For--->", buffer.toString());
        }
        if(stud_past.equals("No")&&stud_curr.equals("No")){
            Cursor cursor3=db.rawQuery("select cmp_name from company where grade<(select cgpa from student where regno='"+regno+"')AND ((past='Yes' AND current='No')||(past='Yes' AND current='Yes')||(past='No' AND current='No')||(past='No' AND current='Yes'));",null);
            StringBuffer buffer=new StringBuffer();
            while(cursor3.moveToNext())
            {
                buffer.append(cursor3.getString(0)+"\n");

            }
            showMessage("Companies You Are Eligible For--->", buffer.toString());
        }


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Student.this,Login.class);
                startActivity(intent);
            }
        });
        cts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Student.this,details.class);
                i.putExtra("name_key","CTS");
                i.putExtra("reg",regno);
                startActivity(i);
            }
        });
        tcs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Student.this,details.class);
                i.putExtra("name_key","TCS");
                i.putExtra("reg",regno);
                startActivity(i);
            }
        });
        zoho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Student.this,details.class);
                i.putExtra("name_key","Zoho");
                i.putExtra("reg",regno);
                startActivity(i);
            }
        });
        accenture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Student.this,details.class);
                i.putExtra("name_key","Accenture");
                i.putExtra("reg",regno);
                startActivity(i);
            }
        });
        citi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Student.this,details.class);
                i.putExtra("name_key","Citibank");
                i.putExtra("reg",regno);
                startActivity(i);
            }
        });
        amazon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Student.this,details.class);
                i.putExtra("name_key","Amazon");
                i.putExtra("reg",regno);
                startActivity(i);
            }
        });
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Student.this,details.class);
                i.putExtra("name_key","Infosys");
                i.putExtra("reg",regno);
                startActivity(i);
            }
        });
        gs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Student.this,details.class);
                i.putExtra("name_key","Goldman Sachs");
                i.putExtra("reg",regno);
                startActivity(i);
            }
        });
        myDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Student.this,s_details.class);
                i.putExtra("reg",regno);
                startActivity(i);
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
