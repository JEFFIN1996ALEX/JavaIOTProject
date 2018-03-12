import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.howtodoinjava.jackson2.example.pojo.Employee;

import java.io.*;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;

public class Client
{
    private static Socket socket;

    public static void main(String args[])
    {
        Check check = new Check(1, "Client", "ASUS1");

        try
        {
            String host = "localhost";
            int port = 25000;
            InetAddress address = InetAddress.getByName(host);
            socket = new Socket(address, port);
          //  String json = mapper.writeValueAsString(emp);

            //Send the message to the server
            OutputStream os = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);

            ObjectMapper mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            String number = mapper.writeValueAsString(check);

            String sendMessage = number + "\n";
            bw.write(sendMessage);
            bw.flush();
            System.out.println("Message sent to the server : "+sendMessage);
            System.out.println("************************************************");

            //Get the return message from the server
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String message = br.readLine();
            System.out.println("Message received from the server : " +message);
            System.out.println("************************************************");
            String receivedJson = message;
           // System.out.println(receivedJson);

            ObjectMapper mapper2 = new ObjectMapper();
            mapper2.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

            Check check1 = mapper2.readValue(receivedJson, Check.class);

          //  System.out.println(String.valueOf(receivedJson));
            System.out.println("The POJO of the message from server is : "+String.valueOf(check1));
        }

        catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (Exception e)
        {   e.printStackTrace();}



            finally
        {
            //Closing the socket
            try
            {
                socket.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }

}
 
