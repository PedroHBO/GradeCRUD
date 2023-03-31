package com.example.materias;

import java.io.Serializable;

public class Grade implements Serializable {
    private long id;
    private String materia;
    private String curso;
    private String professor;
    private double nota;
    private int semestre;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        String text = materia +
                "\nCurso: " + curso +
                "\nProfessor: " + professor +
                "\nSemestre: " + semestre +
                "\nNota: " + nota;

        return text;
    }
}
