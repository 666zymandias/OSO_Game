
package oso.core;

/**
 *
 * @author hydfe
 */
public class Partida {
    private final Casilla[][] tablero;
    private int numOsos;

    public Partida(int filas, int columnas) {
        this.tablero = new Casilla[filas][columnas];
        this.numOsos = 0;
        init();
    }
    
    private void init() {
        for (Casilla[] tablero1 : tablero) {
            for (int j = 0; j < tablero[0].length; j++) {
                tablero1[j] = new Casilla(' ');
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
                    tablero[x][y].setValida(false);
                    tablero[x][y-1].setValida(false);
                    tablero[x][y+1].setValida(false);
                }
            }
            
            if (0 < x && x < filas - 1 && tablero[x-1][y].isValida() && tablero[x+1][y].isValida()) {
                if (tablero[x-1][y].getValor() == 'O' && tablero[x+1][y].getValor() == 'O') {
                    osos++;
                    tablero[x][y].setValida(false);
                    tablero[x-1][y].setValida(false);
                    tablero[x+1][y].setValida(false);
                }
            }
            
            if (0 < x && 0 < y && y < columnas - 1 && x < filas - 1 && tablero[x-1][y-1].isValida() && tablero[x+1][y+1].isValida()) {
                if (tablero[x-1][y-1].getValor() == 'O' && tablero[x+1][y+1].getValor() == 'O') {
                    osos++;
                    tablero[x][y].setValida(false);
                    tablero[x-1][y-1].setValida(false);
                    tablero[x+1][y+1].setValida(false);
                }
            }
            
            if (0 < x && 0 < y && y < columnas - 1 && x < filas - 1 && tablero[x-1][y+1].isValida() && tablero[x+1][y-1].isValida()) {
                if (tablero[x-1][y+1].getValor() == 'O' && tablero[x+1][y-1].getValor() == 'O') {
                    osos++;
                    tablero[x][y].setValida(false);
                    tablero[x-1][y+1].setValida(false);
                    tablero[x+1][y-1].setValida(false);
                }
            }
            
        }
                
        else if (letra == 'O') {
            //horizontal
            if (1 < y && tablero[x][y-1].isValida() && tablero[x][y-2].isValida()) {
                if (tablero[x][y-1].getValor() == 'S' && tablero[x][y-2].getValor() == 'O') {
                    osos++;
                    tablero[x][y].setValida(false);
                    tablero[x][y-1].setValida(false);
                    tablero[x][y-2].setValida(false);
                }
            }
            
            if (y < columnas - 2 && tablero[x][y+1].isValida() && tablero[x][y+2].isValida()) {
                if (tablero[x][y+1].getValor() == 'S' && tablero[x][y+2].getValor() == 'O') {
                    osos++;
                    tablero[x][y].setValida(false);
                    tablero[x][y+1].setValida(false);
                    tablero[x][y+2].setValida(false);
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
                    tablero[x][y].setValida(false);
                    tablero[x+1][y].setValida(false);
                    tablero[x+2][y].setValida(false);
                }
            }
            
            //diagonal
            if (1 < x && 1 < y && tablero[x-1][y-1].isValida() && tablero[x-2][y-2].isValida()) {
                if (tablero[x-1][y-1].getValor() == 'S' && tablero[x-2][y-2].getValor() == 'O') {
                    osos++;
                    tablero[x][y].setValida(false);
                    tablero[x-1][y-1].setValida(false);
                    tablero[x-2][y-2].setValida(false);
                }
            }
            
            if (x < filas - 2 && y < columnas - 2 && tablero[x+1][y+1].isValida() && tablero[x+2][y+2].isValida()) {
                if (tablero[x+1][y+1].getValor() == 'S' && tablero[x+2][y+2].getValor() == 'O') {
                    osos++;
                    tablero[x][y].setValida(false);
                    tablero[x+1][y+1].setValida(false);
                    tablero[x+2][y+2].setValida(false);
                }
            }
            
            if (1 < x && y < columnas - 2 && tablero[x-1][y+1].isValida() && tablero[x-2][y+2].isValida()) {
                if (tablero[x-1][y+1].getValor() == 'S' && tablero[x-2][y+2].getValor() == 'O') {
                    osos++;
                    tablero[x][y].setValida(false);
                    tablero[x-1][y+1].setValida(false);
                    tablero[x-2][y+2].setValida(false);
                }
            }
            
            if (1 < y && x < filas - 2 && tablero[x+1][y-1].isValida() && tablero[x+2][y-2].isValida()) {
                if (tablero[x+1][y-1].getValor() == 'S' && tablero[x+2][y-2].getValor() == 'O') {
                    osos++;
                    tablero[x][y].setValida(false);
                    tablero[x+1][y-1].setValida(false);
                    tablero[x+2][y-2].setValida(false);
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
    
}
