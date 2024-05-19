package oso.game.utils;
import java.awt.Component;
import javax.swing.JOptionPane;

public class CustomExceptionHandler implements Thread.UncaughtExceptionHandler {

    private final String info;
    private String mensaje;
    private int tipoMensaje;
    private final Component padre;
    
    public CustomExceptionHandler(String infoAdicional, Component padre, int tipo) {
        this.info = infoAdicional;
        this.padre = padre;
        this.tipoMensaje = tipo;
    }
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        if (info != null)
            mensaje = info;
        else {
            mensaje = e.toString();
            tipoMensaje = JOptionPane.ERROR_MESSAGE;
        }
        JOptionPane.showMessageDialog(padre, mensaje + "\nAbortando cliente...", "Aviso", tipoMensaje);
    }
}