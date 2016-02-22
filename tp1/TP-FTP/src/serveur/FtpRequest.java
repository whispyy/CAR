package serveur;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

//La classe FtpRequest prend la main sur le serveur à chaque client se connectant
//Cette classe regroupe toutes les méthodes et gère l'intéraction avec l'utilisateur
public class FtpRequest implements Runnable {

	private Socket s; 
	private	String adr;
	private int port;
	private boolean auth;
	private boolean terminateConnexion;
	private BufferedReader r; // in
	private OutputStreamWriter w; // out
	private String user;
	private String pass;
	private String path;

	private Socket ds;
	private DataInputStream dr;
	private DataOutputStream dw;

	private String data;
	private String cmd;



	/**
	 * Construit uune instance de FtpRequest 
	 * @param s la socket permettant l'échange entre serveur et client
	 */
	public FtpRequest(Socket s){
		this.s = s;
		this.adr = this.s.getLocalAddress().getHostAddress();
		this.port = s.getPort();
		this.auth = false;
		this.terminateConnexion = false;
		try {
			r = new BufferedReader(new InputStreamReader(this.s.getInputStream()));
			w = new OutputStreamWriter(this.s.getOutputStream());
			sendToClient(220," Connexion ok, enter login");
		}
		catch (IOException e){
			System.out.println(e);
		}
		this.user = "user";
		this.pass = "pass";
		this.path = System.getProperty(user+".home");
	}



	/**
	 * Chaque commande est envoyée à cette méthode qui va ensuite 
	 * passer la main à une autre méthode en fonction de la commande
	 * appelée par le client
	 */
	public void processRequest(String req){
		if(req != null){
			if (req.split(" ").length > 1){
				this.cmd = req.split(" ")[0];
				this.data = req.split(" ")[1];
			}
			else{
				this.cmd = req;
				this.data ="";
			}
			System.out.println("cmd :"+cmd);
			System.out.println("data :"+data);
		}

		if (cmd.contains("USER"))
			processUSER();
		if (cmd.contains("PASS"))
			processPASS();
		if (cmd.contains("RETR"))
			processRETR();
		if (cmd.contains("STOR"))
			processSTOR();
		if (cmd.contains("LIST"))
			processLIST();
		if (cmd.contains("QUIT"))
			processQUIT();
		if (cmd.contains("PWD"))
			processPWD();
		if(cmd.contains("SYST"))
			processSYST();
	}


	/**
	 * Cette méthode vérifie que le username est correct
	 */
	private void processUSER(){
		if (!this.auth && this.user.equals(this.data)){
			this.auth = true;
			sendToClient(331," user ok");
		}
		else
			sendToClient(530," bad user");
	}


	/**
	 * Cette méthode vérifie que le password associé au username
	 * entré précedemment correspond bien
	 */
	private void processPASS(){
		if (this.auth && this.pass.equals(this.data))
			sendToClient(230," login ok");
		else{
			sendToClient(530," bad password");
			//processQuit ?
		}
	}
	
	/**
	 * Cette methode demande des informations concernant le système d'exploitation
	 * du serveur.
	 */
	private void processSYST(){
		sendToClient(215, "UNIX TYPE: L8");
	}
	
	/**
	 * Cette méthode permet au client de récupérer un fichier sur le serveur
	 */
	private void processRETR(){
		try {
			URI path2 = new URI(this.path);
			Path file = Paths.get(path2);
			if (this.auth) {
				byte[] dataArray = Files.readAllBytes(file.resolve(this.data));

				this.ds = new Socket(InetAddress.getByName(this.adr), this.port);

				this.dr = new DataInputStream(this.ds.getInputStream());
				this.dw = new DataOutputStream(this.ds.getOutputStream());

				sendToClient(150," File OK");

				for (int i = 0; i < dataArray.length; i++) {
					this.dw.writeByte(dataArray[i]);
					this.dw.flush();
				}
				this.ds.close();
				sendToClient(226," data closed");
			}
			else
				sendToClient(530," Bad connexion");

		} 
		catch (IOException e) {
			System.out.println("550 : error connexion or login");} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Cette méthode permet au client de déposer un fichier sur le serveur
	 * @return 
	 */
	private String processSTOR(){
		try {
			URI path2 = new URI(this.path);
			Path file = Paths.get(path2);
			if (this.auth){
				this.ds = new Socket(InetAddress.getByName(this.adr), this.port);
				this.dr = new DataInputStream(this.ds.getInputStream());
				this.dw = new DataOutputStream(this.ds.getOutputStream());
				sendToClient(150," File OK");

				FileOutputStream fos = new FileOutputStream(file.resolve(this.data).toString());
				int dr2;

				while ((dr2 = this.dr.read()) != -1){
					fos.write(dr2);
				}
				fos.close();
				this.dr.close();
				this.ds.close();
				sendToClient(226," Closing data connection.");
			}
			else
				sendToClient(530," Bad connexion");
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "TRUE";
	}


	/**
	 * Cette méthode permet de lister le contenu du répertoire courant
	 * se trouvant sur le serveur
	 * @return la liste des fichiers du répertoire courant
	 */
	private String processLIST(){
		File fichier = new File(this.path);
		File[] ls = fichier.listFiles();
		String currentFiles = "";

		try{
			this.dr = new DataInputStream(this.ds.getInputStream());
			this.dw = new DataOutputStream(this.ds.getOutputStream());

			if (this.auth){
				sendToClient(150,"150 : Files OK");
				for (int i=0; i <ls.length; i++){
					if (!ls[i].isHidden())
						if (!ls[i].isFile())
							currentFiles = "+s" +ls[i].length()+ls[i].lastModified()/1000+",\011"+ls[i].getName()+"\015\012";
						else if (ls[i].isDirectory()) 
							currentFiles = "+/,m"+ls[i].lastModified()/1000+",\011"+ls[i].getName()+"\015\012";
					this.dw.writeBytes(currentFiles);
					this.dw.flush();
				}
				this.ds.close();
				sendToClient(226,"226 : data connexion closed");
			}
			else{
				sendToClient(226,"530 : Not logged");
			}
		}
		catch(IOException e){
			System.out.println("425 : Can't open data connexion");
		}

		return "TRUE";
	}


	/**
	 * Cette méthode permet de se déconnecter du serveur
	 */
	private void processQUIT(){
		try {
			this.terminateConnexion = true;
			sendToClient(221, "terminated connexion");
			s.close();
			r.close();
			w.close();
			System.out.println("User : '"+this.user+"' disconnected !");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	/**
	 * Cette méthode affiche le contenu du répertoire courant 
	 */
	private void processPWD(){
		sendToClient(257,this.path);
	}

	/**
	 * Methode permettant d'écrire un message au client
	 * @param code un entier correspondant au code du message.
	 * @param msg un chaine de caractère correspondant à l'intitulé du message.
	 */
	protected void sendToClient(int code, String msg) {
		try {
			this.w.write(code + " " + msg + " \n");
			System.out.println("Messeage send :" + code + " " + msg);
			this.w.flush();
		} catch (IOException e) {
			this.terminateConnexion = true;
			e.printStackTrace();
		}
	}


	/**
	 * Cette méthode lance le thread de FtpRequest
	 */
	public void run() {
		while(true){
			try {
				String req = this.r.readLine();
				this.processRequest(req);
			
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			if (this.terminateConnexion)
				break;
			
		}
	}
}