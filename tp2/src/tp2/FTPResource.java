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

    /* -- LOGIN --*/

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

	/**
     * Login test sur le serveur FTP
     * @return un message d'erreur ou la liste des fichiers
     */
    @POST
    @Path("/data")
    public String LogInAction(@FormParam( "name" )final String name,
                              @FormParam( "pass" ) final String password )throws IOException  {

        commandes.CMDUSER(name);
        if(commandes.CMDPASS(password)){
            this.login = true;
            return getFiles();
        }
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+
                "<html xmlns=\"<xmlns />\" xml:lang=\"en\">"+
                "<head>" +
                "<title>Login</title>" +
                " </head>" +
                "<body>" +
                " <form action=\"/rest/api/res/login\" method=\"GET\">" +
                "<label for=\"name\">Login fail !</label>" +
                " <br/>" +
                "<input type=\"submit\" value=\"Submit\" />" +
                "</form>" +
                "</body>" +
                "</html>";
    }

    /**
     * Logout
     * @return Un formulaire de connexion
     */
    @GET
    @Path("/logout")
    @Produces("text/html")
    public String logOut() throws IOException {

        this.login = false;
        return logIn();

    }

    /* -- READ -- */
    /**
     * Lit le fichier "name"
     * @return  Le contenu du fichier "name" ou la liste des fichiers si "name" est un dossier.
     */
    @GET
    @Path("/data/{name}")
    @Produces("text/html")
    public String getFile(@PathParam( "name" ) String name)throws IOException  {

        if(!login)
            return logIn();

        if(this.commandes.CMDCWD("/data")){
            String result = searchFile(name);
            if(this.commandes.getCurrentDir().equals("data")){
                result += "<form action=\"/rest/api/res/"+this.commandes.getCurrentDir()+"/"+name+"/edit\">"+
                        "<input type=\"submit\" value=\"Edit\">"+
                        "</form>";
                result += "<button onclick= \"p()\">Delete</button>"+
                        "<script type=\"text/javascript\">"+
                        "function p(){"+
                        "console.log(\"Ici\");"+
                        "xhr=window.ActiveXObject ? new ActiveXObject(\"Microsoft.XMLHTTP\") : new XMLHttpRequest();"+
                        "xhr.onreadystatechange=function(){};"+
                        "xhr.open(\"DELETE\", \"http://localhost:8080/rest/api/res/data/"+name+"\");"+
                        "xhr.send(null);"+
                        "};"+
                        "</script>";
                result +="<form action=\"/rest/api/res/data\">"+
                        "<input type=\"submit\" value=\"Return\">"+
                        "</form></br>";
            }else{
                result +="<form action=\"/rest/api/res/"+this.commandes.getCurrentDir()+"/add\">"+
                        "<input type=\"submit\" value=\"Add File\">"+
                        "</form></br>";
                result +="<form action=\"/rest/api/res/logout\">"+
                        "<input type=\"submit\" value=\"Logout\">"+
                        "</form></br>";
            }
            return result;
        }
        return "<h1>PATH NOT FOUND</h1>";
    }

    /**
     * Lit le fichier "dir/name"
     * @return  Le contenu du fichier "dir/name" ou la liste des fichiers si "dir/name" est un dossier.
     */
    @GET
    @Path("/data/{dir}/{name}")
    @Produces("text/html")
    public String getFile(@PathParam( "name" ) String name,@PathParam( "dir" ) String dir)throws IOException  {

        if(!login)
            return logIn();

        System.out.println(this.commandes.getCurrentDir());
        if(this.commandes.CMDCWD("/data/"+dir)){
            String result = searchFile(name);
            if(this.commandes.getCurrentDir().equals("data/"+dir)){
                result += "<form action=\"/rest/api/res/"+this.commandes.getCurrentDir()+"/"+name+"/edit\">"+
                        "<input type=\"submit\" value=\"Edit\">"+
                        "</form></br>";
                result += "<button onclick= \"p()\">Delete</button>"+
                        "<script type=\"text/javascript\">"+
                        "function p(){"+
                        "console.log(\"Ici\");"+
                        "xhr=window.ActiveXObject ? new ActiveXObject(\"Microsoft.XMLHTTP\") : new XMLHttpRequest();"+
                        "xhr.onreadystatechange=function(){};"+
                        "xhr.open(\"DELETE\", \"http://localhost:8080/rest/api/res/data/"+dir+"/"+name+"\");"+
                        "xhr.send(null);"+
                        "};"+
                        "</script>";
                result +="<form action=\"/rest/api/res/data/"+dir+"\">"+
                        "<input type=\"submit\" value=\"Return\">"+
                        "</form></br>";
            }else{
                result +="<form action=\"/rest/api/res/"+this.commandes.getCurrentDir()+"/add\">"+
                        "<input type=\"submit\" value=\"Add File\">"+
                        "</form></br>";
                result +="<form action=\"/rest/api/res/logout\">"+
                        "<input type=\"submit\" value=\"Logout\">"+
                        "</form></br>";
            }
            return result;

        }
        return "<h1>PATH NOT FOUND</h1>";
    }
    

}

