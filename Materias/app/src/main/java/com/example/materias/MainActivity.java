package com.example.materias;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText materia, curso, professor, nota, semestre;
    private GradeDAO dao;
    private Grade grade = null;

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

        Intent it = getIntent();
        if(it.hasExtra("grade")){
            grade = (Grade) it.getSerializableExtra("grade");
            materia.setText(grade.getMateria());
            curso.setText(grade.getCurso());
            professor.setText(grade.getProfessor());
            semestre.setText(grade.getSemestre());
            nota.setText(String.valueOf(grade.getNota()));
        }
    }
    public void salvar (View view){

        if (grade == null){
            grade = new Grade();
            grade.setMateria(materia.getText().toString());
            grade.setCurso(curso.getText().toString());
            grade.setProfessor(professor.getText().toString());
            grade.setSemestre(Integer.parseInt(semestre.getText().toString()));
            grade.setNota(Double.parseDouble(nota.getText().toString()));
            long id = dao.inserir(grade);
            Toast.makeText(this, "Grade inserida com sucesso", Toast.LENGTH_SHORT).show();
            finish();
        }else{
            grade.setMateria(materia.getText().toString());
            grade.setCurso(curso.getText().toString());
            grade.setProfessor(professor.getText().toString());
            grade.setSemestre(Integer.parseInt(semestre.getText().toString()));
            grade.setNota(Double.parseDouble(nota.getText().toString()));
            dao.atualizar(grade);
            Toast.makeText(this, "Grade atualizada com sucesso", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}