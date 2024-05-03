
package oso.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import oso.core.BotonOso;
import oso.core.Casilla;
import oso.core.Jugada;
import oso.core.Partida;

public class osoGameIndividualGUI extends javax.swing.JFrame implements ActionListener {

    private BotonOso ultimoBotonOso, botonOsoActual;
    private Partida partidaOso;
    private final List<BotonOso> listaBotonesJugada = new ArrayList<>();
    
    public osoGameIndividualGUI() {
        initComponents();
        
        this.setResizable(false);
        
        setLocationRelativeTo(null);
        dialogFinPartida.setLocationRelativeTo(this);
        dialogFinPartida.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                disposeJuego();
            }
        });
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dialogFinPartida = new javax.swing.JDialog();
        labelFinPartida = new javax.swing.JLabel();
        labelResultados = new javax.swing.JLabel();
        botonFinal = new javax.swing.JButton();
        panelJuego = new javax.swing.JPanel();
        startButton = new javax.swing.JButton();
        fieldFilas = new javax.swing.JTextField();
        fieldColumnas = new javax.swing.JTextField();
        labelFilas = new javax.swing.JLabel();
        labelColumnas = new javax.swing.JLabel();
        labelOsos = new javax.swing.JLabel();
        fieldNumOsos = new javax.swing.JTextField();
        enviarButton = new javax.swing.JButton();

        dialogFinPartida.setMinimumSize(new java.awt.Dimension(300, 150));
        dialogFinPartida.setPreferredSize(new java.awt.Dimension(300, 150));
        dialogFinPartida.setResizable(false);

        labelFinPartida.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        labelFinPartida.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelFinPartida.setText("Fin de Partida!");

        labelResultados.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        labelResultados.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelResultados.setText("Total OSOs: ");

        botonFinal.setText("aceptar");
        botonFinal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonFinalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout dialogFinPartidaLayout = new javax.swing.GroupLayout(dialogFinPartida.getContentPane());
        dialogFinPartida.getContentPane().setLayout(dialogFinPartidaLayout);
        dialogFinPartidaLayout.setHorizontalGroup(
            dialogFinPartidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dialogFinPartidaLayout.createSequentialGroup()
                .addContainerGap(80, Short.MAX_VALUE)
                .addGroup(dialogFinPartidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dialogFinPartidaLayout.createSequentialGroup()
                        .addComponent(labelResultados, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(77, 77, 77))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dialogFinPartidaLayout.createSequentialGroup()
                        .addComponent(labelFinPartida)
                        .addGap(95, 95, 95))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dialogFinPartidaLayout.createSequentialGroup()
                        .addComponent(botonFinal)
                        .addGap(107, 107, 107))))
        );
        dialogFinPartidaLayout.setVerticalGroup(
            dialogFinPartidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dialogFinPartidaLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(labelFinPartida)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelResultados)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botonFinal)
                .addContainerGap(39, Short.MAX_VALUE))
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
            
            int sizeLetra = 0;
            if (filas*columnas < 81) 
                sizeLetra = 30;
            else    
                sizeLetra = 15;
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    BotonOso botonOso = new BotonOso(i, j);
                    botonOso.setFont(new Font("Arial", Font.PLAIN, sizeLetra));
                    botonOso.addActionListener(this);
                    panelJuego.add(botonOso);
                }
            }
            fieldFilas.setEnabled(false);
            fieldColumnas.setEnabled(false);
            
            panelJuego.revalidate();
            
            partidaOso.imprimeEstadoPartida();
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
            listaBotonesJugada.add(botonOsoActual);
            
            Jugada jugada = new Jugada(x, y, letra.charAt(0));
            partidaOso.realizaJugada(jugada);
            
            pintaBotones(partidaOso.getTablero().getCasillasJugada());
            partidaOso.getTablero().bloqueaJugada();
            panelJuego.revalidate();
            
            int numOsos = partidaOso.getNumOsos();
            fieldNumOsos.setText(String.valueOf(numOsos));
            partidaOso.imprimeEstadoPartida();
            botonOsoActual.setEnabled(false);
            botonOsoActual = null;
            
            if (partidaOso.finPartida()) {
                partidaOso.imprimeEstadoPartida();
                enviarButton.setEnabled(false);
                labelResultados.setText(labelResultados.getText() + partidaOso.getNumOsos());
                dialogFinPartida.setVisible(true);
            }
        }
    }//GEN-LAST:event_enviarButtonActionPerformed

    private void botonFinalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonFinalActionPerformed
        // TODO add your handling code here:
        disposeJuego();
    }//GEN-LAST:event_botonFinalActionPerformed

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

    private void pintaBotones(Set<Casilla> casillasJugada) {
        for (int i= 0; i< listaBotonesJugada.size(); i++) {
            BotonOso boton = listaBotonesJugada.get(i);
            for (Casilla casilla: casillasJugada) {
                if (casilla.getX() ==  boton.getFila() && casilla.getY() == boton.getColumna()) {
                    boton.setForeground(Color.blue);
                }
            }
        }
    }
    
    private void disposeJuego() {
        dispose();
        System.exit(0);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonFinal;
    private javax.swing.JDialog dialogFinPartida;
    private javax.swing.JButton enviarButton;
    private javax.swing.JTextField fieldColumnas;
    private javax.swing.JTextField fieldFilas;
    private javax.swing.JTextField fieldNumOsos;
    private javax.swing.JLabel labelColumnas;
    private javax.swing.JLabel labelFilas;
    private javax.swing.JLabel labelFinPartida;
    private javax.swing.JLabel labelOsos;
    private javax.swing.JLabel labelResultados;
    private javax.swing.JPanel panelJuego;
    private javax.swing.JButton startButton;
    // End of variables declaration//GEN-END:variables

}
