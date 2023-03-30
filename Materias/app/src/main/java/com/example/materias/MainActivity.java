package com.example.materias;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText materia, curso, professor, nota, semestre;
    private GradeDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        materia = findViewById(R.id.inpMateria);
        curso = findViewById(R.id.inpCurso);
        professor = findViewById(R.id.inpProfessor);
        semestre = findViewById(R.id.inpSemestre);
        nota = findViewById(R.id.inpNota);
        dao = new GradeDAO(this);
    }

    public void salvar (View view){
        Grade a = new Grade();
        a.setMateria(materia.getText().toString());
        a.setCurso(curso.getText().toString());
        a.setProfessor(professor.getText().toString());
        a.setSemestre(Integer.parseInt(semestre.getText().toString()));
        a.setNota(Double.parseDouble(nota.getText().toString()));
        long id = dao.inserir(a);
        Toast.makeText(this, "Grade inserida com id:" + id, Toast.LENGTH_SHORT).show();
    }
}