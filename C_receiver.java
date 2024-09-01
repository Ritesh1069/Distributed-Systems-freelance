//working till now
import java.io.*;
import java.net.*;

public class C_receiver extends Thread {
    private C_buffer buffer;
    private int port;
    private ServerSocket s_socket;
    private Socket socketFromNode;
    private C_Connection_r connect;

    public C_receiver(C_buffer b, int p) {
        buffer = b;
        port = p;

        try {
            s_socket = new ServerSocket(port);
            System.out.println("C:receiver    Coordinator is now accepting requests on port " + port);
        } catch (IOException e) {
            System.out.println("Exception when creating a server socket " + e);
        }
    }

    public void run() {
        while (true) {
            try {
                socketFromNode = s_socket.accept();
                System.out.println("C:receiver    Coordinator has received a request ...");

                connect = new C_Connection_r(socketFromNode, buffer);
                connect.start();
                PrintWriter out = new PrintWriter(socketFromNode.getOutputStream(), true);
                out.println("Request received"); 
                out.close(); 

            } catch (IOException e) {
                System.out.println("Exception when creating a connection " + e);
            }
        }
    }
}
