package chat;

import java.net.*;	// All network classes; ServerSocket, Socket
import java.io.*;		// All read and write classes, same I/O as we have already used

/* MyClient - Demo of client / server network communication
	by: Michael Floeser
*/

public class client																				
{																											
	public static void main(String [] args)													
	{																										
	 	try{																								
	 		// These two lines show how to get the IP address of this client			
	 		System.out.println("getLocalHost: "+InetAddress.getLocalHost() );			
	 		System.out.println("getByName:    "+InetAddress.getByName("localhost"));
																											
	 		// Make a connection with the server												
	 		Socket s = new Socket( InetAddress.getLocalHost(), 15555);											
																											
	 		// Open input from server																
	 		InputStream in = s.getInputStream();												
	 		BufferedReader bin = new BufferedReader(new InputStreamReader(in));		
																											
	 		// open output to server																
	 		OutputStream out = s.getOutputStream();											
	 		PrintWriter pout = new PrintWriter(new OutputStreamWriter(out));			
			ObjectOutputStream oos = new ObjectOutputStream(out);																								
	 		pout.println("Hello world");		// Writes some String to server					
	 		pout.flush(); 					// forces the data through to server			
																											
	 		// Read from the server using bin. and print it out							
	 		System.out.println(" <=returned-as=> " + bin.readLine());			
																											
	 		bin.close();																				
	 		pout.close();																				
	 		s.close();																					
	 	}																									
	 	catch(UnknownHostException uhe) {														
	 		System.out.println("no host");														
	 		uhe.printStackTrace();																	
	 	}																									
	 	catch(IOException ioe) {																	
	 		System.out.println("IO error");														
	 		ioe.printStackTrace();																	
	 	}																									
	 	catch( ArrayIndexOutOfBoundsException aioobe ) {									
	 		System.out.println("\nUsage: java Day10Server hostname some-word");		
	 	}																									
	}																										
}																											

