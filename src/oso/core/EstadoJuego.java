/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oso.core;

import java.io.Serializable;

/**
 *
 * @author felipe
 */
public class EstadoJuego implements Serializable{
    
    private final PartidaMultijugador partida;
    private int turnoActual;
    private int totalJugadores;

    public EstadoJuego(int filas, int columnas, int turnoActual, int totalJugadores) {
        this.partida = new PartidaMultijugador(filas, columnas);
        this.turnoActual = turnoActual;
        this.totalJugadores = totalJugadores;
    }

    public PartidaMultijugador getPartidaOso() {
        return partida;
    }

    public int getTurnoActual() {
        return turnoActual;
    }

    public int getTotalJugadores() {
        return totalJugadores;
    }
    
    public void aumentaJugadoresEn1() {
        this.totalJugadores ++;
    }
    
    public void siguienteTurno() {
        if (turnoActual == 0)
            turnoActual = 1; 
        else
            turnoActual = 0;
    }
}
