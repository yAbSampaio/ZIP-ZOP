
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
    static ArrayList<BufferedWriter> Users;
    private String nameUser;
    private Socket Sockets;
    private InputStream inputData;
    private InputStreamReader readerData;
    private BufferedReader inputBuffer;
    private static boolean On = true;
    
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

            while(!("Desconectado".equals(message))&&(message != null)) {

               message = inputBuffer.readLine();
               sendToAll(writerBuffer, message);
               System.out.println(message);
            }
            int cont = 0;
            if(Users.size() != 0){    
                for(BufferedWriter bw : Users){
                    if((writerBuffer == bw)){
                      Users.remove(cont);
                      writerBuffer.close();
                      outWriting.close();
                      depOut.close();
                      Sockets.close();
                      break;
                    }
                    cont ++;
                }
            }

         }catch (Exception exc) {
           exc.printStackTrace();

         }
    }
    
    public void sendToAll(BufferedWriter bufWriOut, String msg) throws  IOException {
      
        
      for(BufferedWriter bw : Users){
       if(!(bufWriOut == bw)){
         bw.write(nameUser + ": " + msg+"\r\n");
         bw.flush();
       }
      }
    }
    public static int verification(String port){
        if(port.equals("")){
            return 1;
        }
        for ( int i = 0; i < port.length(); i++ ){
            if (!Character.isDigit(port.charAt(i)) ) {
                return 1; 
            }
        }
        return 0;
    } 
    
    public static int confirmInp(){

        JOptionPane out = new JOptionPane();
        int output = out.showConfirmDialog(null,"Tentar novamente","Campo de porta invalido",JOptionPane.YES_NO_OPTION);
        if(output == JOptionPane.YES_OPTION){
         //    System.exit(0);
         return 1;

        }else{
           if(output == JOptionPane.NO_OPTION){
               return 0;
           }
        }
        return 2;


    }
    public static void main(String []args) {

        try{
          
          // Interface
          JLabel lblMessage = new JLabel("Informe a porta do Servidor");
          JTextField txtPorta = new JTextField("");
          Object[] texts = {lblMessage, txtPorta };
          JOptionPane.showMessageDialog(null, texts);
          int conf = 1;
          int veri = verification(txtPorta.getText());
          while(veri == 1 && conf == 1){
            conf = confirmInp();
            if(conf == 0){
                On = false;
                break;
            }
            JOptionPane.showMessageDialog(null, texts);
            veri = verification(txtPorta.getText());
          }
          
          
          
          if(!(conf==0)){
            ServerSocket serverOn = new ServerSocket(Integer.parseInt(txtPorta.getText()));
            Users = new ArrayList<BufferedWriter>();

            //interface
            JOptionPane.showMessageDialog(null,"Servidor inicializado na porta: "+
            txtPorta.getText());

             while(On){
               System.out.println("Aguardando conexão...");
               Socket sockConect = serverOn.accept();
               System.out.println("Cliente conectado !!!");
               Thread parallelism = new Server(sockConect);

               parallelism.start();
              }
          }

        }catch (Exception exp) {

          exp.printStackTrace();
        }
     }
    
    
}