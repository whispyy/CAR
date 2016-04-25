<%@ page import="java.util.ArrayList"%>
<%@ page import="car.tp4.*"%>

<html>
	<body>
	<p><b>Liste des auteurs : </b></p>
		<ul>
		
		<%	
			Bibliotheque bibliotheque = (Bibliotheque)application.getAttribute("LIB");
		
			if(bibliotheque != null) {
				ArrayList<String> auteurs = bibliotheque.listeAuteurs();
				for (String auteur : auteurs) {
					out.print("<li>" + auteur + "</li>");
				}
			}
		%>
		
		</ul>
	</body>
</html>
