package oso.utils;

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
        final List<ServerJuegoHilo> clientes;
        final Socket socket;
        ObjectInputStream in;
        ObjectOutputStream out;
        int jugador;
        EstadoJuego estadoJuego;

        public ServerJuegoHilo(List<ServerJuegoHilo> clientes, Socket socket, int jugador, EstadoJuego estadoJuego) {
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
            
            sendEstadoActual(estadoJuego);
            
        }
    }