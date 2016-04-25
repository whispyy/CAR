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
				out.print("<table><tr><td>Title<td>Author</td><td>Year</td><td>Qte</td></tr>");
				for (Map.Entry<Book, Integer> map : commande.getBooks().entrySet()) {
					Book b = map.getKey();

					out.print("<tr>");
					out.print("<td>" + b.getTitre() + "</td>");
					out.print("<td>" + b.getAuteur() + "</td>");
					out.print("<td>" + b.getAnnee() + "</td>");
					out.print("<td>" + map.getValue() + "</td>");

					out.print("</tr>");
					out.print("</form>");
				}
				out.print("</table>");
			}
		}
	%>
</body>
</html>
