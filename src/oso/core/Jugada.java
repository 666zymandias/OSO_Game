/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oso.core;

/**
 *
 * @author felipe
 */
public class Jugada {
    private int fila;
    private int columna;
    private String letra;

    public Jugada(int fila, int columna, String letra) {
        this.fila = fila;
        this.columna = columna;
        this.letra = letra;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public String getLetra() {
        return letra;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }
    
}
