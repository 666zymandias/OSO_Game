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
    private String J1 = null;
    private String J2 = null;
    private String ganador;

    public EstadoJuego(int filas, int columnas, int turnoActual) {
        this.partida = new PartidaMultijugador(filas, columnas);
        this.turnoActual = turnoActual;
        this.ultimaJugada = null;
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

    public String getJ1() {
        return J1;
    }

    public String getJ2() {
        return J2;
    }

    public void setJ1(String J1) {
        this.J1 = J1;
    }

    public void setJ2(String J2) {
        this.J2 = J2;
    }

    public String getGanador() {
        return ganador;
    }

    public void setGanador(String ganador) {
        this.ganador = ganador;
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
