package com.example.controlasistencia;

public class AlumnoItem {
    private String matricula;
    private String nombre;
    private String estatus;

    public AlumnoItem(String matricula, String nombre) {
        this.matricula = matricula;
        this.nombre = nombre;
        estatus = "";
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }
}
