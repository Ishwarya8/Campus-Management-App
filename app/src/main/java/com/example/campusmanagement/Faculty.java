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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Faculty extends AppCompatActivity {
    int fac_id1;
    TextView tv;
    Button btn1,btn2,btnlog;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    SQLiteDatabase db;
    String name,s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty);
        tv=(TextView)findViewById(R.id.tvname);
        btn1=(Button)findViewById(R.id.company);
        btn2=(Button)findViewById(R.id.pd);
        btnlog=(Button)findViewById(R.id.btnlog);
        radioGroup = (RadioGroup) findViewById(R.id.rg1);
        Intent i=getIntent();
        fac_id1=i.getIntExtra("fac_id",0);
        name=Integer.toString(fac_id1);

        tv.setText("Welcome "+name);
        db = openOrCreateDatabase("campusDB", Context.MODE_PRIVATE, null);
    //    Toast.makeText(getApplicationContext(),"The fac id id "+fac_id1,Toast.LENGTH_LONG).show();
        Cursor cursor=db.rawQuery("SELECT dept from faculty where fac_id='"+fac_id1+"'",null);
        cursor.moveToFirst();
        final String dep=cursor.getString(0);
    //    Toast.makeText(getApplicationContext(),"Department is "+dep,Toast.LENGTH_LONG).show();

        btnlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Faculty.this,Login.class);
                startActivity(intent);
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                // find the radiobutton by returned id
                radioButton = (RadioButton) findViewById(selectedId);
                if(selectedId==-1)
                {
                    Toast.makeText(getApplicationContext(),"Please select a department",Toast.LENGTH_LONG).show();
                }
                else {
                    //String dept = (String)radioButton.getText().toString();
                    Intent i=new Intent(Faculty.this,cdetails.class);
                    i.putExtra("fac_key",fac_id1);
                    startActivity(i);
                }
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                // find the radiobutton by returned id
                radioButton = (RadioButton) findViewById(selectedId);
                if (selectedId == -1) {
                    Toast.makeText(getApplicationContext(), "Please select a department", Toast.LENGTH_LONG).show();
                } else {
                    String dept = (String) radioButton.getText().toString();

                    if (dept.equals("CSE") && dep.equals("CSE")) {
                        Intent intent = new Intent(Faculty.this, csepd.class);
                        intent.putExtra("dep_key", dept);
                        startActivity(intent);
                    }
                    else if (dept.equals("IT") && dep.equals("IT")) {
                        Intent j = new Intent(Faculty.this, csepd.class);
                        j.putExtra("dep_key", dept);
                        startActivity(j);
                    } else {
                        showMessage("Error!", "Cannot access");
                    }
                }
            }
        });

    }
    public void checkButton(View v)
    {
        int radioID = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioID);
     //   Toast.makeText(this,"Selected Choice: " + radioButton.getText(), Toast.LENGTH_SHORT).show();
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
