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
	<p>
		<b>Liste des commandes : </b>
	</p>

	<%
		ListeCommandes commandes = (ListeCommandes) application.getAttribute("COMMANDES");

		if (commandes != null) {
			for (Commande commande : commandes.getListeCommandes()) {
				out.print("<table><tr><td>Titre<td>Auteur</td><td>Année</td><td>Quantité</td></tr>");
				for (Map.Entry<Livre, Integer> entry : commande.getListeLivre().entrySet()) {
					Livre livre = entry.getKey();

					out.print("<tr>");
					out.print("<td>" + livre.getTitre() + "</td>");
					out.print("<td>" + livre.getAuteur() + "</td>");
					out.print("<td>" + livre.getAnnee() + "</td>");
					out.print("<td>" + entry.getValue() + "</td>");

					out.print("</tr>");
					out.print("</form>");
				}
				out.print("</table>");
			}
		}
	%>
</body>
</html>
