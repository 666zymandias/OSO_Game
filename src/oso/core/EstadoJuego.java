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
    private static final long serialVersionUID = 20L;
    
    private final PartidaMultijugador partida;
    private int turnoActual;
    private Jugada ultimaJugada;

    public EstadoJuego(int filas, int columnas, int turnoActual) {
        this.partida = new PartidaMultijugador(filas, columnas);
        this.turnoActual = turnoActual;
        ultimaJugada = null;
    }

    public PartidaMultijugador getPartidaOso() {
        return partida;
    }

    public int getTurnoActual() {
        return turnoActual;
    }

    public Jugada getUltimaJugada() {
        return ultimaJugada;
    }

    public void setUltimaJugada(Jugada ultimaJugada) {
        this.ultimaJugada = ultimaJugada;
    }
    
    public void siguienteTurno() {
        if (turnoActual == 0)
            turnoActual = 1; 
        else
            turnoActual = 0;
    }
}
