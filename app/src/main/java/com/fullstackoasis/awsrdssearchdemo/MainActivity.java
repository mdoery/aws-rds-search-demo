package com.fullstackoasis.awsrdssearchdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b = (Button)this.findViewById(R.id.btn_inspection_id);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchByInspectionId();
            }
        });
    }

    protected void searchByInspectionId() {
        int id = R.id.editText;
        super.findViewById(id);
        EditText et = findViewById(R.id.editText);
        Editable editable = et.getText();
        String s = editable.toString();
        System.out.println(s);
        MySQLAsyncTask mySQLAsyncTask = new MySQLAsyncTask();
        mySQLAsyncTask.execute(s);
    }
}
