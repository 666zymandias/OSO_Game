package oso.chat;

import oso.chat.utils.EscupeTextoHilo;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClienteChatConsole {

    public static void main(String[] args) throws IOException {
        final String hostAddr = "127.0.0.1";
        final int port = 16000;
        final Scanner scanner = new Scanner(System.in);
        final Socket socket = new Socket(hostAddr, port);

        System.out.println("Socket connected to " + socket.getInetAddress() + ":" + socket.getPort());        
        System.out.println("Introduce tu usuario");
        String user = scanner.next();
        System.out.println("Type empty line for just receiving, exit for finishing the app");
        
        final DataInputStream in = new DataInputStream(socket.getInputStream());
        final DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        
        EscupeTextoHilo lectorChat = new EscupeTextoHilo(in);
        lectorChat.start();
        out.writeUTF("+ Usuario "+ user + " conectado");
        for(;;){
            String msg = scanner.nextLine();
            if(msg.equalsIgnoreCase("exit"))
                break;
            if (! msg.isEmpty()){ //we want to send
                out.writeUTF(user + " > " + msg); 
                out.flush(); //flush forces to send everything that may be in the buffer
            }
        }        

        lectorChat.interrupt();
    }
}
