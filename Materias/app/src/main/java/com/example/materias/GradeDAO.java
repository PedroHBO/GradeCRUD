package com.example.materias;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

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

    public List<Grade> obterTodos(){
        List<Grade> gradeList = new ArrayList<>();
        Cursor cursor = banco.query("grade",
                new String[]{"id", "materia", "professor", "curso", "nota", "semestre"},
                null, null, null, null, null);
        while(cursor.moveToNext()){
            Grade grade = new Grade();
            grade.setId(cursor.getLong(0));
            grade.setMateria(cursor.getString(1));
            grade.setProfessor(cursor.getString(2));
            grade.setCurso(cursor.getString(3));
            grade.setNota(cursor.getDouble(4));
            grade.setSemestre(cursor.getInt(5));
            gradeList.add(grade);
        }
    return  gradeList;
    }

    public void excluir(Grade grade){
       banco.delete("grade",
               "id = ?", new String[]{String.valueOf(grade.getId())});
    }

    public void atualizar(Grade grade){
        ContentValues values = new ContentValues();
        values.put("id", grade.getId());
        values.put("materia", grade.getMateria());
        values.put("professor", grade.getProfessor());
        values.put("curso", grade.getCurso());
        values.put("nota", grade.getNota());
        values.put("semestre", grade.getSemestre());
        banco.update("grade", values,
                "id = ?", new String[]{String.valueOf(grade.getId())});
    }

}
