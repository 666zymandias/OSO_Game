package oso.game.server;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import oso.core.EstadoJuego;
import oso.core.Jugada;
import oso.game.server.ServerGameMultijugador;

public class ServerJuegoHilo extends Thread{
    private final List<ServerJuegoHilo> clientesActivos;
    private final Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private final int jugador;
    private final EstadoJuego estadoJuego;
    private final ServerGameMultijugador servidor;

    public ServerJuegoHilo(List<ServerJuegoHilo> clientes, Socket socket, ServerGameMultijugador servidor, int jugador, EstadoJuego estadoJuego) {
        this.clientesActivos = clientes;
        this.socket = socket;
        this.jugador = jugador;
        this.estadoJuego = estadoJuego;
        this.servidor = servidor;
    }
    
    @Override
    public void run() {
        try {
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
            
            synchronized (clientesActivos) { 
                clientesActivos.add(this);
            }
            
            sendInformacionInicial();

            for (Object inputJugada; (inputJugada = in.readObject()) != null;) {
                try {
                    Jugada jugada = (Jugada) inputJugada;

                    synchronized (clientesActivos) {
                        clientesActivos.forEach(c -> c.sendJugada(jugada));
                    }
                    
                    if(jugada.isEsFinalPartida() && ! jugada.getNombreGanador().equals("Empate")) {
                        servidor.guardaInformacionGanador(jugada.getNombreGanador());
                    }

                }catch (IOException ex) {
                    Logger.getLogger(ServerGameMultijugador.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
        catch (EOFException | SocketException ex) {
            System.out.println("Cliente de juego desconectado desde "+socket.getInetAddress() + " : " + socket.getPort());
        } catch (IOException | SecurityException | IllegalArgumentException | NullPointerException | ClassNotFoundException ex) {
            Logger.getLogger(ServerGameMultijugador.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
            servidor.clienteDesconectado(this);
        }
    }

    public List<ServerJuegoHilo> getClientesActivos() {
        return clientesActivos;
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
    private void sendInformacionInicial() {
        try {
            out.writeInt(jugador);
            out.flush();
        } catch (IOException ex) {
            Logger.getLogger(ServerGameMultijugador.class.getName()).log(Level.SEVERE, null, ex);
        }

        sendEstado(estadoJuego);
    }
    
    public void cerrarConexion() {
        try {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(ServerGameMultijugador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}