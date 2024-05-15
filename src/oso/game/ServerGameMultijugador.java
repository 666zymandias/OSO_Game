/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package oso.game;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import oso.chat.ServerChat;
import oso.core.EstadoJuego;
import oso.utils.ServerJuegoHilo;

/**
 *
 * @author felipe
 */
public class ServerGameMultijugador extends Thread{

    private final int portJuego = 15000;
    private final int filas;
    private final int columnas;
    final List<ServerJuegoHilo> clientes = new LinkedList<>();
    private EstadoJuego estadoJuego;
    
    public static void main(String[] args) {
        final int portChat = 16000;
        ServerGameMultijugador server = new ServerGameMultijugador(3, 3);
        ServerChat serverChat = new ServerChat(portChat);
        serverChat.start();
        server.start();
    }

    public ServerGameMultijugador(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
    }
    
    @Override
    public void run() {
        int jugador = 0;
        
        estadoJuego = new EstadoJuego(filas, columnas, jugador, 0);
        
        try {
            ServerSocket serverSocket = new ServerSocket(portJuego);
            
            System.out.println("Servidor juego del OSO iniciado en puerto: " + portJuego);
            
            while (! estadoJuego.getPartidaOso().finPartida()) {
                
                if (estadoJuego.getTotalJugadores() < 2) {
                    Socket clientSocket = serverSocket.accept();
                    
                    ServerJuegoHilo clientThread = new ServerJuegoHilo(clientes, clientSocket, jugador, estadoJuego);
                    clientThread.start();
                    
                    jugador ++;
                    estadoJuego.aumentaJugadoresEn1();
                }
            }
        } catch (IOException | SecurityException | IllegalArgumentException | NullPointerException ex) {
            Logger.getLogger(ServerGameMultijugador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
