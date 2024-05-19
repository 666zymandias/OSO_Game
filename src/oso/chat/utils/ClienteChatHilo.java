/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oso.chat.utils;

import java.io.DataInputStream;
import java.io.IOException;
import javax.swing.JTextArea;

/**
 *
 * @author felipe
 */
public class ClienteChatHilo extends EscupeTextoHilo {
    
    private final JTextArea areaChat;
    
    public ClienteChatHilo(DataInputStream in, JTextArea areaChat) {
            super(in);
            this.areaChat = areaChat;
        }

    @Override
    public void run() {
        try {
            for (Object line; (line = in.readUTF()) != null;) {
                areaChat.append(line + "\n"); 
            }
        } catch (IOException ex) {
        }
    }
    
}
