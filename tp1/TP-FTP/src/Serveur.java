import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Serveur {
	
	public static ServerSocket soc;
	
	
	public static void lancementServeur(){
		System.out.println("******************************************************************************");
        System.out.println("********************************* FTP SERVER *********************************");
        System.out.println("******************************************************************************");
	}
	
	
	public static void main(String args[]){	
        int port = Authentification.port;
        int nbLimit = Authentification.nbLimit;
        lancementServeur();
        
		try {
			soc = new ServerSocket(port);
			System.out.println("FTP Server started on port n°"+port+".");
	
			while (true){
				Socket s = soc.accept();
				FtpRequest ftpR = new FtpRequest(s);
				System.out.println(s.getInputStream().toString());
				
                System.out.println("Client connected from " + s.getInetAddress().getHostName() + "...");
                
			}
			
		} catch (IOException e) {
			System.out.println("socConnect::"+e.getMessage());
		}
		try {
			soc.close();
		} catch (IOException e) {
			System.out.println("socClose::"+e.getMessage());
		}
	}
}
