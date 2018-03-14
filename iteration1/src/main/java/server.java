import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.howtodoinjava.jackson2.example.pojo.Employee;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class server
{

    private static Socket socket;
    //public static String number;

    public static void main(String[] args)
    {
        Check check = new Check(2,"server","ASUS2");
        ServerTransport serverTransport = new ServerTransport();
        try
        {
            //ServerTransport serverTransport = new ServerTransport();
            int port = 25000;
            ServerSocket serverSocket =  new ServerSocket(port);
            System.out.println("Server Started and listening to the port 25000");
            ObjectMapper mapper2 = new ObjectMapper();
            mapper2.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            //Server is running always. This is done using this while(true) loop
            while(true)
            {
                //Reading the message from the client

                //socket = serverSocket.accept();

                /*InputStream is = socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                String number = br.readLine();*/
               // String number = null;
               // System.out.println("Message received from client is "+ number.receiveMessage());

                serverTransport.receiveMessage();
                ObjectMapper mapper4 = new ObjectMapper();
                mapper4.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
                Check check2 = mapper2.readValue(serverTransport.receiveMessage(), Check.class);
                System.out.println("The POJO of the message received from the client is : "+check2);
                System.out.println("************************************************");

                ObjectMapper mapper3 = new ObjectMapper();
                mapper3.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
                String msgBack = mapper3.writeValueAsString(check);

                // System.out.println(msgBack);
                // String sendmsg = mapper.writeValueAsString(msgBack);

                //Multiplying the number by 2 and forming the return message
                String returnMessage;
                try
                {
                    //String sendmsg = String.valueOf(mapper.readValue(number, Employee.class));
                    // System.out.println(sendmsg);
                    // returnMessage = String.valueOf(emp) + "\n";
                    //int numberInIntFormat = Integer.parseInt(number);
                    // int returnValue = numberInIntFormat*2;

                    String returnValue = msgBack;
                    //System.out.println(returnValue);
                    returnMessage = String.valueOf(returnValue) + "\n";
                }
                catch(NumberFormatException e)
                {
                    //Input was not a number. Sending proper message back to client.
                    returnMessage = "Please send a proper number\n";
                }

                //Sending the response back to the client.
                OutputStream os = socket.getOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter(os);
                BufferedWriter bw = new BufferedWriter(osw);
                bw.write(returnMessage);
                System.out.println("Message sent to the client is "+returnMessage);
                bw.flush();
                System.out.println("************************************************");
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

        finally
        {
            try
            {
                socket.close();
            }
            catch(Exception e){}
        }
    }
}