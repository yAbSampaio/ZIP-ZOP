/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


/**
 *
 * @author Thiago Jansen, Igor Oliveira
 */
public class Client extends javax.swing.JFrame {

    /**
     * Creates new form Client
     */
    public Client() {
        this.connect = false;
        this.first = true;
        initComponents();
        
    }
    
    public void connectServer(String name, String ip, String port) throws IOException{
        try{    
            this.socket = new Socket(ip,Integer.parseInt(port));
            this.outputData = socket.getOutputStream();
            this.outWriting = new OutputStreamWriter(this.outputData);
            this.writerBuf = new BufferedWriter(this.outWriting);
            this.writerBuf.write(name+"\r\n");
            this.writerBuf.flush();      
            this.connect = true;
            this.sendMsg("yes");
        }
        catch (IOException exp) {
            JOptionPane.showMessageDialog(null, "Não foi possivel conecctar com server");
        }
    }
    public void sendMsg(String msg) throws IOException{
        if(this.first){
            this.writerBuf.write("Entrei !!\r\n");
            this.feedMsg.append( "Você foi conectado com sucesso!!!\r\n");
            this.writerBuf.flush();
            this.first = false;
        }
        else{
            this.writerBuf.write(msg+"\r\n");
            this.feedMsg.append( this.inpName.getText() + ": "+this.inpMsg.getText()+"\r\n");
            this.writerBuf.flush();
        }
    }
    public void out()throws IOException{
        this.writerBuf.write("Desconectado");
        this.writerBuf.flush();
        this.feedMsg.append("Desconectado \r\n");
        this.writerBuf.close();
        this.outWriting.close();
        this.outputData.close();
        this.socket.close();
        this.connect = false;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        inpMsg = new javax.swing.JTextField();
        btnSend = new javax.swing.JButton();
        separatorDown = new javax.swing.JSeparator();
        txtTitle = new javax.swing.JLabel();
        separatorTop = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        feedMsg = new javax.swing.JTextArea();
        inpName = new javax.swing.JTextField();
        txtName = new javax.swing.JLabel();
        inpPort = new javax.swing.JTextField();
        txtIp = new javax.swing.JLabel();
        btnLogin = new javax.swing.JButton();
        txtPort = new javax.swing.JLabel();
        btnOut = new javax.swing.JButton();
        inpIp = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnSend.setText("Enviar");
        btnSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendActionPerformed(evt);
            }
        });

        txtTitle.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtTitle.setText("ZIP ZOP");

        feedMsg.setEditable(false);
        feedMsg.setColumns(20);
        feedMsg.setRows(5);
        jScrollPane1.setViewportView(feedMsg);

        txtName.setText("Nome");

        txtIp.setText("Ip");

        btnLogin.setText("Logar");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        txtPort.setText("Porta");

        btnOut.setText("Deslogar");
        btnOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(separatorDown, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(separatorTop, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(93, 93, 93)
                                        .addComponent(txtName))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(104, 104, 104)
                                        .addComponent(txtIp))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(98, 98, 98)
                                        .addComponent(txtPort)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(inpName, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(inpIp, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(inpPort, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(73, 73, 73)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(btnLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnOut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(inpMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(74, 74, 74)
                        .addComponent(btnSend, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGap(255, 255, 255)
                .addComponent(txtTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(separatorTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(txtName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(inpName, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(txtIp)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(inpIp, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPort)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(inpPort, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnOut, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)))
                .addComponent(separatorDown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inpMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSend, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendActionPerformed
        // TODO add your handling code here:
        if(this.connect){    
            String msg = this.inpMsg.getText();
            //System.out.println(msg);
            if(!msg.isEmpty()){
                try {
                    this.sendMsg(msg);
                    this.inpMsg.setText("");
                } catch (IOException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_btnSendActionPerformed

    private void btnOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOutActionPerformed
        try {
            // TODO add your handling code here:
            this.out();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnOutActionPerformed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        // TODO add your handling code here:
        String name = this.inpName.getText();
        String ip = this.inpIp.getText();
        String port = this.inpPort.getText();
        if((!name.isEmpty()) && (!ip.isEmpty()) && (!port.isEmpty())){
            this.inpIp.setText("");
            this.inpPort.setText("");
            try {
                this.connectServer(name, ip, port);
                new Thread(new msg(this.socket)).start();
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }else{
            JOptionPane.showMessageDialog(null, "Todos os campos são obrigatórios");
        }
        
        
    }//GEN-LAST:event_btnLoginActionPerformed

    /**
     * @param args the command line arguments
     */
    private class msg implements Runnable {
        private InputStreamReader inpReader;
        private BufferedReader bufferR;
        public msg(Socket sock) throws IOException{
            InputStream inpData = sock.getInputStream();
            this.inpReader = new InputStreamReader(inpData);
        
        }
        @Override
        public void run() {
            try {
                while(connect){
                    this.bufferR = new BufferedReader(inpReader);
                    String msg = "";
                    //if(!bufferR.ready()){
                    msg = bufferR.readLine();           
                    System.out.println(msg);
                    feedMsg.append(msg+"\r\n");
                    //}
                }
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnOut;
    private javax.swing.JButton btnSend;
    private javax.swing.JTextArea feedMsg;
    private javax.swing.JTextField inpIp;
    private javax.swing.JTextField inpMsg;
    private javax.swing.JTextField inpName;
    private javax.swing.JTextField inpPort;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator separatorDown;
    private javax.swing.JSeparator separatorTop;
    private javax.swing.JLabel txtIp;
    private javax.swing.JLabel txtName;
    private javax.swing.JLabel txtPort;
    private javax.swing.JLabel txtTitle;
    // End of variables declaration//GEN-END:variables
    private boolean connect;
    private Socket socket;
    private OutputStream outputData;
    private Writer outWriting;
    private BufferedWriter writerBuf;
    private boolean first;
}
