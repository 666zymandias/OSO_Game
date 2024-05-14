/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package oso.game;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import oso.chat.ServidorChat;
import oso.core.EstadoJuego;
import oso.core.Jugada;

/**
 *
 * @author felipe
 */
public class ServidorJuegoMultijugador extends Thread{

    private final int portJuego = 12000;
    private final int filas;
    private final int columnas;
    final List<ClientThreadJuego> clientes = new LinkedList<>();
    private EstadoJuego estadoJuego;
    
    public static void main(String[] args) {
        final int portChat = 13000;
        ServidorJuegoMultijugador server = new ServidorJuegoMultijugador(3, 3);
        ServidorChat serverChat = new ServidorChat(portChat);
        serverChat.start();
        server.start();
    }

    public ServidorJuegoMultijugador(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
    }
    
    @Override
    public void run() {
        int jugador = 0;
        
        estadoJuego = new EstadoJuego(filas, columnas, 0, 0);
        
        try {
            ServerSocket serverSocket = new ServerSocket(portJuego);
            
            System.out.println("Servidor juego del OSO iniciado en puerto: " + portJuego);
            
            while (! estadoJuego.getPartidaOso().finPartida()) {
                
                if (estadoJuego.getTotalJugadores() < 2) {
                    Socket clientSocket = serverSocket.accept();
                    
                    ClientThreadJuego clientThread = new ClientThreadJuego(clientes, clientSocket, jugador, estadoJuego);
                    clientThread.start();
                    
                    jugador ++;
                    estadoJuego.aumentaJugadoresEn1();
                }
            }
        } catch (IOException | SecurityException | IllegalArgumentException | NullPointerException ex) {
            Logger.getLogger(ServidorJuegoMultijugador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public class ClientThreadJuego extends Thread{
        final List<ClientThreadJuego> clientes;
        final Socket socket;
        ObjectInputStream in;
        ObjectOutputStream out;
        int jugador;
        EstadoJuego estadoJuego;

        public ClientThreadJuego(List<ClientThreadJuego> clientes, Socket socket, int jugador, EstadoJuego estadoJuego) {
            this.clientes = clientes;
            this.socket = socket;
            this.jugador = jugador;
            this.estadoJuego = estadoJuego;
        }
        
        synchronized public void sendEstadoActual(EstadoJuego estado) {
            try {
                out.writeObject(estado);
                out.flush();
            } catch (IOException ex ) {
                Logger.getLogger(ServidorJuegoMultijugador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        @Override
        public void run() {
            try {
                in = new ObjectInputStream(socket.getInputStream());
                out = new ObjectOutputStream(socket.getOutputStream());
                
                System.out.println("Conexion con cliente (" +jugador+ ") a juego desde " + 
                        socket.getInetAddress() + ": " + socket.getPort());
                
                sendInformacionInicial();
                
                synchronized (clientes) { 
                    clientes.add(this);
                }

                for (Object inputJugada; (inputJugada = in.readObject()) != null;) {
                    
                    try {
                        Jugada jugada = (Jugada) inputJugada;
                        estadoJuego.getPartidaOso().realizaJugada(jugada);
                        estadoJuego.siguienteTurno();
                        
                        synchronized (clientes) {
                            clientes.forEach(c -> {
                                c.sendEstadoActual(estadoJuego);
                            });
                        }
                        
                    }catch (Exception ex) {
                    
                    }
                }

            } catch (IOException | SecurityException | IllegalArgumentException | NullPointerException | ClassNotFoundException ex) {
                Logger.getLogger(ServidorJuegoMultijugador.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try{ 
                    socket.close(); 
                } catch(IOException ex){
                }
                synchronized (clientes) {
                    clientes.remove(this);
                }
            }
        }

        private void sendInformacionInicial() {
            try {
                out.writeInt(jugador);
                out.flush();
            } catch (IOException ex) {
                Logger.getLogger(ServidorJuegoMultijugador.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            sendEstadoActual(estadoJuego);
            
        }
    }

}
