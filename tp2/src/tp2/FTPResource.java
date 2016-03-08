package tp2;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * Exemple de ressource REST accessible a l'adresse :
 * 
 * 		http://localhost:8080/rest/tp2/helloworld
 * 
 * @author Lionel Seinturier <Lionel.Seinturier@univ-lille1.fr>
 */
@Path("/ftp")
public class FTPResource {

	@GET
	@Produces("text/html")
	public String sayHello() {
		return "<h1>Yo FTP ! </h1>";
	}

	@GET
	@Path("/book/{isbn}")
	@Produces("text/html")
	public String getBook( @PathParam("isbn") String isbn ) {
		return "<h2>Book: "+isbn+"</h2>";		 
	}

	@GET
	@Path("{var: .*}/stuff")
	public String getStuff( @PathParam("var") String stuff ) {
		return "Stuff: "+stuff;
	}
}

