package car.tp4;


import javax.servlet.ServletConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(urlPatterns = "/init", loadOnStartup = 1, asyncSupported = true)
public class StartupServlet extends HttpServlet {

	private static final long serialVersionUID = -1535804208755992063L;

	public void init(ServletConfig config) {
		Library lib = new Library();
		lib.initBooks();
		config.getServletContext().setAttribute("LIB", lib);
		
		Basket basket = new Basket();
		config.getServletContext().setAttribute("BASKET", basket);
		
		ListeCommandes commandes = new ListeCommandes();
		config.getServletContext().setAttribute("COMMANDES", commandes);
		
		System.out.println("Servlet online.");
	}
}