package oso.utils;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class CustomExceptionHandler implements Thread.UncaughtExceptionHandler {
    private JFrame parent;
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        JOptionPane.showMessageDialog(null, "Se ha producido un error:\n" + e.toString() + "\nAbortando cliente",
                "Error Cliente OSO", JOptionPane.ERROR_MESSAGE);
    }
}