package com.example.takenote;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.PointerIcon;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.takenote.Database.Database;

import java.util.Date;

public class AddNote extends AppCompatActivity {

    Toolbar toolbar;
    MultiAutoCompleteTextView tv;
    EditText Title;
    TextView res;
    Database database;
    DateFormat dateFormat;
    Button work,entermant,life,family;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        getSupportActionBar().hide();

        work = findViewById(R.id.Add_btn_work);
        life = findViewById(R.id.Add_btn_life);
        entermant = findViewById(R.id.Add_btn_entermant);
        family = findViewById(R.id.Add_btn_family);
        res = findViewById(R.id.Add_result);

        work.setOnClickListener(view -> res.setText("work"));
        life.setOnClickListener(view -> res.setText("life"));
        entermant.setOnClickListener(view -> res.setText("entermant"));
        family.setOnClickListener(view -> res.setText("family"));

        dateFormat = new SimpleDateFormat("MMMM d YYYY");
        toolbar = findViewById(R.id.Add_toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId()==R.id.menu_add){
                    if(!Title.getText().toString().equals("")&&!tv.getText().toString().equals("")&&!res.getText().equals("")){
                        Log.d("TAG", new Date().toString());
                        System.out.println("it is insert "+database.Insert(new Note(Title.getText().toString(),res.getText().toString(),new Date().toString(),tv.getText().toString())));
                        Toast.makeText(getBaseContext(), "Added", Toast.LENGTH_SHORT).show();
                        finish();
                    }else {
                        System.out.println("it is fuck");
                    }
                }
                return true;
            }
        });
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tv = findViewById(R.id.Add_multy);
        database = new Database(this);
        Title = findViewById(R.id.Add_ed_title);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add,menu);
        return true;
    }
}