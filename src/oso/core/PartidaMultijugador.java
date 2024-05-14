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
public class PartidaMultijugador extends Partida implements Serializable{
    
    private int ososJ1 = 0;
    private int ososJ2 = 0;
    
    public PartidaMultijugador(int filas, int columnas) {
        super(filas, columnas);
    }

    public int getOsosJ1() {
        return ososJ1;
    }

    public int getOsosJ2() {
        return ososJ2;
    }
    
    @Override
    public void realizaJugada(Jugada jugada) {
        if (esValida(jugada)) {
            
            System.out.println(jugada);
            int x = jugada.getFila();
            int y = jugada.getColumna();
            char letra =  jugada.getLetra();
            tableroOso.ponPieza(jugada.getFila(), jugada.getColumna(), letra);
            if (jugada.getJugador() == 0) {
                ososJ1 += tableroOso.contarOsosDesdeCasilla(x, y, letra);
                totalOsos += ososJ1;
            }
            else {
                ososJ2 += tableroOso.contarOsosDesdeCasilla(x, y, letra); 
                totalOsos += ososJ2;
            }
            imprimeEstadoPartida();
        }
        else
            System.out.println("Jugada invalida, pon tu pieza en una casilla libre!");
    }
    
}
