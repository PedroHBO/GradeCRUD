package com.example.materias;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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
        ArrayAdapter<Grade> adaptador = new ArrayAdapter<Grade>(this, android.R.layout.simple_list_item_1, gradeList);
        listView.setAdapter(adaptador);
    }
}