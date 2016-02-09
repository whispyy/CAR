import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FtpRequest {

	private Socket s;
	private	String adr;
	private int port;
	private boolean auth;
	private BufferedReader r;
	private OutputStreamWriter w;
	private String user;
	private String pass;
	private String path;

	private Socket ds;
	private DataInputStream dr;
	private DataOutputStream dw;
	private Path data; //pas sur
	
	public FtpRequest(Socket s){
		this.s = s;
		this.adr = this.s.getLocalAddress().getHostAddress();
		this.port = 3000;
		this.auth = false;
		try{
			r = new BufferedReader(new InputStreamReader(this.s.getInputStream()));
			w = new OutputStreamWriter(this.s.getOutputStream());
		}
		catch (IOException e){
			System.out.println(e);
		}
		this.user = "user";
		this.pass = "pass";
		this.path = System.getProperty(user.home);

		//définir le data
		this.data;
		//définir un datasocket
		this.ds;
		this.dr;
		this.dw;
	}

	public void processRequest(){
		String req = null;

		if (req.contains("USER"))
			processUSER(req);
		if (req.contains("PASS"))
			processPASS(req);
		if (req.contains("RETR"))
			processRETR(req);
		if (req.contains("STOR"))
			processSTOR(req);
		if (req.contains("LIST"))
			processLIST(req);
		if (req.contains("QUIT"))
			processQUIT(req);
	}

	private void processUSER(String req){
		if (!this.auth && this.user.equals(this.data)){
			this.auth = true;
			System.out.println("331 : user ok");
		}
		else
			System.out.println("530 : bad user");
	}

	private void processPASS(String req){
		if (this.auth && this.pass.equals(this.data))
			System.out.println("230 : login ok");
		else
			System.out.println("530 : bad password");
	}

	private void processRETR(String req){
		Path file = Paths.get(this.path);

        try {
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
        catch (IOException e) {System.out.println("550 : error connexion or login");}
	}

	private String processSTOR(String req){
		
		return "TRUE";
	}

	private String processLIST(String req){
		
		return "TRUE";
	}

	private String processQUIT(String req){
		
		return "TRUE";
	}
}