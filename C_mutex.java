//working till now
import java.net.*;
import java.io.*
;
public class C_mutex extends Thread {
    C_buffer buffer;
    int port;

    public C_mutex(C_buffer b, int p) {
		buffer = b;
		port = p;
    }

    public void run() {
		try {

		    ServerSocket ss_back = new ServerSocket(7001);
            System.out.println("C:mutex   Buffer size is " + buffer.size());
		    while (true) {
				
				if (buffer.size() > 0) {
                    System.out.println("C:mutex   Buffer size is " + buffer.size());		    
				    String[] nodeInfo = buffer.get();
				    String n_host = nodeInfo[0];
				    int n_port = Integer.parseInt(nodeInfo[1]);
		    
				    try {
						Socket tokenSocket = new Socket(n_host, n_port);
						PrintWriter out = new PrintWriter(tokenSocket.getOutputStream(), true);
						out.println("Token");
						tokenSocket.close();
						System.out.println("Token given to the node : " + n_host + " , " + n_port);
				    } catch (java.io.IOException e) {
						System.out.println(e);
				    }

				    try {
						Socket tokenBackSocket = ss_back.accept();
						tokenBackSocket.close();
						System.out.println("Token received back from node : " + nodeInfo[0] + " , " + nodeInfo[1]);
				    } catch (java.io.IOException e) {
						System.out.println("CRASH Mutex waiting for the TOKEN back" + e);
				    }
				}// endif	
		    }// endwhile
		} catch (Exception e) {
			System.out.print(e);
		}
    }
}
