package com.example.takenote;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.takenote.Database.Database;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Adptar adptar;
    Toolbar toolbar;
    Database database;
    Button all,Work,Life,Family,Entermant;
    FloatingActionButton btn;
    ArrayList<Note>arrayList;
    public static String IntentValue="Note";
    SearchView searchView;
    Menu menuMain;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbarformain);
        all = findViewById(R.id.btn_home);
        Work = findViewById(R.id.mod_btn_work);
        Entermant = findViewById(R.id.mod_btn_entermant);
        Family = findViewById(R.id.mod_btn_family);
        Life = findViewById(R.id.mod_btn_life);
        btn = findViewById(R.id.ft_btn);
        setSupportActionBar(toolbar);

        database = new Database(this);
        arrayList=new ArrayList<>();
        arrayList = database.getAll();

        all.setOnClickListener(view -> {
            arrayList = database.getAll();
            adptar.setArrayList(arrayList);
        });
        Work.setOnClickListener(view -> {
            arrayList = database.getType("work");
            adptar.setArrayList(arrayList);
        });
        Life.setOnClickListener(view -> {
            arrayList=database.getType("life");
            adptar.setArrayList(arrayList);
        });
        Entermant.setOnClickListener(view -> {
            arrayList=database.getType("entermant");
            adptar.setArrayList(arrayList);
        });
        Family.setOnClickListener(x->{
            arrayList=database.getType("family");
            adptar.setArrayList(arrayList);
        });



        adptar=new Adptar(arrayList, new Adptar.Listener() {
            @Override
            public void OnObjectClick(int id) {
                Note n=new Note();
                n=database.SearchById(id);
                Log.d("id",id+"");
                Log.d("note",n.toString());
                Intent intent = new Intent(getBaseContext(), EditNote.class);
                intent.putExtra(IntentValue,n);
                startActivityForResult(intent,5);
            }
        });

        NotesShow n= NotesShow.newInstance(adptar);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.FrameL,n);
        ft.commit();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getBaseContext(),AddNote.class),2);
            }
        });
        System.out.println(arrayList.size()+" new ");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        arrayList=database.getAll();
        adptar.setArrayList(arrayList);
        System.out.println(arrayList.size()+" it is back");
        for (int i=0;i<arrayList.size();i++){
            System.out.println(i+" "+arrayList.get(i).toString());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_view,menu);
        searchView = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                arrayList=database.SearchByTitle(s);
                adptar.setArrayList(arrayList);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                arrayList = database.SearchByTitle(s);
                adptar.setArrayList(arrayList);
                return false;
            }
        });
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                arrayList=database.getAll();
                adptar.setArrayList(arrayList);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return true;
    }
}