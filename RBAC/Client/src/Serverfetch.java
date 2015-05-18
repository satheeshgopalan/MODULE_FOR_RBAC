import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Serverfetch {

  public final static int SOCKET_PORT = 6077;  // you may change this
  //public final static String FILE_TO_SEND = "d:/Cloud/Output/ab.txt";  // you may change this

  public static void main () throws IOException {
    FileInputStream fis = null;
    BufferedInputStream bis = null;
    OutputStream os = null;
    ServerSocket servsock = null;
    Socket sock = null;
    
    try {
      servsock = new ServerSocket(SOCKET_PORT);
      while (true) {
        System.out.println("Waiting...");
        try {
        	
        	
        	
          sock = servsock.accept();
          System.out.println("Accepted connection : " + sock);
          
          
           
             
          InputStream in = sock.getInputStream();  
          DataInputStream clientData = new DataInputStream(in);  
          String text = clientData.readUTF();
          
         
          // send file
          File myFile = new File ("d:/"+text+".txt");
          byte [] mybytearray  = new byte [(int)myFile.length()];
          fis = new FileInputStream(myFile);
          bis = new BufferedInputStream(fis);
          bis.read(mybytearray,0,mybytearray.length);
          os = sock.getOutputStream();
          System.out.println("Sending " + "d:/"+text+".txt" + "(" + mybytearray.length + " bytes)");
          os.write(mybytearray,0,mybytearray.length);
          os.flush();
          System.out.println("Done.");
          bis.close();
          os.close();
          sock.close();
          servsock.close();
          break;
        }
        finally {
          if (bis != null) bis.close();
          if (os != null) os.close();
          if (sock!=null) sock.close();
        
        }
      }
    }
    finally {
      if (servsock != null) servsock.close();
    }
  }


}