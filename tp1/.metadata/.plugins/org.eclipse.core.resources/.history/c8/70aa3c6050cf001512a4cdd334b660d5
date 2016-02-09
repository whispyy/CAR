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

public class FtpRequest implements Runnable {

	private Socket s;
	private	String adr;
	private int port;
	private boolean auth;
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
		try {
			r = new BufferedReader(new InputStreamReader(this.s.getInputStream()));
			w = new OutputStreamWriter(this.s.getOutputStream());
		}
		catch (IOException e){
			System.out.println(e);
		}
		this.user = "user";
		this.pass = "pass";
		this.path = System.getProperty("user.home");
	}

	public void processRequest(){
		String req = null;

		while(req = this.r.readLine() != null){
			if (req.split(" ") > 1){
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
		else
			System.out.println("530 : bad password");
	}

	private void processRETR(){
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

	private String processSTOR(){
		
		return "TRUE";
	}

	private String processLIST(){
		
		return "TRUE";
	}

	private String processQUIT(){
		
		return "TRUE";
	}
}