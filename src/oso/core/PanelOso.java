/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oso.core;

import javax.swing.JPanel;

/**
 *
 * @author felipe
 */
public class PanelOso {
    private boolean tuTurno;
    private JPanel panelJuego;

    public PanelOso() {
        panelJuego = new javax.swing.JPanel();
        panelJuego.setBackground(new java.awt.Color(255, 255, 255));
        panelJuego.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelJuego.setMinimumSize(new java.awt.Dimension(32767, 32767));

        javax.swing.GroupLayout panelJuegoLayout = new javax.swing.GroupLayout(panelJuego);
        panelJuego.setLayout(panelJuegoLayout);
        panelJuegoLayout.setHorizontalGroup(
            panelJuegoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelJuegoLayout.setVerticalGroup(
            panelJuegoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
    }
    
}
