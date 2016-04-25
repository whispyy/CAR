<%@ page import="java.util.ArrayList"%>
<%@ page import="car.tp4.*"%>

<%
	String idStr = request.getParameter("id");
	if (idStr != null) {
		Panier panier = (Panier) application.getAttribute("PANIER");
		Bibliotheque bibliotheque = (Bibliotheque) application.getAttribute("LIB");

		if (panier != null) {
			panier.add(Integer.parseInt(idStr), bibliotheque);
		}
	}
%>

<html>
<head>
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
</head>
<body>
	<p>
		<b>Liste des livres : </b>
	</p>
	<table>

		<%
			Bibliotheque bibliotheque = (Bibliotheque) application.getAttribute("LIB");

			if (bibliotheque != null) {
				ArrayList<Livre> livres = bibliotheque.listeLivres();

				out.print("<tr><td>Titre<td>Auteur</td><td>Année</td><td></td></tr>");
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
