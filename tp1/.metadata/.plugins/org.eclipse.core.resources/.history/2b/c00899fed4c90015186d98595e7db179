import java.net.Socket;
//import java.io.BufferedInputStream;
//import java.io.BufferedOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import java.io.BufferedReader;
import java.io.BufferedWriter;

public class FtpRequest {

	private Socket s;
	private BufferedReader r;
	private OutputStreamWriter w;

	public FtpRequest(Socket s){
		this.s = s;

		try{
			r = new BufferedReader(new inputStreamReader(this.s.getInputStream()));
			w = new OutputStreamWriter(this.s.getOutputStream());
		}
		catch (IOexception e){
			System.out.println(e);
		}
	}

	public void processRequest(String req){
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

	private String processUSER(String req){
		System.out.println ("USER : "+req);



		return "TRUE";
	}

	private String processPASS(String req){
		
		return "TRUE";
	}

	private String processRETR(String req){
		
		return "TRUE";
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