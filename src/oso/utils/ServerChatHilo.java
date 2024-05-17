package oso.utils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import oso.chat.ServerChat;

public class ServerChatHilo extends Thread{
    private final Socket socket;
    private DataOutputStream out;
    private final List<ServerChatHilo> clients;

    public ServerChatHilo(Socket socket, List<ServerChatHilo> clients) {
        this.socket = socket;
        this.clients = clients;
    }

    synchronized public void sendMsg(String msg){
        try {
            out.writeUTF(msg);
        } catch (IOException ex) {
            Logger.getLogger(ServerChat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        try {
            System.out.println("Conexion a chat desde " + 
                    socket.getInetAddress() + " : " + socket.getPort());

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
                System.out.println("Cliente de chat desconectado desde " + socket.getInetAddress() +" : "+ socket.getPort());
                clients.remove(this);
            }
        }
    }
}