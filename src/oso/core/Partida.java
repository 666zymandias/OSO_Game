
package oso.core;

/**
 *
 * @author hydfe
 */
public class Partida {
    private int numOsos;
    private final Tablero tableroOso;

    public Partida(int filas, int columnas) {
        this.tableroOso = new Tablero(filas, columnas);
        this.numOsos = 0;
        tableroOso.init();
    }

    public Tablero getTablero() {
        return tableroOso;
    }

    public int getNumOsos() {
        return numOsos;
    }
    
    public void imprimeEstadoPartida() {
        tableroOso.imprimeTablero();
        System.out.println("Numero de OSOs: "+ numOsos);
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
            int x = jugada.getFila();
            int y = jugada.getColumna();
            char letra =  jugada.getLetra();
            tableroOso.ponPieza(jugada.getFila(), jugada.getColumna(), letra);
            numOsos += tableroOso.contarOsosDesdeCasilla(x, y, letra);
            imprimeEstadoPartida();
        }
        else
            System.out.println("Jugada invalida, pon tu pieza en una casilla libre!");
    }
    
}
