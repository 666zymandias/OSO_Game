/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oso.core;

public class Jugada {
    private int fila;
    private int columna;
    private char letra;

    /**
     *
     * @param fila
     * @param columna
     * @param letra
     */
    public Jugada(int fila, int columna, char letra) {
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

    public char getLetra() {
        return letra;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public void setLetra(char letra) {
        this.letra = letra;
    }
    
}
