package car.tp4;

import javax.servlet.ServletConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(urlPatterns = "/init", loadOnStartup = 1, asyncSupported = true)
public class StartupServlet extends HttpServlet {

	private static final long serialVersionUID = -1535804208755992063L;

	public void init(ServletConfig config) {
		Bibliotheque bibliotheque = new Bibliotheque();
		bibliotheque.init();
		config.getServletContext().setAttribute("LIB", bibliotheque);
		
		Panier panier = new Panier();
		config.getServletContext().setAttribute("PANIER", panier);
		
		ListeCommandes commandes = new ListeCommandes();
		config.getServletContext().setAttribute("COMMANDES", commandes);
		
		System.out.println("_______My servlet has been initialized_______");
	}
}