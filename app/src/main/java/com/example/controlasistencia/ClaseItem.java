package com.example.controlasistencia;

public class ClaseItem {
    private String claseNombre;
    private String materiaNombre;

    public String getClaseNombre() {
        return claseNombre;
    }

    public void setClaseNombre(String calseNombre) {
        this.claseNombre = calseNombre;
    }

    public String getMateriaNombre() {
        return materiaNombre;
    }

    public void setMateriaNombre(String materiaNombre) {
        this.materiaNombre = materiaNombre;
    }

    public ClaseItem(String claseNombre, String materiaNombre) {
        this.claseNombre = claseNombre;
        this.materiaNombre = materiaNombre;
    }
}
