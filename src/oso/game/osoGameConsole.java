
package oso.game;

import java.util.Scanner;
import oso.core.Jugada;
import oso.core.Partida;

/**
 *
 * @author hydfe
 */
public class osoGameConsole {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String cadena;
        
        System.out.println("Introduce el numero de filas: ");
        int filas = sc.nextInt();
        System.out.println("Introduce el numero de columnas: ");
        int columnas = sc.nextInt();
        
        Partida alone = new Partida(filas, columnas);
        System.out.println("Introduce 'salir' para finalizar juego");
        
        while(!alone.finPartida()) {
            System.out.println("Numero de osos: " +alone.getNumOsos());
            System.out.println("Introduce tu jugada (formato: <x>,<y>,<letra>");
            cadena = sc.next();
            if (cadena.equals("salir"))
                break;
            if (cadena.length() == 5) {
                int x = cadena.charAt(0) - '0';
                int y = cadena.charAt(2) - '0';
                char letra = cadena.charAt(4);
                Jugada jugada = new Jugada(x, y, Character.toString(letra));
                alone.realizaJugada(jugada);
                alone.imprimeTablero();
                System.out.println("Numero de osos: " +alone.getNumOsos());
            }
        }   
    }
    
}
