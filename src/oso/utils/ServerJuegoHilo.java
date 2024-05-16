package oso.utils;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import oso.core.EstadoJuego;
import oso.core.Jugada;
import oso.game.ServerGameMultijugador;

public class ServerJuegoHilo extends Thread{
    private final List<ServerJuegoHilo> clientes;
    private final Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private final int jugador;
    private final EstadoJuego estadoJuego;

    public ServerJuegoHilo(List<ServerJuegoHilo> clientes, Socket socket, int jugador, EstadoJuego estadoJuego) {
        this.clientes = clientes;
        this.socket = socket;
        this.jugador = jugador;
        this.estadoJuego = estadoJuego;
    }

    synchronized public void sendEstado(EstadoJuego estado) {
        try {
            out.writeObject(estado);
            out.flush();
        } catch (IOException ex ) {
            Logger.getLogger(ServerGameMultijugador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    synchronized public void sendJugada(Jugada jugada) {
        try {
            out.writeObject(jugada);
            out.flush();
        } catch (IOException ex ) {
            Logger.getLogger(ServerGameMultijugador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        try {
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());

            System.out.println("Conexion con cliente (" +jugador+ ") a juego desde " + 
                    socket.getInetAddress() + ": " + socket.getPort());
           
            synchronized (clientes) { 
                clientes.add(this);
            }
            
            sendInformacionInicial();

            //jugadas
            for (Object inputJugada; (inputJugada = in.readObject()) != null;) {

                try {
                    Jugada jugada = (Jugada) inputJugada;

                    synchronized (clientes) {
                        clientes.forEach(c -> c.sendJugada(jugada));
                    }

                }catch (Exception ex) {
                    Logger.getLogger(ServerGameMultijugador.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }catch (EOFException ex) {
            Logger.getLogger(ServerGameMultijugador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | SecurityException | IllegalArgumentException | NullPointerException | ClassNotFoundException ex) {
            Logger.getLogger(ServerGameMultijugador.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ServerGameMultijugador.class.getName()).log(Level.SEVERE, null, ex);
        }

        sendEstado(estadoJuego);

    }
}