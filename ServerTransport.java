import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.sun.javafx.font.PrismFontFactory;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class ServerTransport {

    private static Socket socket;

    public ServerTransport() {
        int port = 25000;
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server Started and listening to the port 25000");
    }

       try {
       while(true)

            {
                //Reading the message from the client
                socket = serverSocket.accept();
                System.out.println("Server Started and listening to the port 25000");

       }
       }
         catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        finally {
           try {
               socket.close();
           } catch (Exception e) {
           }
        }
       }
    }
}
