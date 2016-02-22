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



	public FtpRequest(Socket s){
		this.s = s;
		this.adr = this.s.getLocalAddress().getHostAddress();
		this.port = 3000;
		this.auth = false;
		this.terminateConnexion = false;
		try {
			r = new BufferedReader(new InputStreamReader(this.s.getInputStream()));
			w = new OutputStreamWriter(this.s.getOutputStream());
			System.out.println("220 : Connexion ok, enter login");
		}
		catch (IOException e){
			System.out.println(e);
		}
		this.user = "user";
		this.pass = "pass";
		this.path = System.getProperty(user+".home");
		new Thread(this).start();
	}



	public void processRequest(){
		String req;

		try {
			for(req = this.r.readLine(); req != null; req= this.r.readLine()){
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
		} catch (IOException e) {
			e.printStackTrace();
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
	}

	private void processUSER(){
		if (!this.auth && this.user.equals(this.data)){
			this.auth = true;
			System.out.println("331 : user ok");
		}
		else
			System.out.println("530 : bad user");
	}

	private void processPASS(){
		if (this.auth && this.pass.equals(this.data))
			System.out.println("230 : login ok");
		else{
			System.out.println("530 : bad password");
			//processQuit ?
		}
	}

	private void processRETR(){
		try {
			URI path2 = new URI(this.path);
			Path file = Paths.get(path2);
			if (this.auth) {
				byte[] dataArray = Files.readAllBytes(file.resolve(this.data));

				this.ds = new Socket(InetAddress.getByName(this.adr), this.port);

				this.dr = new DataInputStream(this.ds.getInputStream());
				this.dw = new DataOutputStream(this.ds.getOutputStream());

				System.out.println("150 : File OK");

				for (int i = 0; i < dataArray.length; i++) {
					this.dw.writeByte(dataArray[i]);
					this.dw.flush();
				}
				this.ds.close();
				System.out.println("226 : data closed");
			}
			else
				System.out.println("530 : Bad connexion");

		} 
		catch (IOException e) {System.out.println("550 : error connexion or login");} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	 
	
	private String processSTOR(){
		try {
			URI path2 = new URI(this.path);
			Path file = Paths.get(path2);
			if (this.auth){
				this.ds = new Socket(InetAddress.getByName(this.adr), this.port);
				this.dr = new DataInputStream(this.ds.getInputStream());
				this.dw = new DataOutputStream(this.ds.getOutputStream());
				System.out.println("150 : File OK");

				FileOutputStream fos = new FileOutputStream(file.resolve(this.data).toString());
				int dr2;

				while ((dr2 = this.dr.read()) != -1){
					fos.write(dr2);
				}
				fos.close();
				this.dr.close();
				this.ds.close();
				System.out.println("226 : Closing data connection.");
			}
			else
				System.out.println("530 : Bad connexion");
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

	private String processLIST(){
		File fichier = new File(this.path);
		File[] ls = fichier.listFiles();
		String currentFiles = "";

		try{
			this.dr = new DataInputStream(this.ds.getInputStream());
			this.dw = new DataOutputStream(this.ds.getOutputStream());

			if (this.auth){
				System.out.println("150 : Files OK");
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
				System.out.println("226 : data connexion closed");
			}
			else{
				System.out.println("530 : Not logged");
			}
		}
		catch(IOException e){
			System.out.println("425 : Can't open data connexion");
		}

		return "TRUE";
	}

	private void processQUIT(){
		try {
			this.terminateConnexion = true;
			s.close();
			r.close();
			w.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void processPWD(){
		System.out.println("257 :"+this.path);
	}

	public void run() {
		while(true){
			if (this.terminateConnexion)
				break;

			this.processRequest();
			
		};
	}
}