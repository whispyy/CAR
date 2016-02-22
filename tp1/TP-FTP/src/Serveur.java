import java.io.IOException;
import java.net.ServerSocket;


public class Serveur {

	public static ServerSocket soc;


	public static void lancementServeur(){
		System.out.println("******************************************************************************");
		System.out.println("********************************* FTP SERVER *********************************");
		System.out.println("******************************************************************************");
	}


	public static void main(String args[]){	
		int port = Authentification.port;
		lancementServeur();

		try {
			soc = new ServerSocket(port);
			System.out.println("FTP Server started on port n°"+port+".");

			while (true){
				try {
					new Thread(new FtpRequest(soc.accept())).start();
				} catch (IOException e) {
					System.out.println("main::"+e.getMessage());
				}
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
