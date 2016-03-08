package tp2;

import exemple.ftpClient.FTPClient;
import exemple.ftpClient.FTPCommandes;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.io.IOException;

/**
 * Created by durand on 01/03/16.
 */

@Path("/ftp")
public class FTPResource {
	private FTPCommandes commandes;
	private FTPClient client;
	private boolean login;

	public FTPResource(){
		try {
			this.client = new FTPClient();
            this.commandes=this.client.getCommandes();
            System.out.println("Client OK");
		} catch (IOException e) {
			e.printStackTrace();
            System.out.println("Client error");
		}
		this.login = true;
	}

    @GET
    @Produces("text/html")
    public String sayHello() {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+
                "<html xmlns=\"<xmlns />\" xml:lang=\"en\">"+
                "<head><title>Client FTP</title></head>"+
                "<br/>" +
                "<body><h1>FTP</h1>" +
                "<br/>" +
                "<p>Cliquer ici pour se connecter :"+
                "<a href='./ftp/login'>Login</a>"+
                "</p></body></html>";
    }

	/**
	 * Login
	 * @return un formulaire de connexion
	 */
	@GET
	@Path("/login")
	@Produces("text/html")
	public String logIn() throws IOException {

		return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+
				"<html xmlns=\"<xmlns />\" xml:lang=\"en\">"+
				"<head>" +
				"<title>Login</title>" +
				" </head>" +
				"<body>" +
				" <form action=\"/rest/api/res/data\" method=\"POST\">" +
				"<label for=\"name\">Name</label>" +
				"<input type=\"text\" name=\"name\" />" +
				"<br/>" +
				"<label for=\"name\">Password</label>" +
				"<input type=\"password\" name=\"pass\" />" +
				" <br/>" +
				"<input type=\"submit\" value=\"Submit\" />" +
				"</form>" +
				"</body>" +
				"</html>";
	}

}

