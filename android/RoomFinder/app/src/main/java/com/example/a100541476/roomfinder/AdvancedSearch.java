package com.example.a100541476.roomfinder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

/**
 * Created by 100541476 on 11/30/2017.
 */

public class AdvancedSearch extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_search);
    }

    public void search(View view){
        EditText txtIn = (EditText) findViewById(R.id.inDay);
        MainActivity.day = txtIn.getText().toString();

        txtIn = (EditText) findViewById(R.id.inTime);
        MainActivity.time = Double.parseDouble(txtIn.getText().toString());

        Intent intent = new Intent(this, QuickSearch.class);
        startActivity(intent);
    }
}
