import java.net.Socket;


public class FtpRequest {

	private Socket s;
	
	public FtpRequest(Socket s){
		this.s = s;
		processRequest(s.toString());
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