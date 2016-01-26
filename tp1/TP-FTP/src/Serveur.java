import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Serveur {
	
	public static int port = 8000;
	public static ServerSocket soc;

	
	public static void main(String args[]){	
		System.out.println("******************************************************************************");
        System.out.println("********************************* FTP SERVER *********************************");
        System.out.println("******************************************************************************");
        
		try {
			soc = new ServerSocket(port);
			System.out.println("FTP Server started on port n°"+port+".");
			
			while (true){
				Socket s = soc.accept();
                System.out.println("Client connected from " + s.getInetAddress().getHostName() + "...");
                Thread t = new Thread();
                t.start();
			}
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
