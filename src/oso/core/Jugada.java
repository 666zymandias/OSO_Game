package oso.core;

import java.io.Serializable;

public class Jugada implements Serializable{
    private static final long serialVersionUID = 100L;
    
    private int fila;
    private int columna;
    private char letra;
    private int jugador = -1;
    private String nombrePJ;
    private String nombreGanador;
    private boolean finalJuego;
    
    public Jugada(int fila, int columna, char letra) {
        this.fila = fila;
        this.columna = columna;
        this.letra = letra;
    }
    
    public Jugada(int fila, int columna, char letra, int jugador, String nombrePJ, boolean esFinal) {
        this.fila = fila;
        this.columna = columna;
        this.letra = letra;
        this.jugador = jugador;
        this.nombrePJ = nombrePJ;
        this.finalJuego = esFinal;
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

    public String getNombrePJ() {
        return nombrePJ;
    }

    public boolean isEsFinalPartida() {
        return finalJuego;
    }

    public void setFinalPartida() {
        this.finalJuego = true;
    }

    public String getNombreGanador() {
        return nombreGanador;
    }

    public void setNombreGanador(String nombreGanador) {
        this.nombreGanador = nombreGanador;
    }
    
    
    @Override
    public String toString() {
        return "Jugador: "+ jugador + ", fila: " +fila+ ", columna: "+ columna + ", letra: "+ letra;
    }
    
}
