
package oso.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import oso.core.BotonOso;
import oso.core.Casilla;
import oso.core.Jugada;
import oso.core.Partida;

/**
 *
 * @author alumno
 */
public class osoGameIndividualGUI extends javax.swing.JFrame implements ActionListener {

    private BotonOso ultimoBotonOso, botonOsoActual;
    private Partida partidaOso;
    private List<BotonOso> listaBotones = new ArrayList();
    
    public osoGameIndividualGUI() {
        initComponents();
        
        this.setResizable(false);
        
        setLocationRelativeTo(null);
        dialogFinPartida.setLocationRelativeTo(this);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dialogFinPartida = new javax.swing.JDialog();
        labelFinPartida = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        labelResultado = new javax.swing.JLabel();
        panelJuego = new javax.swing.JPanel();
        startButton = new javax.swing.JButton();
        fieldFilas = new javax.swing.JTextField();
        fieldColumnas = new javax.swing.JTextField();
        labelFilas = new javax.swing.JLabel();
        labelColumnas = new javax.swing.JLabel();
        labelOsos = new javax.swing.JLabel();
        fieldNumOsos = new javax.swing.JTextField();
        enviarButton = new javax.swing.JButton();

        dialogFinPartida.setTitle("Fin de partida");
        dialogFinPartida.setBackground(new java.awt.Color(255, 255, 255));
        dialogFinPartida.setMinimumSize(new java.awt.Dimension(375, 189));
        dialogFinPartida.setPreferredSize(new java.awt.Dimension(200, 150));
        dialogFinPartida.setResizable(false);

        labelFinPartida.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        labelFinPartida.setText("Fin partida!");

        jButton1.setText("vale");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        labelResultado.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        labelResultado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelResultado.setText("NumOsos");
        labelResultado.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout dialogFinPartidaLayout = new javax.swing.GroupLayout(dialogFinPartida.getContentPane());
        dialogFinPartida.getContentPane().setLayout(dialogFinPartidaLayout);
        dialogFinPartidaLayout.setHorizontalGroup(
            dialogFinPartidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dialogFinPartidaLayout.createSequentialGroup()
                .addContainerGap(96, Short.MAX_VALUE)
                .addGroup(dialogFinPartidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelResultado, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelFinPartida, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(69, 69, 69))
        );
        dialogFinPartidaLayout.setVerticalGroup(
            dialogFinPartidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dialogFinPartidaLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(labelFinPartida, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelResultado, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("OSO Individual");
        setBackground(new java.awt.Color(255, 255, 255));

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
                .addContainerGap(31, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(labelFilas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(fieldFilas, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(labelColumnas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fieldColumnas, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(startButton)
                        .addGap(33, 33, 33))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelOsos)
                                .addGap(4, 4, 4)
                                .addComponent(fieldNumOsos, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(enviarButton))
                            .addComponent(panelJuego, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(startButton)
                    .addComponent(fieldFilas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fieldColumnas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelFilas)
                    .addComponent(labelColumnas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelJuego, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            int filas = Integer.parseInt(fieldFilas.getText());
            int columnas = Integer.parseInt(fieldColumnas.getText());
            
            if (filas < 3 || columnas < 3) {
                fieldFilas.setText("");
                fieldColumnas.setText("");
                return;
            }
            startButton.setEnabled(false);

            partidaOso = new Partida(filas, columnas);
            panelJuego.setLayout(new GridLayout(filas,columnas));
            
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    BotonOso botonOso = new BotonOso(i, j);
                    botonOso.setFont(new Font("Arial", Font.PLAIN, 30));
                        
                    botonOso.addActionListener(this);
                    panelJuego.add(botonOso);
                }
            }
            fieldFilas.setEnabled(false);
            fieldColumnas.setEnabled(false);
            
            panelJuego.revalidate();
            
            partidaOso.imprimeTablero();
        }catch(NumberFormatException ex) {
            startButton.setEnabled(true);
        }
    }//GEN-LAST:event_startButtonActionPerformed

    private void enviarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enviarButtonActionPerformed
        if (botonOsoActual != null) {
            int x = botonOsoActual.getFila();
            int y = botonOsoActual.getColumna();
            String letra = botonOsoActual.getText();
            
            listaBotones.add(botonOsoActual);
            botonOsoActual.setLetra(letra);
            Jugada jugada = new Jugada(x, y, letra.charAt(0));
            partidaOso.realizaJugada(jugada);
            pintaBotones(partidaOso.getCasillasJugada());
            panelJuego.revalidate();
            partidaOso.vaciaLista();
            
            int numOsos = partidaOso.getNumOsos();
            fieldNumOsos.setText(String.valueOf(numOsos));
            partidaOso.imprimeTablero();
            botonOsoActual.setEnabled(false);
            botonOsoActual = null;
            
            if (partidaOso.finPartida()) {
                enviarButton.setEnabled(false);
                labelResultado.setText("Total de OSOs conseguidos: "+ String.valueOf(partidaOso.getNumOsos()));
                dialogFinPartida.setVisible(true);
                
            }
        }
    }//GEN-LAST:event_enviarButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
            System.exit(0);
    }//GEN-LAST:event_jButton1ActionPerformed

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
    private javax.swing.JDialog dialogFinPartida;
    private javax.swing.JButton enviarButton;
    private javax.swing.JTextField fieldColumnas;
    private javax.swing.JTextField fieldFilas;
    private javax.swing.JTextField fieldNumOsos;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel labelColumnas;
    private javax.swing.JLabel labelFilas;
    private javax.swing.JLabel labelFinPartida;
    private javax.swing.JLabel labelOsos;
    private javax.swing.JLabel labelResultado;
    private javax.swing.JPanel panelJuego;
    private javax.swing.JButton startButton;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent ae) {
        ultimoBotonOso = botonOsoActual;
        botonOsoActual = (BotonOso) ae.getSource();
        if (ultimoBotonOso != null && ultimoBotonOso != botonOsoActual)
            ultimoBotonOso.setText("");
        if (botonOsoActual.getText().equals("") || botonOsoActual.getText().equals("S"))
            botonOsoActual.setText("O");
        else if (botonOsoActual.getText().equals("O"))
            botonOsoActual.setText("S");
    }

    private void pintaBotones(List<Casilla> casillasJugada) {
        for (int i = 0; i< listaBotones.size(); i++) {
            Casilla casilla = casillasJugada.get(i);
            BotonOso boton = listaBotones.get(i);
            if (casilla.getX() ==  boton.getFila() && casilla.getY() == boton.getColumna()) {
                boton.setForeground(Color.blue);
            }
        }
    }
}
