<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Map"%>
<%@ page import="car.tp4.*"%>

<html>
<head>
	<meta charset="UTF-8"/>
	<title>Bibliothèque | Panier</title>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.5/css/materialize.min.css">
</head>
<body>
	<div class="navbar-fixed">
	  <nav>
      <div class="nav-wrapper teal lighten-1">
        <a href="#" class="brand-logo right">CAR-TP4</a>
        <ul id="nav-mobile" class="left hide-on-med-and-down">
          <li><a href="liste_livres.jsp">Bibliothèque</a></li>
          <li><a href="liste_auteurs.jsp">Auteurs</a></li>
          <li><a href="panier.jsp">Mon Panier</a></li>
        </ul>
      </div>
  	  </nav>
  	</div>
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