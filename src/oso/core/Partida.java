
package oso.core;

import java.io.Serializable;


/**
 *
 * @author hydfe
 */
public class Partida implements Serializable {
    protected int totalOsos = 0;
    protected final Tablero tableroOso;

    public Partida(int filas, int columnas) {
        this.tableroOso = new Tablero(filas, columnas);
        tableroOso.init();
    }

    public Tablero getTablero() {
        return tableroOso;
    }

    public int getNumOsos() {
        return totalOsos;
    }
    
    public void imprimeEstadoPartida() {
        tableroOso.imprimeTablero();
        System.out.println("Numero de OSOs: "+ totalOsos);
    }
        
    public boolean finPartida() {
        for (Casilla[] fila : tableroOso.getMatrizOsos()) {
            for (Casilla i : fila) {
                if (i.getValor() == ' ')  return false;
            }
        }
        return true;
    }

    public boolean esValida(Jugada jugada) {
        int x = jugada.getFila();
        int y = jugada.getColumna();
        return tableroOso.getMatrizOsos()[x][y].getValor() == ' ';
    }
    
    public void realizaJugada(Jugada jugada) {
        if (esValida(jugada)) {
            System.out.println(jugada);
            int x = jugada.getFila();
            int y = jugada.getColumna();
            char letra =  jugada.getLetra();
            tableroOso.ponPieza(jugada.getFila(), jugada.getColumna(), letra);
            totalOsos += tableroOso.contarOsosDesdeCasilla(x, y, letra);
            imprimeEstadoPartida();
        }
        else
            System.out.println("Jugada invalida, pon tu pieza en una casilla libre!");
    }
    
}
