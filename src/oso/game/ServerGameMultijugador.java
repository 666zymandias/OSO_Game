/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package oso.game;

import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
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

    private final int puertoJuego = 15000;
    private final int puertoChat = 16000;
    
    private final int filas;
    private final int columnas;
    
    final List<ServerJuegoHilo> clientes = new LinkedList<>();
    private EstadoJuego estadoJuego;
    
    public static void main(String[] args) {
        
        ServerGameMultijugador server = new ServerGameMultijugador(getFilas(), getColumnas());
        
        server.start();
    }

    public ServerGameMultijugador(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
    }
    
    @Override
    public void run() {
        int jugador = 0;
        System.out.println("Tableros de juego de "+filas+"x"+columnas);
        
        ServerChat serverChat = new ServerChat(puertoChat);
        serverChat.start();
        
        estadoJuego = new EstadoJuego(filas, columnas, jugador, 0);
        
        try {
            
            ServerSocket serverSocket = new ServerSocket(puertoJuego);
            
            System.out.println("Servidor juego del OSO iniciado en puerto: " + puertoJuego);
            
            while (! interrupted()) {
                
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
    
    public static int getFilas() {
        
        Scanner sc = new Scanner(System.in);
        boolean entradaValida = false;
        int filas = 0;
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
        return filas;
    }
    
    public static int getColumnas() {
        Scanner sc = new Scanner(System.in);
        int columnas = 0;
        boolean entradaValida = false;
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
        
        return columnas;
    }

}
