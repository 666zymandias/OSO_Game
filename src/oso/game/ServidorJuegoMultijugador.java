/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package oso.game;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.DataOutputStream;
import static java.lang.Thread.interrupted;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import oso.chat.ServidorChat;
import oso.core.EstadoJuego;
import oso.core.Jugada;
import oso.core.Partida;

/**
 *
 * @author felipe
 */
public class ServidorJuegoMultijugador extends Thread{

    private final int portJuego = 15000;
    private final int filas;
    private final int columnas;
    private int numConexiones = 0;
    final List<ClientThreadJuego> clients = new LinkedList<>();
    private EstadoJuego estadoJuego;
    
    public static void main(String[] args) {
        final int portChat = 16000;
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
        
        estadoJuego = new EstadoJuego(filas, columnas, 0, 0, 0);
        
        try (ServerSocket serverSocket = new ServerSocket(portJuego);) {
            
            System.out.println("Servidor de juego iniciado en puerto: " + portJuego);
            // repeatedly wait for connections
            while (numConexiones < 2) {
                Socket clientSocket = serverSocket.accept();
                
                ClientThreadJuego clientThread = new ClientThreadJuego(clientSocket);
                clientThread.start();
                numConexiones += 1;
            }
        } catch (Exception ex) {
        }
         
        while (! interrupted()) {
            
        
        }
    
    }
    
    public class ClientThreadJuego extends Thread{
        final Socket socket;
        ObjectInputStream in;
        ObjectOutputStream out;

        public ClientThreadJuego(Socket socket) {
            this.socket = socket;
        }
        
        synchronized public void sendInformacionInicial() throws IOException {
            DataOutputStream outaux = new DataOutputStream(socket.getOutputStream());
            String mensaje = filas + ", " + columnas, "";
            outaux.writeUTF(mensaje);
            outaux.flush();
        }
                
        synchronized public void sendJugada(Jugada jugada){
            try {
                out.writeObject(jugada);
            } catch (IOException ex) {
                Logger.getLogger(ServidorChat.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        @Override
        public void run() {
            try {
                System.out.println("Conexion a juego desde " + 
                        socket.getInetAddress() + ": " + socket.getPort());
                sendInformacionInicial(filas, columnas);
                
                in = new ObjectInputStream(socket.getInputStream());
                out = new ObjectOutputStream(socket.getOutputStream());

                synchronized (clients) { 
                    clients.add(this);
                }

                for (Object object; (object = in.readObject()) != null;) {
                    try {
                        Jugada jugada = (Jugada) object;
                        synchronized (clients) { //other clients may be trying to add to the list
                            clients.forEach(c -> {
                                c.sendJugada(jugada);
                            });
                        }
                    }catch (Exception ex) {
                    }
                }

            } catch (IOException ex) {
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ServidorJuegoMultijugador.class.getName()).log(Level.SEVERE, null, ex);
            } finally { //we have finished or failed so let's close the socket and remove ourselves from the list
                try{ 
                    socket.close(); 
                } catch(IOException ex){
                }
                synchronized (clients) {
                    clients.remove(this);
                }
            }
        }
    }

}
