package oso.chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import static java.lang.Thread.interrupted;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServidorChat extends Thread{
    private final int port;
    private final List<ClientThread> clients = new LinkedList<>();
    
    public static void main(String[] args) {
        ServidorChat server = new ServidorChat(16000);
        server.start();
    }   
    
    public ServidorChat(int port) {
        this.port = port;
    }

    @Override
    public void run(){
        try( ServerSocket serverSocket = new ServerSocket(port); ){
        System.out.println("Servidor de chat iniciado en puerto: " + port);
            // repeatedly wait for connections
            while(! interrupted() ){
                Socket clientSocket = serverSocket.accept();
                ClientThread clientThread = new ClientThread(clientSocket, clients);
                clientThread.start();
            }
        }catch(Exception ex){
        }
    }
    
    public class ClientThread extends Thread{
        private final Socket socket;
        private DataOutputStream out;
        private final List<ClientThread> clients;

        public ClientThread(Socket socket, List<ClientThread> clients) {
            this.socket = socket;
            this.clients = clients;
        }
        
        //only one thread at the time can send messages through the socket
        synchronized public void sendMsg(String msg){
            try {
                out.writeUTF(msg);
            } catch (IOException ex) {
                Logger.getLogger(ServidorChat.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        @Override
        public void run() {
            try {
                System.out.println("Conexion a chat desde " + 
                        socket.getInetAddress() + ": " + socket.getPort());
                
                DataInputStream in = new DataInputStream(socket.getInputStream());
                out = new DataOutputStream(socket.getOutputStream());

                synchronized (clients) {
                    clients.add(this);
                }

                for (String line; (line = in.readUTF()) != null;) {
                    try {
                        String msg = line;
                        synchronized (clients) {
                            clients.forEach(c -> c.sendMsg(msg));
                        }
                    }catch (Exception ex) {
                    }
                }

            } catch (IOException ex) {
            } finally { 
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
