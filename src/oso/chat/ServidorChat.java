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
    public static void main(String[] args) {
        ServidorChat server = new ServidorChat(16000);
        server.start();
    }
    
    final int port;
    final List<ClientThread> clients = new LinkedList<>();
    
    
    public ServidorChat(int port) {
        this.port = port;
    }

    @Override
    public void run(){
        try( ServerSocket serverSocket = new ServerSocket(port); ){
        System.out.println("Started server on port " + port);
            // repeatedly wait for connections
            while(! interrupted() ){
                Socket clientSocket = serverSocket.accept();
                ClientThread clientThread = new ClientThread(clients, clientSocket);
                clientThread.start();
            }
        }catch(Exception ex){
        }
    }
    
    public class ClientThread extends Thread{
        final List<ClientThread> clients;
        final Socket socket;
        DataOutputStream out;

        public ClientThread(List<ClientThread> clients, Socket socket) {
            this.clients = clients;
            this.socket = socket;
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
                System.out.println("Conexion desde" + 
                        socket.getInetAddress() + ": " + socket.getPort());
                
                DataInputStream in = new DataInputStream(socket.getInputStream());
                out = new DataOutputStream(socket.getOutputStream());

                synchronized (clients) { //we must sync because other clients may be iterating over it
                    clients.add(this);
                }

                for (String line; (line = in.readUTF()) != null;) {
                    try {
                        String msg = line;
                        synchronized (clients) { //other clients may be trying to add to the list
                            clients.forEach(c -> c.sendMsg(msg));
                        }
                    }catch (Exception ex) {
                    }
                }

            } catch (IOException ex) {
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
