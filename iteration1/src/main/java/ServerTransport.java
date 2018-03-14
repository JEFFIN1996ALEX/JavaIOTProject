import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
//import com.sun.javafx.font.PrismFontFactory;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class ServerTransport {

    private static Socket socket;
    public ServerSocket serverSocket;
    public String number;
    public String returnMessage;
    ;

    public String receiveMessage() {
        int port = 25000;
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server Started and listening to the port 25000");
            while (true)

            {
                //Reading the message from the client
                socket = serverSocket.accept();
                InputStream is = socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                number = br.readLine();
                System.out.println("Message received from client is "+ number);
            }
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return receiveMessage();
    }


  /*  public String sendMessage() {

        while (true) {
            OutputStream os = null;
            try {
                os = socket.getOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter(os);
                BufferedWriter bw = new BufferedWriter(osw);
                bw.write(returnMessage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }*/
}
