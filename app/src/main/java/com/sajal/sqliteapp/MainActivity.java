package com.sajal.sqliteapp;

import androidx.appcompat.app.AppCompatActivity;

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

        final EditText name, surname, marks;
        Button button;
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
    }
}
