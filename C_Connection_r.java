//working till now
import java.net.*;
import java.io.*;

public class C_Connection_r extends Thread {
    private C_buffer buffer;
    private Socket s;
    private InputStream in;
    private BufferedReader bin;
    
    public C_Connection_r(Socket s, C_buffer b) {
        this.s = s;
        this.buffer = b;
    }
    
    public void run() {
        final int NODE = 0;
        final int PORT = 1;

        String[] request = new String[2];

        System.out.println("C:connection IN  dealing with request from socket " + s);
        try {
            in = s.getInputStream();
            bin = new BufferedReader(new InputStreamReader(in));
            
            String inputLine = bin.readLine();
            System.out.println("Received input from socket: " + inputLine);
            
            String[] parts = inputLine.split(":");
            request[NODE] = parts[0];
            request[PORT] = parts[1];
            
            buffer.saveRequest(request);

            s.close();
            
            PrintWriter out = new PrintWriter(s.getOutputStream(), true);
            out.println("Request received");
        } catch (java.io.IOException e) {
            System.out.println("IOException occurred: " + e.getMessage());
        }
    }
}
