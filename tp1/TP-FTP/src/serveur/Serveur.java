package serveur;

import java.io.IOException;
import java.net.ServerSocket;

import main.Authentification;


public class Serveur {

	public static ServerSocket soc;


	public static void accueilServeur(){
		System.out.println("******************************************************************************");
		System.out.println("********************************* FTP SERVER *********************************");
		System.out.println("******************************************************************************");
	}

	public static void initPort() throws IOException{
		int port = Authentification.port;
		soc = new ServerSocket(port);
		System.out.println("FTP Server started on port n°"+port+".");
	}

	public static void lancementServeur(){	
		while (true){
			try {
				new Thread(new FtpRequest(soc.accept())).start();
			} catch (IOException e) {
				System.out.println("main::"+e.getMessage());
			}
		}
	}
}
