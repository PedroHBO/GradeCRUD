package com.example.materias;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class GradeDAO {

    private Conexao conexao;
    private SQLiteDatabase banco;

    public GradeDAO(Context context){
        conexao = new Conexao(context);
        banco = conexao.getWritableDatabase();
    }

    public long inserir(Grade grade){
        ContentValues values = new ContentValues();
        values.put("materia", grade.getMateria());
        values.put("professor", grade.getProfessor());
        values.put("curso", grade.getCurso());
        values.put("nota", grade.getNota());
        values.put("semestre", grade.getSemestre());
        return banco.insert("grade", null, values);
    }
}
