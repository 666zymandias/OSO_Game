/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oso.core;

/**
 *
 * @author felipe
 */
public class Casilla {
    private char valor;
    private boolean Valida;

    public Casilla(char valor) {
        this.valor = valor;
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
    
    @Override
    public String toString() {
        return String.valueOf(getValor());
    }
}
