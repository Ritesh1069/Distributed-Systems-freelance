import java.util.*;

public class C_buffer {
    
    private Vector<String[]> data;
    
    public C_buffer (){
        data = new Vector<String[]>(); 
    }    

    public synchronized int size(){
        return data.size();
    }

    public synchronized void saveRequest (String[] r){
        data.add(r);
    }

    public synchronized void show(){
        for (int i = 0; i < data.size(); i++) {
            String[] request = data.get(i);
            System.out.println("Regular Queue");
            System.out.println(request[0] + "  " + request[1]);
        }
        System.out.println(" ");
    }
    
    public synchronized String[] get(){
        String[] request = null; 
        
        if (data.size() > 0){
            request = data.get(0);
            data.remove(0);
        }
        return request;
    }
}
