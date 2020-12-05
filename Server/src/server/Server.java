
package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Server extends Thread {
    private static ArrayList<BufferedWriter> Users;
    private String nameUser;
    private Socket Sockets;
    private InputStream inputData;
    private InputStreamReader readerData;
    private BufferedReader inputBuffer;
    
    public Server(Socket Sock){
        this.Sockets = Sock;
        try{
            inputData = Sockets.getInputStream();
            readerData = new InputStreamReader(inputData);
            inputBuffer = new BufferedReader(readerData);
        } catch(IOException exc){
            exc.printStackTrace();
        }
    }
    
    public void run(){

        try{

          String message;
          OutputStream depOut =  this.Sockets.getOutputStream();
          Writer outWriting = new OutputStreamWriter(depOut);
          BufferedWriter writerBuffer = new BufferedWriter(outWriting);
          Users.add(writerBuffer);
          nameUser =  message = inputBuffer.readLine();

          while(!"Sair".equalsIgnoreCase(message) && message != null) {
             
             message = inputBuffer.readLine();
             sendToAll(writerBuffer, message);
             System.out.println(message);
            }

         }catch (Exception exc) {
           exc.printStackTrace();

         }
    }
    
    public void sendToAll(BufferedWriter bufWriOut, String msg) throws  IOException {

      for(BufferedWriter bw : Users){
       if(!(bufWriOut == bw)){
         bw.write(nameUser + " -> " + msg+"\r\n");
         bw.flush();
       }
      }
    }
    public static void main(String []args) {

        try{
          //Cria os objetos necessário para instânciar o servidor
          
          // Interface
          JLabel lblMessage = new JLabel("Porta do Servidor:");
          JTextField txtPorta = new JTextField("25565");
          Object[] texts = {lblMessage, txtPorta };
          JOptionPane.showMessageDialog(null, texts);
          
          
          
          ServerSocket serverOn = new ServerSocket(Integer.parseInt(txtPorta.getText()));
          Users = new ArrayList<BufferedWriter>();
          
          //interface
          JOptionPane.showMessageDialog(null,"Servidor ativo na porta: "+
          txtPorta.getText());

           while(true){
             System.out.println("Aguardando conexão...");
             Socket sockConect = serverOn.accept();
             System.out.println("Cliente conectado...");
             Thread parallelism = new Server(sockConect);
             
             parallelism.start();
             JOptionPane.showMessageDialog(null,"Teste: "+
             txtPorta.getText());
          }

        }catch (Exception exp) {

          exp.printStackTrace();
        }
     }
    
    
}