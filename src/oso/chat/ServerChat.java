package oso.chat;

import static java.lang.Thread.interrupted;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import oso.utils.ServerChatHilo;

public class ServerChat extends Thread{
    private final int port;
    private final List<ServerChatHilo> clients = new LinkedList<>();
    
    public static void main(String[] args) {
        ServerChat server = new ServerChat(16000);
        server.start();
    }   
    
    public ServerChat(int port) {
        this.port = port;
    }

    @Override
    public void run(){
        try( ServerSocket serverSocket = new ServerSocket(port); ){
        System.out.println("Servidor de chat iniciado en puerto: " + port);
            // repeatedly wait for connections
            while(! interrupted() ){
                Socket clientSocket = serverSocket.accept();
                ServerChatHilo clientThread = new ServerChatHilo(clientSocket, clients);
                clientThread.start();
            }
        }catch(Exception ex){
        }
    }
}
