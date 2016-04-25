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
		Basket basket = (Basket) application.getAttribute("BASKET");
		String cmd = request.getParameter("commande");
		if (cmd != null && cmd.equals("valide")) {
			out.print("<p><b>Commande : </b></p>");

			ListeCommandes commandes = (ListeCommandes) application.getAttribute("COMMANDES");

			if (commandes != null && basket != null) {
				commandes.add(new Commande(basket));
			}
		} else {
			out.print("<p><b>Basket : </b></p>");
		}
	%>

	<table>

		<%
			if (basket != null) {
				Map<Book, Integer> books = basket.getBasket();

				out.print("<tr><td>Title<td>Author</td><td>Year</td><td>Qte</td></tr>");

				for (Map.Entry<Book, Integer> map : books.entrySet()) {
					Book b = map.getKey();

					out.print("<tr>");
					out.print("<td>" + b.getTitre() + "</td>");
					out.print("<td>" + b.getAuteur() + "</td>");
					out.print("<td>" + b.getAnnee() + "</td>");
					out.print("<td>" + map.getValue() + "</td>");

					out.print("</tr>");
					out.print("</form>");
				}
			}
		%>
	</table>
	<%
		if (cmd == null) {
	%>
	<form action="basket.jsp" method="get">
		<input type="hidden" value="valide" name="commande"> <input
			type="submit" value="Passer commande">
	</form>
	<%
		} else {
			basket.clear();
		}
	%>
</body>
</html>
