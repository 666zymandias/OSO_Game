
package oso.utils;

import java.io.DataInputStream;
import java.io.IOException;

public class EscupeTextoHilo extends Thread{

    protected final DataInputStream in;

    public EscupeTextoHilo(DataInputStream in) {
        this.in = in;
    }
    
    @Override
    public void run() {
        try {
            for (String line; (line = in.readUTF()) != null;) {
                System.out.println(line);
            }
        } catch (IOException ex) {
        }
    }
}
