import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

 
public class uploadServer extends Thread {
    public static final int PORT = 6177;
    public static final int BUFFER_SIZE = 1024;
    ServerSocket serverSocket=null;
    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("Waiting for Client...");
 
            //while (true) {
                Socket s = serverSocket.accept();
                System.out.println("Connected to client\nWaiting for Client to upload file...");
                
                saveFile(s);
            //}
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
    private void saveFile(Socket socket) throws Exception 
    {
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        FileOutputStream fos;
        FileOutputStream location = null;
        byte [] buffer = new byte[BUFFER_SIZE];
     // 1. Read file name.
        Object o = ois.readObject();
 
        if (o instanceof String) 
        {
        	System.out.println("Instance of String");
        	fos = new FileOutputStream(o.toString());
            
            System.out.println("fos parameter:"+o.toString());
            File dir = new File("e:/");
            
            location = new FileOutputStream(new File(dir,o.toString()));
        }
        else 
        {
            throwException("Something is wrong");
        }
 
        // 2. Read file to the end.
        Integer bytesRead = 0;
 
        do {
	            o = ois.readObject();
	 
	            if (!(o instanceof Integer))
	            {
	                throwException("Something is wrong");
	            }
	 
	            bytesRead = (Integer)o;
	 
	            o = ois.readObject();
	 
	            if (!(o instanceof byte[])) 
	            {
	                throwException("Something is wrong");
	            }
	 
	            buffer = (byte[])o;
	 
	            // 3. Write data to output file.
	            location.write(buffer, 0, bytesRead);
	           
        } while (bytesRead == BUFFER_SIZE);
         
        System.out.println("File transfer success");
        serverSocket.close();
         
      }
 
    public static void throwException(String message) throws Exception {
        throw new Exception(message);
    }
 
    public static void main(String[] args) {
        new uploadServer().start();
    }
}  