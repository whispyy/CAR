

import javax.servlet.ServletConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(urlPatterns = "/init", loadOnStartup = 1, asyncSupported = true)
public class StartupServlet extends HttpServlet {

	private static final long serialVersionUID = -1535804208755992063L;

	public void init(ServletConfig config) {
		Library bibliotheque = new Library();
		bibliotheque.init();
		config.getServletContext().setAttribute("LIB", bibliotheque);
		
		Basket panier = new Basket();
		config.getServletContext().setAttribute("PANIER", panier);
		
		ListeCommandes commandes = new ListeCommandes();
		config.getServletContext().setAttribute("COMMANDES", commandes);
		
		System.out.println("Servlet online.");
	}
}
