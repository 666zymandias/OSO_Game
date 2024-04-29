
package oso.core;

/**
 *
 * @author hydfe
 */
public class Partida {
    private final char[][] tablero;
    private int numOsos;

    public Partida(int filas, int columnas) {
        this.tablero = new char[filas][columnas];
        this.numOsos = 0;
        init();
    }
    
    private void init() {
        for (char[] tablero1 : tablero) {
            for (int j = 0; j < tablero[0].length; j++) {
                tablero1[j] = ' ';
            }
        }
    }

    public char[][] getTablero() {
        return tablero;
    }

    public int getNumOsos() {
        return numOsos;
    }
    
    public void imprimeTablero() {
        System.out.println("");
        System.out.println("-----Tablero-----");
        for (char[] fila : tablero) {
            for (char i : fila) {
                System.out.print(i);
                System.out.print("|");
            }
            System.out.println("");
        }
        System.out.println("-----Tablero-----");
        System.out.println("Numero de Osos: "+ numOsos);
    }
        
    public boolean finPartida() {
        for (char[] fila : tablero) {
            for (char i : fila) {
                if (i == ' ')  return false;
            }
        }
        return true;
    }

    public boolean esValida(Jugada jugada) {
        int x = jugada.getFila();
        int y = jugada.getColumna();
        return tablero[x][y] == ' ';
    }
    
    public void ponPieza(int x, int y, char letra) {
        tablero[x][y] = letra;
    }
    
    public int contarOsosDesdeCasilla(int x, int y, char letra) {
        int filas = tablero.length;
        int columnas = tablero[0].length;

        // Contar osos horizontales, verticales y diagonales
        int osos = 0;
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (tablero[i][j] == letra) {
                    // Verificar osos horizontales
                    if (j + 2 < columnas && tablero[i][j + 1] == letra && tablero[i][j + 2] == letra) {
                        osos++;
                    }
                    // Verificar osos verticales
                    if (i + 2 < filas && tablero[i + 1][j] == letra && tablero[i + 2][j] == letra) {
                        osos++;
                    }
                    // Verificar osos diagonales
                    if (i + 2 < filas && j + 2 < columnas && tablero[i + 1][j + 1] == letra && tablero[i + 2][j + 2] == letra) {
                        osos++;
                    }
                    if (i + 2 < filas && j - 2 >= 0 && tablero[i + 1][j - 1] == letra && tablero[i + 2][j - 2] == letra) {
                        osos++;
                    }
                }
            }
        }
        return osos;
    }
    
    public void realizaJugada(Jugada jugada) {
        if (esValida(jugada)) {
            int x = jugada.getFila();
            int y = jugada.getColumna();
            char letra =  jugada.getLetra().charAt(0);
            ponPieza(jugada.getFila(), jugada.getColumna(), letra);
            numOsos += contarOsosDesdeCasilla(x, y, letra);
        }
    }
    
}
