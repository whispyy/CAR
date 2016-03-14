package tp2;

import tp2.ftpClient.FTPClient;
import tp2.ftpClient.FTPCommandes;

import javax.ws.rs.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;


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
            this.login = true;
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
                result += "<form action=\"/rest/tp2/ftp/"+this.commandes.getCurrentDir()+"/"+name+"/edit\">"+
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
                result +="<form action=\"/rest/tp2/ftp/"+this.commandes.getCurrentDir()+"/add\">"+
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

        System.out.println(this.commandes.getCurrentDir());
        if(this.commandes.CMDCWD("/data/"+dir)){
            String result = searchFile(name);
            if(this.commandes.getCurrentDir().equals("data/"+dir)){
                result += "<form action=\"/rest/tp2/ftp/"+this.commandes.getCurrentDir()+"/"+name+"/edit\">"+
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
                result +="<form action=\"/rest/tp2/ftp/"+this.commandes.getCurrentDir()+"/add\">"+
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


    /* -- Fonctions supplémentaires --*/
    public String searchFile(String name) throws IOException{
        System.out.println(name);
        File f;

        f=new File(this.commandes.getCurrentDir()+"/"+name);
        if(f.exists()){
            System.out.println(f);
            if(f.isFile()){
                System.out.println("Dans méthode isFile");
                return read(f);
            }

            if(f.isDirectory()){
                if(this.commandes.CMDCWD(name)){
                    return list(f);
                }


            }
        }else{
            System.out.println("FileNotFound");
            boolean retour = this.commandes.CMDRETR(name);
            System.out.println(f);

            if(retour){

                if(!login)
                    return logIn();
                System.out.println("Dans méthode isFile");
                return read(f);
            }
            if(!retour){
                f.delete();
                Files.createDirectory(f.toPath());
                System.out.println("dossier :" +f.isDirectory());
                if(this.commandes.CMDCWD(name)){
                    return list(f);
                }
            }




        }
        return "<h1>FILE NOT FOUND</h1>";
    }

    public String read(File f) throws IOException{
        String result ="";
        FileInputStream br = new FileInputStream(f);

        byte [] buffer = new byte[(int) f.length()];
        while(br.read(buffer) > 0){
            result+= new String(buffer);
        }
        br.close();

        return result;
    }

    public String list(File f) throws IOException{
        String result="";
        System.out.println(this.commandes.getCurrentDir());
        result = this.commandes.CMDLIST("");
        String[] test = result.split("\r\n");
        result="";
        for(int i = 0; i < test.length - 1; i++){
            if(test[i].length()>0){

                result+="<a href='/rest/tp2/ftp/"+this.commandes.getCurrentDir()+"/"+test[i]+"'>" +  test[i] + "</a></br>";
            }
        }
        result+="<a href='/rest/tp2/ftp/"+this.commandes.getCurrentDir()+"/..'>..</a></br>";
        System.out.println("here");
        return result ;

    }
}

