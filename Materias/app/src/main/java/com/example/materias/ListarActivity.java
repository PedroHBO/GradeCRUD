package com.example.materias;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
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
        registerForContextMenu(listView);
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

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_context, menu);
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

    public void atualizar(MenuItem item){
        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final Grade gradeAtualizar = gradeFiltrada.get(menuInfo.position);
        Intent it = new Intent(this, MainActivity.class);
        it.putExtra("grade", gradeAtualizar);
        startActivity(it);
    }
    public void excluir(MenuItem item){
        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final Grade gradeExcluir = gradeFiltrada.get(menuInfo.position);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Observação")
                .setMessage("Realmente deseja excluir a grade?")
                .setNegativeButton("Não", null)
                .setPositiveButton("Sim", (dialog1, which) -> {
                    gradeFiltrada.remove(gradeExcluir);
                    gradeList.remove(gradeExcluir);
                    dao.excluir(gradeExcluir);
                    listView.invalidateViews();
                }).create();
        dialog.show();
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