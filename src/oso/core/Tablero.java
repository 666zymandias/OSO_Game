/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oso.core;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Tablero implements Serializable{
    private static final long serialVersionUID = 12L;
    
    private final Casilla[][] matrizOsos;
    private final Set<Casilla> casillasJugada = new HashSet<>();

    public Tablero(int filas, int columnas) {
        this.matrizOsos = new Casilla[filas][columnas];
    }
    
    public void init() {
        for (int i = 0; i < matrizOsos.length; i++) {
            for (int j = 0; j < matrizOsos[0].length; j++) {
                matrizOsos[i][j] = new Casilla(' ', i, j);
            }
        }
    }
    
    public int getFilas() {
        return matrizOsos.length;
    }
    
    public int getColumnas() {
        return matrizOsos[0].length;
    }

    public Casilla[][] getMatrizOsos() {
        return matrizOsos;
    }

    public Set<Casilla> getCasillasJugada() {
        return casillasJugada;
    }
    
    public void imprimeTablero() {
        System.out.println("");
        System.out.println("-----Tablero-----");
        for (Casilla[] fila : matrizOsos) {
            for (Casilla i : fila) {
                System.out.print(i);
                System.out.print("|");
            }
            System.out.println("");
        }
        System.out.println("-----Tablero-----");
    }
    
    public boolean esValida(Jugada jugada) {
        int x = jugada.getFila();
        int y = jugada.getColumna();
        return matrizOsos[x][y].getValor() == ' ';
    }
    
    public void ponPieza(int x, int y, char letra) {
        letra = Character.toUpperCase(letra);
        matrizOsos[x][y].setValor(letra);
    }
    
    public int contarOsosDesdeCasilla(int x, int y, char letra) {
        int filas = matrizOsos.length;
        int columnas = matrizOsos[0].length;
        int osos = 0;
        
        if (letra == 'S') {
            osos += cuentaOsosS(x, y, filas, columnas);
        }
                
        else {
            osos += cuentaOsosO(x, y, filas, columnas);
        }
        return osos;
    }
    
        public void bloqueaJugada() {
        for (Casilla casilla : casillasJugada) {
            casilla.setValida(false);
        }
        casillasJugada.clear();
    }
    
    private int cuentaOsosS(int x, int y, int filas, int columnas) {
        int osos = 0;
        if (0 < y && y < columnas - 1 && matrizOsos[x][y-1].isValida() && matrizOsos[x][y+1].isValida()) {
            if (matrizOsos[x][y-1].getValor() == 'O' && matrizOsos[x][y+1].getValor() == 'O') {
                osos++;
                casillasJugada.add(matrizOsos[x][y]);
                casillasJugada.add(matrizOsos[x][y-1]);
                casillasJugada.add(matrizOsos[x][y+1]);
            }
        }
        if (0 < x && x < filas - 1 && matrizOsos[x - 1][y].isValida() && matrizOsos[x + 1][y].isValida()) {
            if (matrizOsos[x - 1][y].getValor() == 'O' && matrizOsos[x + 1][y].getValor() == 'O') {
                osos++;
                casillasJugada.add(matrizOsos[x][y]);
                casillasJugada.add(matrizOsos[x - 1][y]);
                casillasJugada.add(matrizOsos[x + 1][y]);
            }
        }

        if (0 < x && 0 < y && y < columnas - 1 && x < filas - 1 && matrizOsos[x - 1][y - 1].isValida() && matrizOsos[x + 1][y + 1].isValida()) {
            if (matrizOsos[x - 1][y - 1].getValor() == 'O' && matrizOsos[x + 1][y + 1].getValor() == 'O') {
                osos++;
                casillasJugada.add(matrizOsos[x][y]);
                casillasJugada.add(matrizOsos[x - 1][y - 1]);
                casillasJugada.add(matrizOsos[x + 1][y + 1]);
            }
        }

        if (0 < x && 0 < y && y < columnas - 1 && x < filas - 1 && matrizOsos[x - 1][y + 1].isValida() && matrizOsos[x + 1][y - 1].isValida()) {
            if (matrizOsos[x - 1][y + 1].getValor() == 'O' && matrizOsos[x + 1][y - 1].getValor() == 'O') {
                osos++;

                casillasJugada.add(matrizOsos[x][y]);
                casillasJugada.add(matrizOsos[x - 1][y + 1]);
                casillasJugada.add(matrizOsos[x + 1][y - 1]);
            }
        }
        return osos;
    }
    
    private int cuentaOsosO(int x, int y, int filas, int columnas) {
        int osos = 0;
        //horizontal
        if (1 < y && matrizOsos[x][y - 1].isValida() && matrizOsos[x][y - 2].isValida()) {
            if (matrizOsos[x][y - 1].getValor() == 'S' && matrizOsos[x][y - 2].getValor() == 'O') {
                osos++;
                casillasJugada.add(matrizOsos[x][y]);
                casillasJugada.add(matrizOsos[x][y - 1]);
                casillasJugada.add(matrizOsos[x][y - 2]);
            }
        }

        if (y < columnas - 2 && matrizOsos[x][y + 1].isValida() && matrizOsos[x][y + 2].isValida()) {
            if (matrizOsos[x][y + 1].getValor() == 'S' && matrizOsos[x][y + 2].getValor() == 'O') {
                osos++;

                casillasJugada.add(matrizOsos[x][y]);
                casillasJugada.add(matrizOsos[x][y + 1]);
                casillasJugada.add(matrizOsos[x][y + 2]);
            }
        }

        //vertical
        if (1 < x && matrizOsos[x - 1][y].isValida() && matrizOsos[x - 2][y].isValida()) {
            if (matrizOsos[x - 1][y].getValor() == 'S' && matrizOsos[x - 2][y].getValor() == 'O') {
                osos++;
                casillasJugada.add(matrizOsos[x][y]);
                casillasJugada.add(matrizOsos[x - 1][y]);
                casillasJugada.add(matrizOsos[x - 2][y]);
            }
        }

        if (x < filas - 2 && matrizOsos[x + 1][y].isValida() && matrizOsos[x + 2][y].isValida()) {
            if (matrizOsos[x + 1][y].getValor() == 'S' && matrizOsos[x + 2][y].getValor() == 'O') {
                osos++;
                casillasJugada.add(matrizOsos[x][y]);
                casillasJugada.add(matrizOsos[x + 1][y]);
                casillasJugada.add(matrizOsos[x + 2][y]);
            }
        }

        //diagonal
        if (1 < x && 1 < y && matrizOsos[x - 1][y - 1].isValida() && matrizOsos[x - 2][y - 2].isValida()) {
            if (matrizOsos[x - 1][y - 1].getValor() == 'S' && matrizOsos[x - 2][y - 2].getValor() == 'O') {
                osos++;
                casillasJugada.add(matrizOsos[x][y]);
                casillasJugada.add(matrizOsos[x - 1][y - 1]);
                casillasJugada.add(matrizOsos[x - 2][y - 2]);
            }
        }

        if (x < filas - 2 && y < columnas - 2 && matrizOsos[x + 1][y + 1].isValida() && matrizOsos[x + 2][y + 2].isValida()) {
            if (matrizOsos[x + 1][y + 1].getValor() == 'S' && matrizOsos[x + 2][y + 2].getValor() == 'O') {
                osos++;
                casillasJugada.add(matrizOsos[x][y]);
                casillasJugada.add(matrizOsos[x + 1][y + 1]);
                casillasJugada.add(matrizOsos[x + 2][y + 2]);
            }
        }

        if (1 < x && y < columnas - 2 && matrizOsos[x - 1][y + 1].isValida() && matrizOsos[x - 2][y + 2].isValida()) {
            if (matrizOsos[x - 1][y + 1].getValor() == 'S' && matrizOsos[x - 2][y + 2].getValor() == 'O') {
                osos++;
                casillasJugada.add(matrizOsos[x][y]);
                casillasJugada.add(matrizOsos[x - 1][y + 1]);
                casillasJugada.add(matrizOsos[x - 2][y + 2]);
            }
        }

        if (1 < y && x < filas - 2 && matrizOsos[x + 1][y - 1].isValida() && matrizOsos[x + 2][y - 2].isValida()) {
            if (matrizOsos[x + 1][y - 1].getValor() == 'S' && matrizOsos[x + 2][y - 2].getValor() == 'O') {
                osos++;
                casillasJugada.add(matrizOsos[x][y]);
                casillasJugada.add(matrizOsos[x + 1][y - 1]);
                casillasJugada.add(matrizOsos[x + 2][y - 2]);
            }
        }
        return osos;
    }
}
