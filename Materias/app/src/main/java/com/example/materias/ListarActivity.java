package com.example.materias;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

public class ListarActivity extends AppCompatActivity {

    private ListView listView;
    private GradeDAO dao;
    private List<Grade> gradeList;
    private List<Grade> gradeFiltrada = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);

        listView = findViewById(R.id.lista);
        dao = new GradeDAO(this);
        gradeList = dao.obterTodos();
        gradeFiltrada.addAll(gradeList);
        ArrayAdapter<Grade> adaptador = new ArrayAdapter<Grade>(this, android.R.layout.simple_list_item_1, gradeFiltrada);
        listView.setAdapter(adaptador);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_principal, menu);

        SearchView sv = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                procuraMateria(newText);
                return false;
            }
        });

        return true;
    }

    public void procuraMateria (String materia) {
        gradeFiltrada.clear();
        for(Grade g : gradeList){
            if(g.getMateria().toLowerCase().contains(materia.toLowerCase())){
                gradeFiltrada.add(g);
            }
        }
        listView.invalidateViews();
    }
    public void cadastrar(MenuItem item){
        Intent it = new Intent(this, MainActivity.class);
        startActivity(it);
    }

    @Override
    public void onResume() {
        super.onResume();
        gradeList = dao.obterTodos();
        gradeFiltrada.clear();
        gradeFiltrada.addAll(gradeList);
        listView.invalidateViews();
    }

}