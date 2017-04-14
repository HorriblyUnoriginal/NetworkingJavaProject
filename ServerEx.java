import java.net.*;
import java.io.*;

public class ServerEx {
   public static void main(String[] args){
      
      BufferedReader br;
      PrintWriter opw;
      
      try {
         ServerSocket ss = new ServerSocket( 15555 );
         while(true) {
            Socket cs = ss.accept();
            br = new BufferedReader(new InputStreamReader(cs.getInputStream()));
            opw = new PrintWriter(new OutputStreamWriter(cs.getOutputStream()));
            String clientMsg = br.readLine(); //read msg from client
            opw.println(clientMsg.toUpperCase()); // convert to uppercase and flush the buffer
            opw.flush();
         }
      } catch(IOException ioe) {ioe.printStackTrace(); }
   }
}