//working till now
import java.net.*;

public class Coordinator {
    
    public static void main(String[] args) {
        int port = 7000;
        
        Coordinator coordinator = new Coordinator();
        
        try {    
            InetAddress c_addr = InetAddress.getLocalHost();
            String c_name = c_addr.getHostName();
            System.out.println("Coordinator address is " + c_addr);
            System.out.println("Coordinator host name is " + c_name + "\n\n");    
        } catch (Exception e) {
            System.err.println(e);
            System.err.println("Error in coordinator");
        }
        
        if (args.length == 1) port = Integer.parseInt(args[0]);
        C_buffer buffer = new C_buffer();
        C_receiver receiver = new C_receiver(buffer, port);
        receiver.start();
        C_mutex mutex = new C_mutex(buffer, port);
        mutex.start();
    }
    
}
