
package oso.core;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hydfe
 */
public class Partida {
    private final Casilla[][] tablero;
    private int numOsos;
    private List<Casilla> casillasJugada = new ArrayList<>();

    public Partida(int filas, int columnas) {
        this.tablero = new Casilla[filas][columnas];
        this.numOsos = 0;
        init();
    }
    
    private void init() {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[0].length; j++) {
                tablero[i][j] = new Casilla(' ', i, j);
            }
        }
    }

    public Casilla[][] getTablero() {
        return tablero;
    }

    public int getNumOsos() {
        return numOsos;
    }
    
    public void imprimeTablero() {
        System.out.println("");
        System.out.println("-----Tablero-----");
        for (Casilla[] fila : tablero) {
            for (Casilla i : fila) {
                System.out.print(i);
                System.out.print("|");
            }
            System.out.println("");
        }
        System.out.println("-----Tablero-----");
        System.out.println("Numero de Osos: "+ numOsos);
    }
        
    public boolean finPartida() {
        for (Casilla[] fila : tablero) {
            for (Casilla i : fila) {
                if (i.getValor() == ' ')  return false;
            }
        }
        return true;
    }

    public boolean esValida(Jugada jugada) {
        int x = jugada.getFila();
        int y = jugada.getColumna();
        return tablero[x][y].getValor() == ' ';
    }
    
    public void ponPieza(int x, int y, char letra) {
        letra = Character.toUpperCase(letra);
        tablero[x][y].setValor(letra);
    }
    
    public int contarOsosDesdeCasilla(int x, int y, char letra) {
        int filas = tablero.length;
        int columnas = tablero[0].length;
        int osos = 0;
        
        if (letra == 'S') {
            if (0 < y && y < columnas - 1 && tablero[x][y-1].isValida() && tablero[x][y+1].isValida()) {
                if (tablero[x][y-1].getValor() == 'O' && tablero[x][y+1].getValor() == 'O') {
                    osos++;
                    casillasJugada.add(tablero[x][y]);
                    casillasJugada.add(tablero[x][y-1]);
                    casillasJugada.add(tablero[x][y+1]);
                }
            }
            
            if (0 < x && x < filas - 1 && tablero[x-1][y].isValida() && tablero[x+1][y].isValida()) {
                if (tablero[x-1][y].getValor() == 'O' && tablero[x+1][y].getValor() == 'O') {
                    osos++;
                    casillasJugada.add(tablero[x][y]);
                    casillasJugada.add(tablero[x-1][y+1]);
                    casillasJugada.add(tablero[x+1][y]);
                }
            }
            
            if (0 < x && 0 < y && y < columnas - 1 && x < filas - 1 && tablero[x-1][y-1].isValida() && tablero[x+1][y+1].isValida()) {
                if (tablero[x-1][y-1].getValor() == 'O' && tablero[x+1][y+1].getValor() == 'O') {
                    osos++;
                    casillasJugada.add(tablero[x][y]);
                    casillasJugada.add(tablero[x-1][y-1]);
                    casillasJugada.add(tablero[x+1][y+1]);
                }
            }
            
            if (0 < x && 0 < y && y < columnas - 1 && x < filas - 1 && tablero[x-1][y+1].isValida() && tablero[x+1][y-1].isValida()) {
                if (tablero[x-1][y+1].getValor() == 'O' && tablero[x+1][y-1].getValor() == 'O') {
                    osos++;
                    
                    casillasJugada.add(tablero[x][y]);
                    casillasJugada.add(tablero[x-1][y+1]);
                    casillasJugada.add(tablero[x+1][y-1]);
                }
            }
            
        }
                
        else if (letra == 'O') {
            //horizontal
            if (1 < y && tablero[x][y-1].isValida() && tablero[x][y-2].isValida()) {
                if (tablero[x][y-1].getValor() == 'S' && tablero[x][y-2].getValor() == 'O') {
                    osos++;
                    casillasJugada.add(tablero[x][y]);
                    casillasJugada.add(tablero[x][y-1]);
                    casillasJugada.add(tablero[x][y-2]);
                }
            }
            
            if (y < columnas - 2 && tablero[x][y+1].isValida() && tablero[x][y+2].isValida()) {
                if (tablero[x][y+1].getValor() == 'S' && tablero[x][y+2].getValor() == 'O') {
                    osos++;
                    
                    casillasJugada.add(tablero[x][y]);
                    casillasJugada.add(tablero[x][y+1]);
                    casillasJugada.add(tablero[x][y+21]);
                }
            }
            
            //vertical
            if (1 < x && tablero[x-1][y].isValida() && tablero[x-2][y].isValida()) {
                if (tablero[x-1][y].getValor() == 'S' && tablero[x-2][y].getValor() == 'O') {
                    osos++;
                    tablero[x][y].setValida(false);
                    tablero[x-1][y].setValida(false);
                    tablero[x-2][y].setValida(false);
                }
            }
            
            if (x < filas - 2 && tablero[x+1][y].isValida() && tablero[x+2][y].isValida()) {
                if (tablero[x+1][y].getValor() == 'S' && tablero[x+2][y].getValor() == 'O') {
                    osos++;
                    casillasJugada.add(tablero[x][y]);
                    casillasJugada.add(tablero[x][y-1]);
                    casillasJugada.add(tablero[x][y+1]);
                }
            }
            
            //diagonal
            if (1 < x && 1 < y && tablero[x-1][y-1].isValida() && tablero[x-2][y-2].isValida()) {
                if (tablero[x-1][y-1].getValor() == 'S' && tablero[x-2][y-2].getValor() == 'O') {
                    osos++;
                    casillasJugada.add(tablero[x][y]);
                    casillasJugada.add(tablero[x-1][y-1]);
                    casillasJugada.add(tablero[x-2][y-2]);
                }
            }
            
            if (x < filas - 2 && y < columnas - 2 && tablero[x+1][y+1].isValida() && tablero[x+2][y+2].isValida()) {
                if (tablero[x+1][y+1].getValor() == 'S' && tablero[x+2][y+2].getValor() == 'O') {
                    osos++;
                    casillasJugada.add(tablero[x][y]);
                    casillasJugada.add(tablero[x+1][y+1]);
                    casillasJugada.add(tablero[x+2][y+2]);
                }
            }
            
            if (1 < x && y < columnas - 2 && tablero[x-1][y+1].isValida() && tablero[x-2][y+2].isValida()) {
                if (tablero[x-1][y+1].getValor() == 'S' && tablero[x-2][y+2].getValor() == 'O') {
                    osos++;
                    casillasJugada.add(tablero[x][y]);
                    casillasJugada.add(tablero[x-1][y+1]);
                    casillasJugada.add(tablero[x-2][y+2]);
                }
            }
            
            if (1 < y && x < filas - 2 && tablero[x+1][y-1].isValida() && tablero[x+2][y-2].isValida()) {
                if (tablero[x+1][y-1].getValor() == 'S' && tablero[x+2][y-2].getValor() == 'O') {
                    osos++;
                    casillasJugada.add(tablero[x][y]);
                    casillasJugada.add(tablero[x+1][y-1]);
                    casillasJugada.add(tablero[x+2][y-2]);
                }
            }
            
        }
        return osos;
    }
    
    public void realizaJugada(Jugada jugada) {
        if (esValida(jugada)) {
            int x = jugada.getFila();
            int y = jugada.getColumna();
            char letra =  jugada.getLetra();
            ponPieza(jugada.getFila(), jugada.getColumna(), letra);
            numOsos += contarOsosDesdeCasilla(x, y, letra);
            imprimeTablero();
        }
        else
            System.out.println("Jugada invalida, pon tu pieza en una casilla libre!");
    }

    public List<Casilla> getCasillasJugada() {
        return casillasJugada;
    }
    
    public void vaciaLista() {
        casillasJugada.clear();
    }
    
}
