/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oso.core;

import java.io.Serializable;

/**
 *
 * @author felipe
 */
public class Casilla implements Serializable {
    private char valor;
    private boolean Valida;
    private int x;
    private int y;

    public Casilla(char valor, int x, int y) {
        this.valor = valor;
        this.x = x;
        this.y = y;
        this.Valida = true;
    }

    public char getValor() {
        return valor;
    }

    public boolean isValida() {
        return Valida;
    }

    public void setValor(char valor) {
        this.valor = valor;
    }

    public void setValida(boolean Valida) {
        this.Valida = Valida;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }  
    
    @Override
    public String toString() {
        return String.valueOf(getValor());
    }
    
}
