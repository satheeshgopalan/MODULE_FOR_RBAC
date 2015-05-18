import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Arrays;
import java.io.*;
import java.net.*;
 
 
public class uploadfiles {
	uploadfiles(String args) throws UnknownHostException, IOException
	{
		String file_name = args;
        
        File file = new File(file_name);
        Socket socket = new Socket("127.0.0.1", 6177);
        System.out.println("Connected");
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        
        System.out.println("Sending "+file.getName()+" from path "+file.getAbsolutePath()+" to server");
        oos.writeObject(file.getName());
 
        FileInputStream fis = new FileInputStream(file);
        byte [] buffer = new byte[1024];
        Integer bytesRead = 0;
 
        while ((bytesRead = fis.read(buffer)) > 0) {
            oos.writeObject(bytesRead);
            oos.writeObject(Arrays.copyOf(buffer, buffer.length));
        }
        socket.close();
        fis.close();
        oos.close();
        ois.close();
        System.exit(0); 
	}
    public static void main(String args) throws IOException
    {
    	uploadfiles cl=new uploadfiles(args);
    }
 
}
  