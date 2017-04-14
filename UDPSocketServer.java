import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPSocketServer {
DatagramSocket socket = null;

public UDPSocketServer() {

}

public void createAndListenSocket() {
try {
socket = new DatagramSocket(9876);
byte[] incomingData = new byte[1024];

while (true) {
DatagramPacket incomingPacket = new DatagramPacket(incomingData, incomingData.length);
socket.receive(incomingPacket);
String message = new String(incomingPacket.getData());
System.out.println("Received message from client: " + message);
InetAddress IPAddress = incomingPacket.getAddress();
int port = incomingPacket.getPort();
String reply = "Thank you for the message";
byte[] data = reply.getBytes();
DatagramPacket replyPacket =
new DatagramPacket(data, data.length, IPAddress, port);
socket.send(replyPacket);
Thread.sleep(2000);
socket.close();
}

} catch (SocketException e) {
e.printStackTrace();
} catch (IOException i) {
i.printStackTrace();
} catch (InterruptedException e) {
e.printStackTrace();
}
}

public static void main(String[] args) {
UDPSocketServer server = new UDPSocketServer();
server.createAndListenSocket();
}
}