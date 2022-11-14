package com.example.pmdm_ejercicioextra.modelos;

import java.io.Serializable;

public class Partido implements Serializable {
    private String equipo1;
    private int gol1;
    private String equipo2;
    private int gol2;
    private String resumen;

    public Partido(String equipo1, int gol1, String equipo2, int gol2, String resumen) {
        this.equipo1 = equipo1;
        this.gol1 = gol1;
        this.equipo2 = equipo2;
        this.gol2 = gol2;
        this.resumen = resumen;
    }

    public String getEquipo1() {
        return equipo1;
    }

    public void setEquipo1(String equipo1) {
        this.equipo1 = equipo1;
    }

    public int getGol1() {
        return gol1;
    }

    public void setGol1(int gol1) {
        this.gol1 = gol1;
    }

    public String getEquipo2() {
        return equipo2;
    }

    public void setEquipo2(String equipo2) {
        this.equipo2 = equipo2;
    }

    public int getGol2() {
        return gol2;
    }

    public void setGol2(int gol2) {
        this.gol2 = gol2;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    @Override
    public String toString() {
        return "Partido{" +
                "Equipo local='" + equipo1 + '\'' +
                ", gol1=" + gol1 +
                ", Equipo visitante='" + equipo2 + '\'' +
                ", gol2=" + gol2 +
                ", resumen='" + resumen + '\'' +
                '}';
    }
}
