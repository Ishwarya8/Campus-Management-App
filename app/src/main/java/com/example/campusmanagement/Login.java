package com.example.campusmanagement;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    EditText email, pass;
    Button log;
    ProgressBar p;
    SQLiteDatabase db;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    int userid;

    String a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = openOrCreateDatabase("campusDB", Context.MODE_PRIVATE, null);

    //    db.execSQL("alter table company add column stream VARCHAR");
    //    db.execSQL("update company set stream='Full Stack Developer' where cmp_name='Amazon'");
    //    db.execSQL("update company set stream='Systems Engineer Manager' where cmp_name='CTS'");
   //     db.execSQL("update company set stream='Software Tester' where cmp_name='TCS'");
    //    db.execSQL("update company set stream='Power Programmer' where cmp_name='Infosys'");
    //    db.execSQL("update company set stream='Network Engineer' where cmp_name='Zoho'");
    //    db.execSQL("update company set stream='Technical Analyst' where cmp_name='Accenture'");
    //    db.execSQL("update company set stream='Business Analyst' where cmp_name='Citibank'");
    //    db.execSQL("update company set stream='Software Engineer' where cmp_name='Goldman Sachs'");


    //    showMessage("Success","Column added");


        email = (EditText) findViewById(R.id.emailid2);
        pass = (EditText) findViewById(R.id.password2);
        log = (Button) findViewById(R.id.loginf);
        p = (ProgressBar) findViewById(R.id.progressBar2);
        p.setVisibility(View.INVISIBLE);
        radioGroup = (RadioGroup) findViewById(R.id.rg);
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mailid = email.getText().toString().trim();
                //userid=Integer.parseInt(mailid);
                String password = pass.getText().toString().trim();
                if (email.getText().toString().trim().length()==0) {
                    email.setError("Enter email");
                    //showMessage("Error", "Please Enter The Email");
                    return;
                }
                else if (pass.getText().toString().trim().length()==0) {
                    pass.setError("Enter password");
                    //showMessage("Error", "Please Enter The Password");
                    return;
                }
                // Toast.makeText(getApplicationContext(),"Reg no is "+userid,Toast.LENGTH_LONG).show();
                p.setVisibility(View.VISIBLE);
                int selectedId = radioGroup.getCheckedRadioButtonId();
                // find the radiobutton by returned id
                radioButton = (RadioButton) findViewById(selectedId);
                if(selectedId==-1)
                {
                    Toast.makeText(getApplicationContext(),"Please select a designation",Toast.LENGTH_LONG).show();
                }
                else {
                    String design = (String) radioButton.getText().toString();
                    userid=Integer.parseInt(mailid);
                    if (design.equals("Student")) {
                        Cursor c = db.rawQuery("SELECT * FROM student WHERE regno='" + userid + "' AND pwd='" + password + "'", null);
                        if (c.moveToFirst()) {
                            Intent i = new Intent(Login.this, Student.class);
                            i.putExtra("reg_key",userid);
                            startActivity(i);
                            clearText();
                        } else {
                            showMessage("Error", "Invalid student details");
                            clearText();
                        }

                    } else if (design.equals("Faculty")) {
                        Cursor c1 = db.rawQuery("SELECT * FROM faculty WHERE fac_id='" + userid + "' AND pass='" + password + "' AND designation='Faculty'", null);
                        if (c1.moveToFirst()) {
                            Intent i = new Intent(Login.this, Faculty.class);
                            i.putExtra("fac_id",userid);
                            startActivity(i);
                            clearText();
                        } else {
                            showMessage("Error", "Invalid details");
                            clearText();
                        }
                    } else {
                        Cursor c2 = db.rawQuery("SELECT * FROM faculty WHERE fac_id='" + userid + "' AND pass='" + password + "' AND designation='HOD'", null);
                        if (c2.moveToFirst()) {
                            Intent i = new Intent(Login.this, hodoptions.class);
                            i.putExtra("fac_id",userid);
                            startActivity(i);
                            clearText();
                        } else {
                            showMessage("Error", "Invalid details");
                            clearText();
                        }

                    }
                    p.setVisibility(View.INVISIBLE);


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
    public void checkButton(View v)
    {
        int radioID = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioID);
    //    Toast.makeText(this,"Selected Choice: " + radioButton.getText(), Toast.LENGTH_SHORT).show();
    }
    public void clearText()
    {
        email.setText("");
        pass.setText("");
        email.requestFocus();
        radioButton.toggle();
    }
}