<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Map"%>
<%@ page import="car.tp4.*"%>

<html>
<head>
<style type="text/css">
td {
	border: 1px solid black;
	padding: 5px;
}

input[type="text"][readonly] {
	color: black;
	border: none;
	background: none;
}

table {
	margin-bottom: 10px;
}
</style>
</head>
<body>
	<%
		Panier panier = (Panier) application.getAttribute("PANIER");
		String commande_state = request.getParameter("commande");
		if (commande_state != null && commande_state.equals("valide")) {
			out.print("<p><b>Commande : </b></p>");

			ListeCommandes commandes = (ListeCommandes) application.getAttribute("COMMANDES");

			if (commandes != null && panier != null) {
				commandes.add(new Commande(panier));
			}
		} else {
			out.print("<p><b>Panier : </b></p>");
		}
	%>

	<table>

		<%
			if (panier != null) {
				Map<Livre, Integer> livres = panier.getListeLivre();

				out.print("<tr><td>Titre<td>Auteur</td><td>Année</td><td>Quantité</td></tr>");

				for (Map.Entry<Livre, Integer> entry : livres.entrySet()) {
					Livre livre = entry.getKey();

					out.print("<tr>");
					out.print("<td>" + livre.getTitre() + "</td>");
					out.print("<td>" + livre.getAuteur() + "</td>");
					out.print("<td>" + livre.getAnnee() + "</td>");
					out.print("<td>" + entry.getValue() + "</td>");

					out.print("</tr>");
					out.print("</form>");
				}
			}
		%>
	</table>
	<%
		if (commande_state == null) {
	%>
	<form action="panier.jsp" method="get">
		<input type="hidden" value="valide" name="commande"> <input
			type="submit" value="Passer commande">
	</form>
	<%
		} else {
			panier.clear();
		}
	%>
</body>
</html>
