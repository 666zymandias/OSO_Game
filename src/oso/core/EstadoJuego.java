/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oso.core;

/**
 *
 * @author felipe
 */
public class EstadoJuego {
    
    private final Partida partidaOso;
    private final int ososJ1;
    private final int ososJ2;
    private final int TurnoActual;

    public EstadoJuego(int filas, int columnas, int ososJ1, int ososJ2, int TurnoActual) {
        this.partidaOso = new Partida(filas, columnas);
        this.ososJ1 = ososJ1;
        this.ososJ2 = ososJ2;
        this.TurnoActual = TurnoActual;
    }

    public Partida getPartidaOso() {
        return partidaOso;
    }

    public int getOsosJ1() {
        return ososJ1;
    }

    public int getOsosJ2() {
        return ososJ2;
    }

    public int getTurnoActual() {
        return TurnoActual;
    }
    
    
}
