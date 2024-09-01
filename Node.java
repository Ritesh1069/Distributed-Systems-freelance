import java.net.*;
import java.io.*;

public class Node {

    private String n_host_name;
    private int n_port;
    private int c_request_port = 7000;
    private int c_return_port = 7001;

    public Node(String hostName, int port, int sec) {
        n_host_name = hostName;
        n_port = port;

        System.out.println("Node " + n_host_name + ":" + n_port + " of DME is active ....");

        try {

            Socket socket = new Socket("localhost", c_request_port);
            PrintWriter pout = new PrintWriter(socket.getOutputStream(), true);
            pout.println(n_host_name + ":" + n_port);

            BufferedReader bin = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String response = bin.readLine();
            if (response != null && response.equals("Request received")) {
                System.out.println("Token Received!!!");
                System.out.println("Critical section entered");
                System.out.println("Performing tasks in the critical section");
                System.out.println("Exiting Critical section");
                System.out.println("Returning the token");

                Socket returnSocket = new Socket("localhost", c_return_port);
                PrintWriter returnOut = new PrintWriter(returnSocket.getOutputStream(), true);
                returnOut.println("Token returned");
                returnSocket.close();
            } else {
                System.out.println("Unexpected response from coordinator: " + response);
            }
            socket.close();
        } catch (IOException e) {
            System.out.println("IOException occurred: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String n_host_name = "";
        int n_port;
        if (args.length < 2 || args.length > 3) {
            System.out.println("Usage: Node [port number] [millisecs]");
            System.exit(1);
        }

        try {
            InetAddress n_inet_address = InetAddress.getLocalHost();
            n_host_name = n_inet_address.getHostName();
            System.out.println("node hostname is " + n_host_name + ":" + n_inet_address);
        } catch (java.net.UnknownHostException e) {
            System.out.println(e);
            System.exit(1);
        }

        n_port = Integer.parseInt(args[0]);
        System.out.println("node port is " + n_port);
        Node node = new Node(n_host_name, n_port, Integer.parseInt(args[1]));
    }
}


// //working till now
// import java.net.*;
// import java.io.*;

// public class Node {

//     private String n_host_name;
//     private int n_port;
//     private int c_request_port = 7000;

//     public Node(String hostName, int port, int sec) {
//         n_host_name = hostName;
//         n_port = port;

//         System.out.println("Node " + n_host_name + ":" + n_port + " of DME is active ....");

//         try {
//             // Request the token from the coordinator
//             Socket socket = new Socket("localhost", c_request_port);
//             PrintWriter pout = new PrintWriter(socket.getOutputStream(), true);
//             pout.println(n_host_name + ":" + n_port);

//             // Wait for the token response
//             BufferedReader bin = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//             String response = bin.readLine();
//             if (response != null && response.equals("Request received")) {
//                 System.out.println("Token Received!!!");
//                 // Enter the critical section
//                 System.out.println("Critical section entered");
//                 // Perform tasks in the critical section
//                 System.out.println("Performing tasks in the critical section");
//                 // Exit the critical section
//                 System.out.println("Exiting Critical section");
//                 // Return the token
//                 System.out.println("Returning the token");
//             } else {
//                 System.out.println("Unexpected response from coordinator: " + response);
//             }
//             socket.close();
//         } catch (IOException e) {
//             System.out.println("IOException occurred: " + e.getMessage());
//         }
//     }

//     public static void main(String[] args) {
//         String n_host_name = "";
//         int n_port;

//         // port and millisec (average waiting time) are specific of a node
//         if (args.length < 2 || args.length > 3) {
//             System.out.println("Usage: Node [port number] [millisecs]");
//             System.exit(1);
//         }

//         // get the IP address and the port number of the node
//         try {
//             InetAddress n_inet_address = InetAddress.getLocalHost();
//             n_host_name = n_inet_address.getHostName();
//             System.out.println("node hostname is " + n_host_name + ":" + n_inet_address);
//         } catch (java.net.UnknownHostException e) {
//             System.out.println(e);
//             System.exit(1);
//         }

//         n_port = Integer.parseInt(args[0]);
//         System.out.println("node port is " + n_port);
//         Node node = new Node(n_host_name, n_port, Integer.parseInt(args[1]));
//     }

// }
