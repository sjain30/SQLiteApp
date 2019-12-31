package com.sajal.sqliteapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb= new DatabaseHelper(this);

        final EditText name, surname, marks,id;
        Button button,button2,button3;
        id=findViewById(R.id.editText5);
        name=findViewById(R.id.editText);
        surname=findViewById(R.id.editText2);
        marks=findViewById(R.id.editText3);
        button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean res= mydb.insetData(name.getText().toString(),surname.getText().toString(),marks.getText().toString());
                if (res==true)
                    Toast.makeText(MainActivity.this, "Data added successfully", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Data not added", Toast.LENGTH_SHORT).show();
            }
        });
        button2=findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = mydb.viewAllData();
                if (res.getCount()==0){
                    ShowMessage("Error", "No Data Found");
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("ID :" + res.getString(0) + "\n");
                    buffer.append("Name :" + res.getString(1) + "\n");
                    buffer.append("Surname :" + res.getString(2) + "\n");
                    buffer.append("Marks :" + res.getString(3) + "\n\n");
                }
                ShowMessage("Data", buffer.toString());
            }
        });
        button3=findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean res = mydb.updateData(id.getText().toString(),name.getText().toString(),surname.getText().toString(),marks.getText().toString());
                if (res==true)
                    Toast.makeText(MainActivity.this, "Data updated successfully", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Data updation unsuccessfull", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void ShowMessage(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
