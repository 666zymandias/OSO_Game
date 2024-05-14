/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oso.core;

import java.io.Serializable;

public class Jugada implements Serializable{
    private int fila;
    private int columna;
    private char letra;
    private int jugador = -1;

    
    public Jugada(int fila, int columna, char letra) {
        this.fila = fila;
        this.columna = columna;
        this.letra = letra;
    }
    
    public Jugada(int fila, int columna, char letra, int jugador) {
        this.fila = fila;
        this.columna = columna;
        this.letra = letra;
        this.jugador = jugador;
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

    public int getJugador() {
        return jugador;
    }
    
    @Override
    public String toString() {
        return "Jugador: "+ jugador + ", fila: " +fila+ ", columna: "+ columna + ", letra: "+ letra;
    }
    
}
