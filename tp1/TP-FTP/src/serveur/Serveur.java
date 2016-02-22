package serveur;

import java.io.IOException;
import java.net.ServerSocket;

import main.Authentification;


//Cette classe Serveur regroupe les méthodes d'initialisation des variables
//ainsi que de lancement du serveur
public class Serveur {

	public static ServerSocket soc;


	/**
	 * Affiche simplement un message de bienvenue
	 */
	public static void accueilServeur(){
		System.out.println("******************************************************************************");
		System.out.println("********************************* FTP SERVER *********************************");
		System.out.println("******************************************************************************");
	}


	/**
	 * Cette méthode initialise les variables indispensables au lancement du
	 * serveur en allant chercher les données utiles dans la classe Authentification
	 * 
	 * @throws IOException 
	 */
	public static void initPort() throws IOException{
		int port = Authentification.port;
		soc = new ServerSocket(port);
		System.out.println("FTP Server started on port n°"+port+".");
	}


	/**
	 * Cette méthode s'occupe de la mise en ligne du serveur
	 */
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
