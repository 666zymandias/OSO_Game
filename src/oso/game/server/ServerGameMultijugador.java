package oso.game.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import oso.chat.ServerChat;
import oso.core.EstadoJuego;
import oso.game.utils.GestorGanador;

/**
 *
 * @author felipe
 */
public class ServerGameMultijugador extends Thread{
    private final int puertoJuego = 15000;
    private final int puertoChat = 16000;
    
    private final int filas;
    private final int columnas;
    
    List<Socket> conexionesEsperando = new ArrayList<>();
    
    private final String FILE_NAME = "src/dataGanadores/ganadores.txt";
    
    private final GestorGanador gestorGanadores = new GestorGanador(FILE_NAME);
    
    public static void main(String[] args) {
        System.out.println("Introduce las medidas que tendrán los tableros de las partidas OSO:");
        ServerGameMultijugador server = new ServerGameMultijugador(getFilas(), getColumnas());
        server.start();
    }

    public ServerGameMultijugador(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
    }
    
    @Override
    public void run() {
        System.out.println("Tableros de juego de "+filas+"x"+columnas);
        
        ServerChat serverChat = new ServerChat(puertoChat);
        serverChat.start();
        
        try {
            
            ServerSocket serverSocket = new ServerSocket(puertoJuego);
            
            System.out.println("Servidor juego del OSO iniciado en puerto: " + puertoJuego);
                        
            while (! interrupted()) {
                
                Socket clientSocket = serverSocket.accept();
                System.out.println("Nueva conexion a juego desde " + 
                    clientSocket.getInetAddress() + " : " + clientSocket.getPort());
                System.out.println("");
                
                conexionesEsperando.add(clientSocket);
                
                if (conexionesEsperando.size() == 2) {
                    List<ServerJuegoHilo> clientesActivos = new LinkedList<>();
                    int jugador = 0;
                    EstadoJuego estadoJuego = new EstadoJuego(filas, columnas, jugador);
                    for (Socket cliente : conexionesEsperando) {
                        ServerJuegoHilo clientThread = new ServerJuegoHilo(clientesActivos, cliente, this, jugador, estadoJuego);
                        clientThread.start();
                        jugador ++;
                    }
                    conexionesEsperando.clear();
                }
            }           
        } 
        
        catch (IOException | SecurityException | IllegalArgumentException | NullPointerException ex) {
            Logger.getLogger(ServerGameMultijugador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public synchronized void cancelarPartida(List<ServerJuegoHilo> clientesActivos) {
        for (ServerJuegoHilo cliente : clientesActivos) {
            cliente.cerrarConexion();
            cliente.interrupt();
        }
        clientesActivos.clear();
    }

    public void clienteDesconectado(ServerJuegoHilo cliente) {
        synchronized (cliente.getClientesActivos()) {
            cliente.getClientesActivos().remove(cliente);
            cancelarPartida(cliente.getClientesActivos());
        }
        cliente.interrupt();
    }
    
    public void guardaInformacionGanador(String ganador) throws IOException {
        gestorGanadores.updatePlayerGamesWon(ganador);
    }
    
    public static int getFilas() {
        
        Scanner sc = new Scanner(System.in);
        boolean entradaValida = false;
        int filas = 0;
        while (!entradaValida) {
            try {
                System.out.println("Numero de filas: ");
                filas = sc.nextInt();
                if (filas > 2)
                    entradaValida = true; 
            } catch (InputMismatchException e) {
                sc.next(); // Limpiar el buffer del scanner
            }
        }
        return filas;
    }
    
    public static int getColumnas() {
        Scanner sc = new Scanner(System.in);
        int columnas = 0;
        boolean entradaValida = false;
        while (!entradaValida) {
            try {
                System.out.println("Numero de columnas: ");
                columnas = sc.nextInt();
                if (columnas > 2)
                    entradaValida = true; 
            } catch (InputMismatchException e) {
                sc.next(); // Limpiar el buffer del scanner
            }
        }
        
        return columnas;
    }

}
