
package oso.game;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import oso.core.BotonOso;
import oso.core.Jugada;
import oso.core.Partida;

/**
 *
 * @author alumno
 */
public class osoGameIndividualGUI extends javax.swing.JFrame implements ActionListener {

    private BotonOso ultimoBotonOso, botonOsoActual;
    private Partida partidaOso;
    
    public osoGameIndividualGUI() {
        initComponents();
        setLocationRelativeTo(null);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelJuego = new javax.swing.JPanel();
        startButton = new javax.swing.JButton();
        fieldFilas = new javax.swing.JTextField();
        fieldColumnas = new javax.swing.JTextField();
        labelFilas = new javax.swing.JLabel();
        labelColumnas = new javax.swing.JLabel();
        labelOsos = new javax.swing.JLabel();
        fieldNumOsos = new javax.swing.JTextField();
        enviarButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        panelJuego.setBackground(new java.awt.Color(255, 255, 255));
        panelJuego.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelJuego.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        panelJuego.setMinimumSize(new java.awt.Dimension(32767, 32767));
        panelJuego.setPreferredSize(new java.awt.Dimension(32767, 32767));

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

        startButton.setText("Empezar");
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });

        labelFilas.setText("Filas:");

        labelColumnas.setText("Columnas:");

        labelOsos.setText("Numero de osos: ");

        fieldNumOsos.setText("0");
        fieldNumOsos.setEnabled(false);
        fieldNumOsos.setFocusable(false);

        enviarButton.setText("enviar");
        enviarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enviarButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelJuego, javax.swing.GroupLayout.PREFERRED_SIZE, 495, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 115, Short.MAX_VALUE)
                        .addComponent(labelFilas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(fieldFilas, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(labelColumnas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fieldColumnas, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(startButton))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelOsos)
                        .addGap(4, 4, 4)
                        .addComponent(fieldNumOsos, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(enviarButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(startButton)
                    .addComponent(fieldFilas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fieldColumnas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelFilas)
                    .addComponent(labelColumnas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelJuego, javax.swing.GroupLayout.PREFERRED_SIZE, 423, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelOsos)
                    .addComponent(fieldNumOsos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(enviarButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
        try {
            startButton.setEnabled(false);
            int filas = Integer.parseInt(fieldFilas.getText());
            int columnas = Integer.parseInt(fieldColumnas.getText());

            partidaOso = new Partida(filas, columnas);
            panelJuego.setLayout(new GridLayout(filas,columnas));
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    BotonOso botonOso = new BotonOso(i, j);
                    botonOso.addActionListener(this);
                    panelJuego.add(botonOso);
                }
            }
            fieldFilas.setEnabled(false);
            fieldColumnas.setEnabled(false);
            partidaOso.imprimeTablero();
            panelJuego.revalidate();
        }catch(NumberFormatException ex) {
            startButton.setEnabled(true);
        }
    }//GEN-LAST:event_startButtonActionPerformed

    private void enviarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enviarButtonActionPerformed
        if (botonOsoActual != null) {
            int x = botonOsoActual.getFila();
            int y = botonOsoActual.getColumna();
            String letra = botonOsoActual.getText();
            botonOsoActual.setLetra(letra);
            Jugada jugada = new Jugada(x, y, letra);
            partidaOso.realizaJugada(jugada);
            int numOsos = partidaOso.getNumOsos();
            fieldNumOsos.setText(String.valueOf(numOsos));
            partidaOso.imprimeTablero();
            botonOsoActual.setEnabled(false);
            botonOsoActual = null;
        }
    }//GEN-LAST:event_enviarButtonActionPerformed

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(osoGameIndividualGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> {
            new osoGameIndividualGUI().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton enviarButton;
    private javax.swing.JTextField fieldColumnas;
    private javax.swing.JTextField fieldFilas;
    private javax.swing.JTextField fieldNumOsos;
    private javax.swing.JLabel labelColumnas;
    private javax.swing.JLabel labelFilas;
    private javax.swing.JLabel labelOsos;
    private javax.swing.JPanel panelJuego;
    private javax.swing.JButton startButton;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent ae) {
        ultimoBotonOso = botonOsoActual;
        botonOsoActual = (BotonOso) ae.getSource();
        if (ultimoBotonOso != null && ultimoBotonOso != botonOsoActual)
            ultimoBotonOso.setText("");
        if (botonOsoActual.getText().equals("") || botonOsoActual.getText().equals("O"))
            botonOsoActual.setText("S");
        else if (botonOsoActual.getText().equals("S"))
            botonOsoActual.setText("O");
    }
}
