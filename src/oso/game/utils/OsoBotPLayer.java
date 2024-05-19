/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oso.game.utils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import oso.core.EstadoJuego;
import oso.core.Jugada;

/**
 *
 * @author felipe
 */
public class OsoBotPLayer {
    private static int idJugador;
    
    public static void main(String[] args) {
        String hostJuego = "localhost";
        int puerto = 15000;
        try {
            
            Socket socketBot = new Socket(hostJuego, puerto);
            
            ObjectOutputStream oOutJuego = new ObjectOutputStream(socketBot.getOutputStream());
            ObjectInputStream oinJuego = new ObjectInputStream(socketBot.getInputStream());
            
            try {
                //leer informacion inicial
                System.out.println("Inicio bot");
                
                idJugador = oinJuego.readInt();
                EstadoJuego estado = (EstadoJuego) oinJuego.readObject();
                
                while (! estado.getPartidaOso().finPartida()) {
                    
                    estado.getPartidaOso().imprimeEstadoPartida();
                    
                    if (miTurno(estado)) {
                        Jugada miJugada = jugadaRandom(estado);
                        estado.getPartidaOso().realizaJugada(miJugada);
                        oOutJuego.writeObject(miJugada);
                        estado.siguienteTurno();
                    }
                    else {
                        Jugada jugada = (Jugada) oinJuego.readObject();
                        if (jugada != null & jugada.getJugador() != idJugador) {
                            actualizaNombreRival(estado, jugada);
                            estado.getPartidaOso().realizaJugada(jugada);
                            estado.setUltimaJugada(jugada);
                            estado.siguienteTurno();
                        }
                    }
                }
                
                estado.getPartidaOso().imprimeEstadoPartida();
                
                System.out.println("Fin bot");
                
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(OsoBotPLayer.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            Logger.getLogger(OsoBotPLayer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static boolean miTurno(EstadoJuego estado) {
        return estado.getTurnoActual() == idJugador;
    }

    private static Jugada jugadaRandom(EstadoJuego estado) {
        char[] jugadas = {'S', 'O'};
        Random random = new Random();
        
        int fila = random.nextInt(estado.getPartidaOso().getTablero().getFilas());
        int columna = random.nextInt(estado.getPartidaOso().getTablero().getColumnas());
        int letraPos = random.nextInt(2);
        
        Jugada jugada = new Jugada(fila, columna, jugadas[letraPos], idJugador, "bot", false);
        
        while (estado.getPartidaOso().esValida(jugada) == false) {
            
            fila = random.nextInt(estado.getPartidaOso().getTablero().getFilas());
            columna = random.nextInt(estado.getPartidaOso().getTablero().getColumnas());
            letraPos = random.nextInt(2);
            jugada = new Jugada(fila, columna, jugadas[letraPos], idJugador, "bot", false);
        }
        return jugada;
    }
    
    public static void actualizaNombreRival(EstadoJuego estado, Jugada jugada) {
        if (idJugador == 0 && estado.getJ2() == null) {
            if (jugada.getJugador() != idJugador) {
                estado.setJ2(jugada.getNombrePJ());
            }
        }

        if (idJugador == 1 && estado.getJ1() == null) {
            if (jugada.getJugador() != idJugador) {
                estado.setJ1(jugada.getNombrePJ());
            }
        }
    }
}
