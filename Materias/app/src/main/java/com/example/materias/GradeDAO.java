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
        Cursor cursor = banco.query("grade", new String[]{"id", "materia", "professor", "curso", "nota", "semestre"},
                null, null, null, null, "id");
        while(cursor.moveToNext()){
            Grade a = new Grade();
            a.setId(cursor.getLong(0));
            a.setMateria(cursor.getString(1));
            a.setProfessor(cursor.getString(2));
            a.setCurso(cursor.getString(3));
            a.setNota(cursor.getDouble(4));
            a.setSemestre(cursor.getInt(5));
            gradeList.add(a);
        }
    return  gradeList;
    }

    public void excluir(Grade a){
       banco.delete("grade", "id = ?", new String[]{String.valueOf(a.getId())});
    }

}
