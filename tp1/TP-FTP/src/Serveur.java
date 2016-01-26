import java.io.IOException;
import java.net.ServerSocket;


public class Serveur {
	
	public static int port = 20;
	public static ServerSocket soc;

	
	public static void main(String args[]){		
		try {
			soc = new ServerSocket(port);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
        System.out.println("FTP Server Started on Port Number " + port + ".");
        
        while(true){
            System.out.println("Waiting for Connection ...");  
        }
	}
}
