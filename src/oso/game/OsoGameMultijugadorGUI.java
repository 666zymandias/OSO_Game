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
import oso.utils.ClienteChatThread;
import oso.core.BotonOso;
import oso.core.Casilla;
import oso.core.EstadoJuego;
import oso.core.Jugada;
import oso.core.Partida;
import oso.core.PartidaMultijugador;
import oso.utils.CustomExceptionHandler;

/**
 *
 * @author felipe
 */
public class OsoGameMultijugadorGUI extends javax.swing.JFrame implements ActionListener {
    
    private BotonOso ultimoBotonOso, botonOsoActual;
    private final List<BotonOso> listaBotonesJugadas = new ArrayList<>();
    private EstadoJuego estado;

    private final String hostAddr = "127.0.0.1";
    private final int portJuego = 12000;
    private final int portChat = 13000;
    private Socket socketJuego;
    private Socket socketChat;
    
    private DataInputStream inChat;
    private DataOutputStream outChat;
    private ObjectInputStream inJuego;
    private ObjectOutputStream outJuego;
    
    private ClienteChatThread leeChat;
    OsoGameThread hiloJuego;
            
    private int filas;
    private int columnas;
    private String user;
    private int idJugador;
    
    
    public OsoGameMultijugadorGUI() {
        initComponents();
        init();
    }
    
    private void init() {
        setLocationRelativeTo(null);
        areaChat.setFocusable(false);
        textoMensaje.setFocusable(false);
        textoOsosPropios.setEnabled(false);
        textoOsosRivales.setEnabled(false);
        botonEnviarJugada.setEnabled(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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
            .addGap(0, 390, Short.MAX_VALUE)
        );
        panelJuegoLayout.setVerticalGroup(
            panelJuegoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
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
        labelOsosPropios.setText("Osos propios");

        labelOsosRivales.setFont(new java.awt.Font("Liberation Sans", 0, 16)); // NOI18N
        labelOsosRivales.setText("Osos rivales");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
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
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textoOsosPropios, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textoOsosRivales, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(panelJuego, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE)
                    .addComponent(panelJuego, javax.swing.GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textoMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botonEnviarMsg)
                            .addComponent(botonEnviarJugada))
                        .addGap(21, 21, 21))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textoOsosPropios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelOsosPropios))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textoOsosRivales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelOsosRivales))
                        .addContainerGap(12, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonConectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonConectarActionPerformed
        user = textoUsuario.getText();
        if (! user.equals("")){
            
            botonConectar.setEnabled(false);
            
            try {
                socketJuego = new Socket(hostAddr, portJuego); //socket para las jugadas del tablero
                socketChat = new Socket(hostAddr, portChat); //socket para el chat
                                
                //streams para juego
                outJuego = new ObjectOutputStream(socketJuego.getOutputStream());
                inJuego = new ObjectInputStream(socketJuego.getInputStream());
                
                //streams para el chat
                outChat = new DataOutputStream(socketChat.getOutputStream());
                inChat = new DataInputStream(socketChat.getInputStream());
                              
            } catch (IOException | IllegalArgumentException | NullPointerException ex) {
                manejaExcepcion(ex);
            }
            
            leeInformacionInicial();
            
            //hilo para leer los nuevos estados
            hiloJuego = new OsoGameThread(inJuego);
            hiloJuego.start();
                
            //hilo de chat
            leeChat = new ClienteChatThread(inChat, areaChat);
            leeChat.start();
            
            try {
                outChat.writeUTF("Usuario "+ user+ " conectado");
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
                outChat.writeUTF(mensaje);
                outChat.flush();
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
            listaBotonesJugadas.add(botonOsoActual);
            
            Jugada jugada = new Jugada(x, y, letra.charAt(0), idJugador);
            
            try {
                outJuego.writeObject(jugada);
            } catch (IOException ex) {
                manejaExcepcion(ex);
            }
            
            desbloqueaTablero(false);
        }
    }//GEN-LAST:event_botonEnviarJugadaActionPerformed

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
            new OsoGameMultijugadorGUI().setVisible(true);
        });
    }
    
    private void leeInformacionInicial() {
        try {
            idJugador = inJuego.readInt();
            estado = (EstadoJuego) inJuego.readObject();
            
            filas = estado.getPartidaOso().getTablero().getFilas();
            columnas = estado.getPartidaOso().getTablero().getColumnas();
            
            System.out.println("Jugador "+ idJugador + " iniciado correctamente");
            
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
    
    private void pintaBotones(Set<Casilla> casillasJugada) {
        for (int i= 0; i< listaBotonesJugadas.size(); i++) {
            BotonOso boton = listaBotonesJugadas.get(i);
            for (Casilla casilla: casillasJugada) {
                if (casilla.getX() ==  boton.getFila() && casilla.getY() == boton.getColumna()) {
                    boton.setForeground(Color.blue);
                }
            }
        }
    }
    
    private void pintaJugada(EstadoJuego estado) {
        PartidaMultijugador partidaOso = estado.getPartidaOso();
        
        pintaBotones(partidaOso.getTablero().getCasillasJugada());
        panelJuego.revalidate();
        
        int ososPropios = estado.getPartidaOso().getOsosJ1();
        int ososRivales = estado.getPartidaOso().getOsosJ2();
        
        textoOsosPropios.setText(String.valueOf(ososPropios));
        textoOsosRivales.setText(String.valueOf(ososRivales));

        botonOsoActual.setEnabled(false);
        botonOsoActual = null;
        
        if (partidaOso.finPartida()) {
            partidaOso.imprimeEstadoPartida();
            botonEnviarJugada.setEnabled(false);
        }
    }
    
    public void manejaExcepcion(Exception ex) {
        CustomExceptionHandler handler = new CustomExceptionHandler();
        Logger.getLogger(ServidorJuegoMultijugador.class.getName()).log(Level.SEVERE, null, ex);
        handler.uncaughtException(Thread.currentThread(), ex);
        disposeJuego();
    }
    
    private void disposeJuego() {
        dispose();
        System.exit(0);
    }
    
    private void desbloqueaTablero(boolean unlock) {
        botonEnviarJugada.setEnabled(unlock);
        Component[] components = panelJuego.getComponents();
        for (Component component : components) {
            if (component instanceof BotonOso) {
                BotonOso button = (BotonOso) component;
                button.setEnabled(unlock);
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea areaChat;
    private javax.swing.JButton botonConectar;
    private javax.swing.JButton botonEnviarJugada;
    private javax.swing.JButton botonEnviarMsg;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelOsosPropios;
    private javax.swing.JLabel labelOsosRivales;
    private javax.swing.JLabel labelUsuario;
    private javax.swing.JPanel panelJuego;
    private javax.swing.JTextField textoMensaje;
    private javax.swing.JTextField textoOsosPropios;
    private javax.swing.JTextField textoOsosRivales;
    private javax.swing.JTextField textoUsuario;
    // End of variables declaration//GEN-END:variables
   
    private class OsoGameThread extends Thread {

        private final ObjectInputStream objectIS;

        public OsoGameThread(ObjectInputStream objectIS) {
            this.objectIS = objectIS;
        }

        @Override
        public void run() {
            try {
                for (Object nuevoEstado; (nuevoEstado = objectIS.readObject()) != null;) {
                    estado = (EstadoJuego) nuevoEstado;
                    estado.getPartidaOso().imprimeEstadoPartida();
                    if (estado.getTurnoActual() == idJugador)
                        desbloqueaTablero(true);
                    pintaJugada(estado);
                }
            } catch (IOException | ClassNotFoundException ex) {
            }
        }
    }
}




