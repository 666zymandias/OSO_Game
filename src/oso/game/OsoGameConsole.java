
package oso.game;

import java.util.InputMismatchException;
import java.util.Scanner;
import oso.core.Jugada;
import oso.core.Partida;

public class OsoGameConsole {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String cadena;
        int filas = 0, columnas = 0;
        boolean entradaValida = false;

        while (!entradaValida) {
            try {
                System.out.println("Introduce el numero de filas: ");
                filas = sc.nextInt();
                if (filas > 2)
                    entradaValida = true; 
            } catch (InputMismatchException e) {
                sc.next(); // Limpiar el buffer del scanner
            }
        }
        entradaValida = false;
        while (!entradaValida) {
            try {
                System.out.println("Introduce el numero de columnas: ");
                columnas = sc.nextInt();
                if (columnas > 2)
                    entradaValida = true; 
            } catch (InputMismatchException e) {
                sc.next(); // Limpiar el buffer del scanner
            }
        }
        
        Partida alone = new Partida(filas, columnas);
        alone.imprimeEstadoPartida();
        System.out.println("Introduce 'salir' para finalizar juego");
        
        while(!alone.finPartida()) {
            System.out.println("Introduce tu jugada (formato: <x>,<y>,<letra>)");
            cadena = sc.next();
            if (cadena.equals("salir"))
                break;
            if (cadena.length() == 5) {
                int x = cadena.charAt(0) - '0';
                int y = cadena.charAt(2) - '0';
                char letra = cadena.charAt(4);
                letra = Character.toUpperCase(letra);
                char coma1 = cadena.charAt(1);
                char coma2 = cadena.charAt(3);
                if (coma1 != ',' || coma2 != ',' || x < 0 || x >= filas || y < 0 || y >= columnas || (letra != 'S' && letra != 'O')) {
                    continue;
                }
                Jugada jugada = new Jugada(x, y, letra);
                alone.realizaJugada(jugada);
                alone.getTablero().bloqueaJugada();
            }
        }   
        
        System.out.println("Fin de partida!");
        System.out.println("Total de OSOs conseguidos: "+ alone.getNumOsos());
    }
    
}
