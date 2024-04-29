
package oso.core;

import javax.swing.JButton;

/**
 *
 * @author alumno
 */
public class BotonOso extends JButton {
    private Integer fila;
    private Integer columna;
    private String letra;
    public BotonOso(int fila, int columna){
        this.fila = fila;
        this.columna = columna;
        this.letra = "";
        this.setText("");
    }
    
    public void setLetra(String val) {
        letra = val;
    }

    public void setFila(Integer fila) {
        this.fila = fila;
    }

    public void setColumna(Integer columna) {
        this.columna = columna;
    }

    public Integer getFila() {
        return fila;
    }

    public Integer getColumna() {
        return columna;
    }

    public String getLetra() {
        return letra;
    }
    
}
