package tp2;

import tp2.ftpClient.FTPClient;
import tp2.ftpClient.FTPCommandes;

import javax.ws.rs.*;
import java.io.File;
import java.io.IOException;


/**
 * Created by durand on 01/03/16.
 */

@Path("/ftp")
public class FTPResource {
	protected static FTPCommandes commandes;
	private FTPClient client;
	protected static boolean login;

	public FTPResource(){
		try {
			this.client = new FTPClient();
            commandes=this.client.getCommandes();
            System.out.println("Client OK");
		} catch (IOException e) {
			e.printStackTrace();
            System.out.println("Client error");
		}
		login = true;
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
	public static String logIn() throws IOException {

		return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+
				"<html xmlns=\"<xmlns />\" xml:lang=\"en\">"+
				"<head>" +
				"<title>Login</title>" +
				" </head>" +
				"<body>" +
				" <form action=\"/rest/tp2/ftp/data\" method=\"POST\">" +
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
            login = true;
            return "<?xml version=\\\"1.0\\\" encoding=\\\"UTF-8\\\"?>\"+\n" +
                    "                \"<html xmlns=\\\"<xmlns />\\\" xml:lang=\\\"en\\\">\"+\n" +
                    "                \"<head>\" +\n" +
                    "                \"<title>Login OK</title>\" +\n" +
                    "                \" </head>\" +\n" +
                    "                \"<body><h1>Login ok</h1>\" +"+
                    "                <a href='/data/'>Click here</a>"+
                    "</body>" +
                    "</html>";
        }
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+
                "<html xmlns=\"<xmlns />\" xml:lang=\"en\">"+
                "<head>" +
                "<title>Login</title>" +
                " </head>" +
                "<body>" +
                " <form action=\"/rest/tp2/ftp/login\" method=\"GET\">" +
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

        login = false;
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

        if(commandes.CMDCWD("/data")){
            String result = AdditionnalResources.searchFile(name);
            if(commandes.getCurrentDir().equals("data")){
                result += "<form action=\"/rest/tp2/ftp/"+ commandes.getCurrentDir()+"/"+name+"/edit\">"+
                        "<input type=\"submit\" value=\"Edit\">"+
                        "</form>";
                result += "<button onclick= \"p()\">Delete</button>"+
                        "<script type=\"text/javascript\">"+
                        "function p(){"+
                        "console.log(\"Ici\");"+
                        "xhr=window.ActiveXObject ? new ActiveXObject(\"Microsoft.XMLHTTP\") : new XMLHttpRequest();"+
                        "xhr.onreadystatechange=function(){};"+
                        "xhr.open(\"DELETE\", \"http://localhost:8080/rest/tp2/ftp/data/"+name+"\");"+
                        "xhr.send(null);"+
                        "};"+
                        "</script>";
                result +="<form action=\"/rest/tp2/ftp/data\">"+
                        "<input type=\"submit\" value=\"Return\">"+
                        "</form></br>";
            }else{
                result +="<form action=\"/rest/tp2/ftp/"+ commandes.getCurrentDir()+"/add\">"+
                        "<input type=\"submit\" value=\"Add File\">"+
                        "</form></br>";
                result +="<form action=\"/rest/tp2/ftp/logout\">"+
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

        System.out.println(commandes.getCurrentDir());
        if(commandes.CMDCWD("/data/"+dir)){
            String result = AdditionnalResources.searchFile(name);
            if(commandes.getCurrentDir().equals("data/"+dir)){
                result += "<form action=\"/rest/tp2/ftp/"+commandes.getCurrentDir()+"/"+name+"/edit\">"+
                        "<input type=\"submit\" value=\"Edit\">"+
                        "</form></br>";
                result += "<button onclick= \"p()\">Delete</button>"+
                        "<script type=\"text/javascript\">"+
                        "function p(){"+
                        "console.log(\"Ici\");"+
                        "xhr=window.ActiveXObject ? new ActiveXObject(\"Microsoft.XMLHTTP\") : new XMLHttpRequest();"+
                        "xhr.onreadystatechange=function(){};"+
                        "xhr.open(\"DELETE\", \"http://localhost:8080/rest/tp2/ftp/data/"+dir+"/"+name+"\");"+
                        "xhr.send(null);"+
                        "};"+
                        "</script>";
                result +="<form action=\"/rest/tp2/ftp/data/"+dir+"\">"+
                        "<input type=\"submit\" value=\"Return\">"+
                        "</form></br>";
            }else{
                result +="<form action=\"/rest/tp2/ftp/"+commandes.getCurrentDir()+"/add\">"+
                        "<input type=\"submit\" value=\"Add File\">"+
                        "</form></br>";
                result +="<form action=\"/rest/tp2/ftp/logout\">"+
                        "<input type=\"submit\" value=\"Logout\">"+
                        "</form></br>";
            }
            return result;

        }
        return "<h1>PATH NOT FOUND</h1>";
    }

    /*-- UPDATE --*/
    /**
     * Modifie le fichier "dir/name" avec les @formParam récupérés
     * @return   le contenu modifié du fichier
     */
    @POST
    @Path("/data/{dir}/{name}")
    public String updateFile(@PathParam("name") final String fileName,@PathParam( "dir" ) String dir,
                             @FormParam( "name" )final String name,
                             @FormParam( "content" ) final String content )throws IOException  {

        if(!name.equals(fileName)){
            File f = new File(commandes.getCurrentDir()+"/"+fileName);
            f.delete();
            commandes.CMDDELE(fileName);
        }
        File f = new File(commandes.getCurrentDir()+"/"+name);
        AdditionnalResources.write(f,content);
        commandes.CMDSTOR(name);
        return AdditionnalResources.read(f);
    }

    /*-- ADD --*/
    /**
     * Fait un formulaire pour ajouter un fichier
     * @return le formulaire
     */
    @GET
    @Path("/data/add")
    @Produces("text/html")
    public String addFile()throws IOException  {

        if(!login)
            return logIn();

        System.out.println(commandes.getCurrentDir());
        if(commandes.CMDCWD("/data")){
            return  "<!DOCTYPE html>"+
                    "<html>"+
                    "<head>	"+
                    "<meta charset=\"utf-8\" />"+
                    "<title>Add File</title>"+

                    "</head>"+
                    "<body>"+
                    "<label for=\"name\">Name</label>"+
                    "<input type=\"text\" name=\"name\" id=\"name\"/>"+
                    "<br/>"+
                    "Content :"+
                    "<TEXTAREA id=\"content\" NAME=\"content\" COLS=40 ROWS=6></TEXTAREA>"+
                    "<br/>"+
                    "<button onclick= \"p()\">Submit</button>"+
                    "<script type=\"text/javascript\">"+
                    "function p(){"+
                    "console.log(\"Ici\");"+
                    "xhr=window.ActiveXObject ? new ActiveXObject(\"Microsoft.XMLHTTP\") : new XMLHttpRequest();"+
                    "xhr.onreadystatechange=function(){};"+
                    "xhr.open(\"PUT\", \"http://localhost:8080/rest/tp2/res/data\");"+
                    "xhr.send(null);"+
                    "};"+
                    "</script>"+
                    "<form action=\"/rest/tp2/res/data\">"+
                    "<input type=\"submit\" value=\"Return\">"+
                    "</form></br>"+
                    "</body>"+
                    "</html>";
        }
        return "<h1>PATH NOT FOUND</h1>";
    }

    /*-- DELETE --*/
    @DELETE
    @Path("/data/{name}")
    @Produces("text/html")
    public String deleteFile(@PathParam ( "name" )final String name)throws IOException  {

        File f = new File(commandes.getCurrentDir()+"/"+name);
        if(commandes.CMDDELE(name)){
            f.delete();
            return "<html xmlns=\"<xmlns />\" xml:lang=\"en\">"+
                    "<head>" +
                    "<title>Delete</title>" +
                    " </head><body>" +
                    "<p><a href = \"/rest/tp2/res/data\">Go Back</a></p>" +
                    "</body>" +
                    "</html>";
        }
        return "<html xmlns=\"<xmlns />\" xml:lang=\"en\">"+
                "<head>" +
                "<title>Delete</title>" +
                " </head>" +
                "<body>" +
                " <form action=\"/rest/tp2/res/data\" method=\"GET\">" +
                "<label for=\"name\">Delete fail ! Le fichier n'existe pas ou le dossier n'est pas vide</label>" +
                " <br/>" +
                "<input type=\"submit\" value=\"Submit\" />" +
                "</form>" +
                "</body>" +
                "</html>";
    }

    /* -- Fonctions supplémentaires --*/

}

