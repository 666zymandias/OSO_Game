/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package oso.game;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import oso.utils.ClienteChatHilo;
import oso.core.BotonOso;
import oso.core.Casilla;
import oso.core.EstadoJuego;
import oso.core.Jugada;
import oso.utils.CustomExceptionHandler;

/**
 *
 * @author felipe
 */
public class OsoGameMultijugadorGUI extends javax.swing.JFrame implements ActionListener {
    
    private BotonOso ultimoBotonOso, botonOsoActual;
    private final List<BotonOso> listaBotonesJugadas = new ArrayList<>();
    private EstadoJuego estado;

    private String hostJuego = "localhost";
    private String hostChat = "localhost";
    private int puertoJuego = 15000;
    private int puertoChat = 16000;
    
    private Socket socketJuego;
    private Socket socketChat;
    
    private DataInputStream dinChat;
    private DataOutputStream doutChat;
    private ObjectInputStream oinJuego;
    private ObjectOutputStream ooutJuego;
    
    private ClienteChatHilo leeChat;
    OsoGameHilo hiloJuego;
            
    private int filas;
    private int columnas;
    private String user = "";
    private int idJugador;
    
    private int ososPropios;
    private int ososRivales;
    
    
    public OsoGameMultijugadorGUI() {
        initComponents();
        init();
    }
    
    private void init() {
        this.setResizable(false);
        setLocationRelativeTo(null);
        
        dialogInicio.setLocationRelativeTo(null);
        dialogInicio.setVisible(true);
        dialogInicio.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                disposeJuego();
            }
        });
        
        dialogConfig.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                user = textUsuarioConfig.getText();
                
                hostJuego = textSvOSO.getText();
                puertoJuego = Integer.parseInt(textPortOso.getText());
                
                hostChat = textSvChat.getText();
                puertoChat = Integer.parseInt(textPortChat.getText());
                
                textoUsuario.setText(user);
            }
        });
        
        areaChat.setFocusable(false);
        textoMensaje.setFocusable(false);
        textoOsosPropios.setFocusable(false);
        textoOsosRivales.setFocusable(false);
        botonEnviarJugada.setEnabled(false);
        
        dialogFinPartida.setResizable(false);
        dialogFinPartida.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                disposeJuego();
            }
        });
        dialogFinPartida.setVisible(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dialogFinPartida = new javax.swing.JDialog();
        labelFinPartida = new javax.swing.JLabel();
        labelOsosPropiosFin = new javax.swing.JLabel();
        labelOsosRivalFin = new javax.swing.JLabel();
        botonFinPartida = new javax.swing.JButton();
        labelGanador = new javax.swing.JLabel();
        dialogInicio = new javax.swing.JDialog();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        mitemConectar1 = new javax.swing.JMenuItem();
        mitemConfig1 = new javax.swing.JMenuItem();
        dialogConfig = new javax.swing.JDialog();
        labelUserConfig = new javax.swing.JLabel();
        textUsuarioConfig = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        textSvOSO = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        textPortOso = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        textSvChat = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        textPortChat = new javax.swing.JTextField();
        labelUsuario = new javax.swing.JLabel();
        textoUsuario = new javax.swing.JTextField();
        botonConectar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        areaChat = new javax.swing.JTextArea();
        textoMensaje = new javax.swing.JTextField();
        botonEnviarMsg = new javax.swing.JButton();
        panelJuego = new javax.swing.JPanel();
        botonEnviarJugada = new javax.swing.JButton();
        textoOsosPropios = new javax.swing.JTextField();
        textoOsosRivales = new javax.swing.JTextField();
        labelOsosPropios = new javax.swing.JLabel();
        labelOsosRivales = new javax.swing.JLabel();

        dialogFinPartida.setTitle("Fin juego");
        dialogFinPartida.setMaximumSize(new java.awt.Dimension(340, 180));
        dialogFinPartida.setMinimumSize(new java.awt.Dimension(340, 180));

        labelFinPartida.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        labelFinPartida.setText("Fin de Partida !");

        labelOsosPropiosFin.setText("Tus OSOs conseguidos: ");

        labelOsosRivalFin.setText("OSOs rivales: ");

        botonFinPartida.setText("salir");
        botonFinPartida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonFinPartidaActionPerformed(evt);
            }
        });

        labelGanador.setText("Ha ganado: ");

        javax.swing.GroupLayout dialogFinPartidaLayout = new javax.swing.GroupLayout(dialogFinPartida.getContentPane());
        dialogFinPartida.getContentPane().setLayout(dialogFinPartidaLayout);
        dialogFinPartidaLayout.setHorizontalGroup(
            dialogFinPartidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dialogFinPartidaLayout.createSequentialGroup()
                .addGroup(dialogFinPartidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dialogFinPartidaLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(dialogFinPartidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelOsosRivalFin)
                            .addComponent(labelOsosPropiosFin)
                            .addComponent(labelGanador)))
                    .addGroup(dialogFinPartidaLayout.createSequentialGroup()
                        .addGap(130, 130, 130)
                        .addComponent(botonFinPartida))
                    .addGroup(dialogFinPartidaLayout.createSequentialGroup()
                        .addGap(112, 112, 112)
                        .addComponent(labelFinPartida)))
                .addContainerGap(116, Short.MAX_VALUE))
        );
        dialogFinPartidaLayout.setVerticalGroup(
            dialogFinPartidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dialogFinPartidaLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(labelFinPartida)
                .addGap(18, 18, 18)
                .addComponent(labelOsosPropiosFin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelOsosRivalFin)
                .addGap(7, 7, 7)
                .addComponent(labelGanador)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(botonFinPartida)
                .addContainerGap())
        );

        dialogInicio.setMinimumSize(new java.awt.Dimension(400, 300));

        jMenu2.setText("Partida");

        mitemConectar1.setText("Conectar");
        mitemConectar1.setMinimumSize(new java.awt.Dimension(104, 25));
        mitemConectar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mitemConectar1ActionPerformed(evt);
            }
        });
        jMenu2.add(mitemConectar1);

        mitemConfig1.setText("Configurar");
        mitemConfig1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mitemConfig1ActionPerformed(evt);
            }
        });
        jMenu2.add(mitemConfig1);

        jMenuBar2.add(jMenu2);

        dialogInicio.setJMenuBar(jMenuBar2);

        javax.swing.GroupLayout dialogInicioLayout = new javax.swing.GroupLayout(dialogInicio.getContentPane());
        dialogInicio.getContentPane().setLayout(dialogInicioLayout);
        dialogInicioLayout.setHorizontalGroup(
            dialogInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        dialogInicioLayout.setVerticalGroup(
            dialogInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 274, Short.MAX_VALUE)
        );

        dialogConfig.setTitle("Configuracion");
        dialogConfig.setMinimumSize(new java.awt.Dimension(300, 235));

        labelUserConfig.setText("Usuario");

        jLabel2.setText("Servidor OSO");

        textSvOSO.setText("127.0.0.1");

        jLabel3.setText(":");

        textPortOso.setText("15000");

        jLabel4.setText("Servidor Chat");

        textSvChat.setText("127.0.0.1");

        jLabel5.setText(":");

        textPortChat.setText("16000");

        javax.swing.GroupLayout dialogConfigLayout = new javax.swing.GroupLayout(dialogConfig.getContentPane());
        dialogConfig.getContentPane().setLayout(dialogConfigLayout);
        dialogConfigLayout.setHorizontalGroup(
            dialogConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dialogConfigLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(dialogConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4)
                    .addComponent(labelUserConfig)
                    .addComponent(textUsuarioConfig, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(dialogConfigLayout.createSequentialGroup()
                        .addComponent(textSvOSO, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textPortOso))
                    .addGroup(dialogConfigLayout.createSequentialGroup()
                        .addComponent(textSvChat, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textPortChat)))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        dialogConfigLayout.setVerticalGroup(
            dialogConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dialogConfigLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelUserConfig)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textUsuarioConfig, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(dialogConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textSvOSO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(textPortOso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(dialogConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textSvChat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(textPortChat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(43, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Juego del Oso Cliente");

        labelUsuario.setFont(new java.awt.Font("Liberation Sans", 0, 16)); // NOI18N
        labelUsuario.setText("Usuario:");

        botonConectar.setFont(new java.awt.Font("Liberation Sans", 0, 15)); // NOI18N
        botonConectar.setText("Conectar");
        botonConectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonConectarActionPerformed(evt);
            }
        });

        areaChat.setColumns(20);
        areaChat.setForeground(new java.awt.Color(0, 0, 255));
        areaChat.setRows(5);
        jScrollPane1.setViewportView(areaChat);

        botonEnviarMsg.setFont(new java.awt.Font("Liberation Sans", 0, 15)); // NOI18N
        botonEnviarMsg.setText("enviar");
        botonEnviarMsg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEnviarMsgActionPerformed(evt);
            }
        });

        panelJuego.setMinimumSize(new java.awt.Dimension(390, 390));
        panelJuego.setPreferredSize(new java.awt.Dimension(390, 390));

        javax.swing.GroupLayout panelJuegoLayout = new javax.swing.GroupLayout(panelJuego);
        panelJuego.setLayout(panelJuegoLayout);
        panelJuegoLayout.setHorizontalGroup(
            panelJuegoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 396, Short.MAX_VALUE)
        );
        panelJuegoLayout.setVerticalGroup(
            panelJuegoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 431, Short.MAX_VALUE)
        );

        botonEnviarJugada.setFont(new java.awt.Font("Liberation Sans", 0, 15)); // NOI18N
        botonEnviarJugada.setText("enviar");
        botonEnviarJugada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEnviarJugadaActionPerformed(evt);
            }
        });

        textoOsosPropios.setFont(new java.awt.Font("Liberation Sans", 0, 15)); // NOI18N
        textoOsosPropios.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        textoOsosPropios.setText("0");

        textoOsosRivales.setFont(new java.awt.Font("Liberation Sans", 0, 15)); // NOI18N
        textoOsosRivales.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        textoOsosRivales.setText("0");

        labelOsosPropios.setFont(new java.awt.Font("Liberation Sans", 0, 16)); // NOI18N
        labelOsosPropios.setForeground(new java.awt.Color(0, 153, 255));
        labelOsosPropios.setText("Osos propios");

        labelOsosRivales.setFont(new java.awt.Font("Liberation Sans", 0, 16)); // NOI18N
        labelOsosRivales.setForeground(new java.awt.Color(255, 0, 0));
        labelOsosRivales.setText("Osos rivales");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelUsuario)
                        .addGap(18, 18, 18)
                        .addComponent(textoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botonConectar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(botonEnviarJugada, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(140, 140, 140)
                                        .addComponent(labelOsosRivales))
                                    .addComponent(labelOsosPropios))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(textoOsosPropios, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                                    .addComponent(textoOsosRivales)))
                            .addComponent(panelJuego, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(textoMensaje)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botonEnviarMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(11, 11, 11))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelUsuario)
                    .addComponent(textoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonConectar))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelJuego, javax.swing.GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botonEnviarJugada)
                        .addGap(21, 21, 21))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textoOsosPropios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelOsosPropios))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textoOsosRivales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelOsosRivales)
                            .addComponent(textoMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botonEnviarMsg))
                        .addContainerGap(12, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonConectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonConectarActionPerformed
        
        
        user = textoUsuario.getText();
        if (! user.equals("")){
            
            botonConectar.setEnabled(false);
            
            try {
                socketJuego = new Socket(hostJuego, puertoJuego); //socket para las jugadas del tablero
                socketChat = new Socket(hostChat, puertoChat); //socket para el chat
                                
                //streams para juego
                ooutJuego = new ObjectOutputStream(socketJuego.getOutputStream());
                oinJuego = new ObjectInputStream(socketJuego.getInputStream());
                
                //streams para el chat
                doutChat = new DataOutputStream(socketChat.getOutputStream());
                dinChat = new DataInputStream(socketChat.getInputStream());
                              
            } catch (IOException | IllegalArgumentException | NullPointerException ex) {
                manejaExcepcion(ex);
            }
            
            leeInformacionInicial();
            
            
            //hilo para leer los nuevos estados
            hiloJuego = new OsoGameHilo(oinJuego);
            hiloJuego.start();
                
            //hilo de chat
            leeChat = new ClienteChatHilo(dinChat, areaChat);
            leeChat.start();
            
            try {
                doutChat.writeUTF("Usuario "+ user+ " conectado");
            } catch (IOException ex) {
                Logger.getLogger(OsoGameMultijugadorGUI.class.getName()).log(Level.SEVERE, null, ex);
            }

            textoUsuario.setFocusable(false);
            textoMensaje.setFocusable(true);
            
            if (estado.getTurnoActual() == idJugador) 
                desbloqueaTablero(true);
            
        }
       
    }//GEN-LAST:event_botonConectarActionPerformed

    private void botonEnviarMsgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEnviarMsgActionPerformed
        String texto = textoMensaje.getText();
        if (! texto.equals("")) {
            textoMensaje.setText("");
            String mensaje = user +" > "+ texto;
            try {
                doutChat.writeUTF(mensaje);
                doutChat.flush();
            } catch (IOException ex) {
                manejaExcepcion(ex);
            }
        }
    }//GEN-LAST:event_botonEnviarMsgActionPerformed

    private void botonEnviarJugadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEnviarJugadaActionPerformed
        // TODO add your handling code here:
        if (botonOsoActual != null) {
            int x = botonOsoActual.getFila();
            int y = botonOsoActual.getColumna();
            String letra = botonOsoActual.getText();
            
            botonOsoActual.setLetra(letra);
            
            Jugada jugada = new Jugada(x, y, letra.charAt(0), idJugador, user);
            
            try {
                ooutJuego.writeObject(jugada);
            } catch (IOException ex) {
                manejaExcepcion(ex);
            }
            botonOsoActual = null;
            desbloqueaTablero(false);
        }
    }//GEN-LAST:event_botonEnviarJugadaActionPerformed

    private void botonFinPartidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonFinPartidaActionPerformed
        disposeJuego();
    }//GEN-LAST:event_botonFinPartidaActionPerformed

    private void mitemConectar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mitemConectar1ActionPerformed
        // TODO add your handling code here:
        dialogInicio.dispose();
        botonConectarActionPerformed(null);
        this.setVisible(true);
    }//GEN-LAST:event_mitemConectar1ActionPerformed

    private void mitemConfig1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mitemConfig1ActionPerformed
       
        dialogConfig.setLocationRelativeTo(dialogInicio);
        dialogConfig.setVisible(true);
    }//GEN-LAST:event_mitemConfig1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OsoGameMultijugadorGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new OsoGameMultijugadorGUI();
        });
    }
    
    private void leeInformacionInicial() {
        try {
            idJugador = oinJuego.readInt();
            estado = (EstadoJuego) oinJuego.readObject();
            
            filas = estado.getPartidaOso().getTablero().getFilas();
            columnas = estado.getPartidaOso().getTablero().getColumnas();
            
            
            panelJuego.setLayout(new GridLayout(filas, columnas));
            
            int sizeLetra;
            if (filas * columnas < 81) {
                sizeLetra = 30;
            } else {
                sizeLetra = 15;
            }
            
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    BotonOso botonOso = new BotonOso(i, j);
                    botonOso.setFont(new Font("Arial", Font.PLAIN, sizeLetra));
                    botonOso.addActionListener(this);
                    panelJuego.add(botonOso);
                }
            }
            desbloqueaTablero(false);

            panelJuego.revalidate();
                        
        } catch (IOException | ClassNotFoundException ex) {
            manejaExcepcion(ex);
        }
    }
        
    @Override
    public void actionPerformed(ActionEvent ae) {

        ultimoBotonOso = botonOsoActual;
        botonOsoActual = (BotonOso) ae.getSource();
        if (ultimoBotonOso != null && ultimoBotonOso != botonOsoActual) {
            ultimoBotonOso.setText("");
        }
        if (botonOsoActual.getText().equals("") || botonOsoActual.getText().equals("S")) {
            botonOsoActual.setText("O");
        } else if (botonOsoActual.getText().equals("O")) {
            botonOsoActual.setText("S");
        }
    }
    
    private void pintaBotones(Set<Casilla> casillasJugada, Jugada jugada) {
        for (int i= 0; i< listaBotonesJugadas.size(); i++) {
            BotonOso boton = listaBotonesJugadas.get(i);
            for (Casilla casilla: casillasJugada) {
                if (casilla.getX() ==  boton.getFila() && casilla.getY() == boton.getColumna()) {
                    if (jugada.getJugador() == idJugador)
                        boton.setForeground(Color.blue);
                    else
                        boton.setForeground(Color.red);
                }
            }
        }
    }
    
    private void pintaLetraOso(Jugada jugada) {
        Component[] components = panelJuego.getComponents();
        for (Component component : components) {
            if (component instanceof BotonOso) {
                BotonOso botonOso = (BotonOso) component;
                if (botonOso.getFila() == jugada.getFila() && botonOso.getColumna() == jugada.getColumna()) {
                    listaBotonesJugadas.add(botonOso);
                    botonOso.setText(String.valueOf(jugada.getLetra()));
                    botonOso.setLetra(String.valueOf(jugada.getLetra()));
                    botonOso.setEnabled(false);
                    break;
                }
            }
        }
        panelJuego.revalidate();
    }
    
    private void pintaJugada(Jugada jugada) {
        
        pintaLetraOso(jugada);      
        pintaBotones(estado.getPartidaOso().getTablero().getCasillasJugada(), jugada);
        estado.getPartidaOso().getTablero().bloqueaJugada();
        
        panelJuego.revalidate();
        
        if (idJugador == 0) {
            ososPropios = estado.getPartidaOso().getOsosJ1();
            ososRivales = estado.getPartidaOso().getOsosJ2();
        }
        else {
            ososPropios = estado.getPartidaOso().getOsosJ2();
            ososRivales = estado.getPartidaOso().getOsosJ1();
        }
        
        textoOsosPropios.setText(String.valueOf(ososPropios));
        textoOsosRivales.setText(String.valueOf(ososRivales));
    }
    
    public void manejaExcepcion(Exception ex) {
        CustomExceptionHandler handler = new CustomExceptionHandler();
        Logger.getLogger(ServerGameMultijugador.class.getName()).log(Level.SEVERE, null, ex);
        handler.uncaughtException(Thread.currentThread(), ex);
        disposeJuego();
    }
    
    private void disposeJuego() {
        dialogFinPartida.dispose();
        dispose();
        System.exit(0);
    }
    
    private void desbloqueaTablero(boolean unlock) {
        botonEnviarJugada.setEnabled(unlock);
        Component[] components = panelJuego.getComponents();
        for (Component component : components) {
            if (component instanceof BotonOso) {
                BotonOso button = (BotonOso) component;
                if (! (button.getLetra().equals("S") || button.getLetra().equals("O")))
                    button.setEnabled(unlock);
            }
        }
    }
    
    public void addResultados(Jugada jugada) {
        String ganador = jugada.getNombrePJ();
        labelOsosPropiosFin.setText(labelOsosPropiosFin.getText() + ososPropios);
        labelOsosRivalFin.setText(labelOsosRivalFin.getText() + ososRivales);
        
        if (ososPropios == ososRivales) 
            ganador = "Empate";
        
        labelGanador.setText(labelGanador.getText() + ganador);
    }
    
    public void finalJuego(Jugada jugada) {
        addResultados(jugada);
        dialogFinPartida.setLocationRelativeTo(this);
        dialogFinPartida.setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea areaChat;
    private javax.swing.JButton botonConectar;
    private javax.swing.JButton botonEnviarJugada;
    private javax.swing.JButton botonEnviarMsg;
    private javax.swing.JButton botonFinPartida;
    private javax.swing.JDialog dialogConfig;
    private javax.swing.JDialog dialogFinPartida;
    private javax.swing.JDialog dialogInicio;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelFinPartida;
    private javax.swing.JLabel labelGanador;
    private javax.swing.JLabel labelOsosPropios;
    private javax.swing.JLabel labelOsosPropiosFin;
    private javax.swing.JLabel labelOsosRivalFin;
    private javax.swing.JLabel labelOsosRivales;
    private javax.swing.JLabel labelUserConfig;
    private javax.swing.JLabel labelUsuario;
    private javax.swing.JMenuItem mitemConectar1;
    private javax.swing.JMenuItem mitemConfig1;
    private javax.swing.JPanel panelJuego;
    private javax.swing.JTextField textPortChat;
    private javax.swing.JTextField textPortOso;
    private javax.swing.JTextField textSvChat;
    private javax.swing.JTextField textSvOSO;
    private javax.swing.JTextField textUsuarioConfig;
    private javax.swing.JTextField textoMensaje;
    private javax.swing.JTextField textoOsosPropios;
    private javax.swing.JTextField textoOsosRivales;
    private javax.swing.JTextField textoUsuario;
    // End of variables declaration//GEN-END:variables
   
    private class OsoGameHilo extends Thread {

        private final ObjectInputStream objectIS;

        public OsoGameHilo(ObjectInputStream objectIS) {
            this.objectIS = objectIS;
        }

        @Override
        public void run() {
            
            //jugadas
            try {
                for (Object input; (input = objectIS.readObject()) != null;) {
                    Jugada nuevaJugada = (Jugada) input;
                    estado.getPartidaOso().realizaJugada(nuevaJugada);
                    estado.setUltimaJugada(nuevaJugada);
                    estado.siguienteTurno();
                    pintaJugada(nuevaJugada);
                    if (estado.getTurnoActual() == idJugador)
                        desbloqueaTablero(true);
                    if (estado.getPartidaOso().finPartida()) 
                        break;
                }
                
                finalJuego(estado.getUltimaJugada());
                
            } catch (IOException | ClassNotFoundException ex) {
                manejaExcepcion(ex);
            }
            
        }
    }
}




