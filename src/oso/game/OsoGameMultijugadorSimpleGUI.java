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
import javax.swing.JOptionPane;
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
public class OsoGameMultijugadorSimpleGUI extends javax.swing.JFrame implements ActionListener {
    
    private BotonOso ultimoBotonOso, botonOsoActual;
    private final List<BotonOso> listaBotonesJugadas = new ArrayList<>();
    private EstadoJuego estado;

    private final String hostJuego = "localhost";
    private final String hostChat = "localhost";
    private final int puertoJuego = 15000;
    private final int puertoChat = 16000;
    
    private Socket socketJuego;
    private Socket socketChat;
    
    private DataInputStream dinChat;
    private DataOutputStream doutChat;
    private ObjectOutputStream ooutJuego;
    
    private ClienteChatHilo leeChat;
    OsoGameHilo hiloJuego;
            
    private int filas;
    private int columnas;
    private String user = "";
    private int idJugador;
    
    private int ososPropios;
    private int ososRivales;
    
    
    public OsoGameMultijugadorSimpleGUI() {
        initComponents();
        init();
    }
    
    private void init() {
        this.setResizable(false);
        setLocationRelativeTo(null);
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
        jLabel8 = new javax.swing.JLabel();
        botonConectar = new javax.swing.JButton();
        labelUsuario = new javax.swing.JLabel();
        textoUsuario = new javax.swing.JTextField();

        dialogFinPartida.setTitle("Fin juego");
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Juego del Oso Cliente");

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
            .addGap(0, 443, Short.MAX_VALUE)
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

        jLabel8.setFont(new java.awt.Font("sansserif", 0, 15)); // NOI18N
        jLabel8.setText("Chat global");

        botonConectar.setFont(new java.awt.Font("sansserif", 0, 15)); // NOI18N
        botonConectar.setText("Conectar");
        botonConectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonConectarActionPerformed(evt);
            }
        });

        labelUsuario.setFont(new java.awt.Font("sansserif", 0, 15)); // NOI18N
        labelUsuario.setText("Usuario");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                    .addGroup(layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(labelUsuario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botonConectar))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(textoMensaje)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonEnviarMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 11, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(botonConectar)
                    .addComponent(labelUsuario)
                    .addComponent(textoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelJuego, javax.swing.GroupLayout.DEFAULT_SIZE, 443, Short.MAX_VALUE)
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
                            .addComponent(labelOsosPropios)
                            .addComponent(textoMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botonEnviarMsg))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textoOsosRivales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelOsosRivales))
                        .addContainerGap(27, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonEnviarMsgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEnviarMsgActionPerformed
        String texto = textoMensaje.getText();
        if (! texto.equals("")) {
            textoMensaje.setText("");
            String mensaje = user +" > "+ texto;
            try {
                doutChat.writeUTF(mensaje);
                doutChat.flush();
            } catch (IOException ex) {
                manejaExcepcion(ex, ex.toString());
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
                manejaExcepcion(ex, null);
            }
            botonOsoActual = null;
            desbloqueaTablero(false);
        }
    }//GEN-LAST:event_botonEnviarJugadaActionPerformed

    private void botonFinPartidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonFinPartidaActionPerformed
        disposeJuego();
    }//GEN-LAST:event_botonFinPartidaActionPerformed

    private void botonConectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonConectarActionPerformed
        
        user = textoUsuario.getText();
        if (! user.equals("")){
            textoMensaje.setFocusable(true);
            botonConectar.setEnabled(false);
            textoUsuario.setFocusable(false);
            
            try {
                socketChat = new Socket(hostChat, puertoChat); //socket para el chat
                socketJuego = new Socket(hostJuego, puertoJuego); //socket para las jugadas del tablero
                
                ooutJuego = new ObjectOutputStream(socketJuego.getOutputStream());
                
                doutChat = new DataOutputStream(socketChat.getOutputStream());
                dinChat = new DataInputStream(socketChat.getInputStream());
                              
            } catch (IOException | IllegalArgumentException | NullPointerException ex) {
                manejaExcepcion(ex, ex.toString());
            }
            
            //hilo de chat
            leeChat = new ClienteChatHilo(dinChat, areaChat);
            leeChat.start();
            
            //hilo para leer los nuevos estados
            hiloJuego = new OsoGameHilo(socketJuego);
            hiloJuego.start();
            
            try {
                doutChat.writeUTF("Usuario "+ user+ " conectado");
            } catch (IOException ex) {
                Logger.getLogger(OsoGameMultijugadorGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        else {
            JOptionPane.showMessageDialog(this, "Introduce un usuario valido\n"
                    + "en el apartado de configuracion!", "Usuario", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_botonConectarActionPerformed

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
            java.util.logging.Logger.getLogger(OsoGameMultijugadorSimpleGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new OsoGameMultijugadorSimpleGUI().setVisible(true);
        });
    }
    
    private void leeInformacionInicial(ObjectInputStream oinJuego) {
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
            manejaExcepcion(ex, ex.toString());
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
    
    public void manejaExcepcion(Exception ex, String infoAdicional) {
        if (infoAdicional == null) {
            Logger.getLogger(ServerGameMultijugador.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        CustomExceptionHandler handler = new CustomExceptionHandler(infoAdicional, this, JOptionPane.WARNING_MESSAGE);
        handler.uncaughtException(Thread.currentThread(), ex);
        disposeJuego();
    }
    
    private void disposeJuego() {
        dialogFinPartida.dispose();
        dispose();
        System.exit(0);
    }
    
    private class OsoGameHilo extends Thread {

        private ObjectInputStream objectIS = null;
        private final Socket socketJuego;

        public OsoGameHilo(Socket socketJuego) {
            this.socketJuego = socketJuego;
        }

        @Override
        public void run() {
            try {
                this.objectIS = new ObjectInputStream(socketJuego.getInputStream());
            } catch (IOException ex) {
                Logger.getLogger(OsoGameMultijugadorGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            leeInformacionInicial(objectIS);
            
            if (estado.getTurnoActual() == idJugador) 
                desbloqueaTablero(true);
            
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
                manejaExcepcion(ex, "Rival desconectado...");
            }
            
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea areaChat;
    private javax.swing.JButton botonConectar;
    private javax.swing.JButton botonEnviarJugada;
    private javax.swing.JButton botonEnviarMsg;
    private javax.swing.JButton botonFinPartida;
    private javax.swing.JDialog dialogFinPartida;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelFinPartida;
    private javax.swing.JLabel labelGanador;
    private javax.swing.JLabel labelOsosPropios;
    private javax.swing.JLabel labelOsosPropiosFin;
    private javax.swing.JLabel labelOsosRivalFin;
    private javax.swing.JLabel labelOsosRivales;
    private javax.swing.JLabel labelUsuario;
    private javax.swing.JPanel panelJuego;
    private javax.swing.JTextField textoMensaje;
    private javax.swing.JTextField textoOsosPropios;
    private javax.swing.JTextField textoOsosRivales;
    private javax.swing.JTextField textoUsuario;
    // End of variables declaration//GEN-END:variables

}




