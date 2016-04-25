<%@ page import="java.util.ArrayList"%>
<%@ page import="car.tp4.*"%>

<%
	Basket basket = (Basket) application.getAttribute("BASKET");
	Library lib = (Library) application.getAttribute("LIB");

	if (basket != null) {
		basket.add(Integer.parseInt(idStr), lib);
	}
%>

<html>
<head>
	<meta charset="UTF-8"/>
	<title>Biblioth�que | Livres</title>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.5/css/materialize.min.css">
</head>
<body>
	<div class="navbar-fixed">
	  <nav>
      <div class="nav-wrapper teal lighten-1">
        <a href="#" class="brand-logo right">CAR-TP4</a>
        <ul id="nav-mobile" class="left hide-on-med-and-down">
          <li><a href="books.jsp">Library</a></li>
          <li><a href="authors.jsp">Authors</a></li>
          <li><a href="basket.jsp">My Basket</a></li>
        </ul>
      </div>
  	  </nav>
  	</div>
	<p>
		<b>Liste des livres : </b>
	</p>
	<table>

		<%
			Bibliotheque bibliotheque = (Bibliotheque) application.getAttribute("LIB");

			if (bibliotheque != null) {
				ArrayList<Livre> livres = bibliotheque.listeLivres();

				out.print("<tr><td>Titre<td>Auteur</td><td>Ann�e</td><td></td></tr>");
				for (Livre livre : livres) {
					out.print("<form action=\"liste_livres.jsp\" method=\"post\">");
					out.print("<tr>");
					out.print("<td hidden><input type=\"hidden\" name=\"id\" value=\"" + livre.getId() + "\"></td>");
					out.print("<td><input type=\"text\" name=\"titre\" value=\"" + livre.getTitre()
							+ "\" disabled></td>");
					out.print("<td><input type=\"text\" name=\"auteur\" value=\"" + livre.getAuteur()
							+ "\" disabled></td>");
					out.print("<td><input type=\"text\" name=\"annee\" value=\"" + livre.getAnnee()
							+ "\" disabled></td>");
					out.print("<td><input type=\"submit\" value=\"Ajouter au panier\"></td>");
					out.print("</tr>");
					out.print("</form>");
				}
			}
		%>

	</table>
</body>
</html>
<style type="text/css">
td {
	border: 1px solid black;
	padding: 5px;
}

input[type="text"][disabled] {
	color: black;
	border: none;
	background: none;
}
</style>