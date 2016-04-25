<%@ page import="java.util.ArrayList"%>
<%@ page import="car.tp4.*"%>

<%
	Basket basket = (Basket) application.getAttribute("BASKET");
	Library lib = (Library) application.getAttribute("LIB");

	if (basket != null) {
		basket.add("Salut", lib);
	}
%>

<html>
<head>
	<meta charset="UTF-8"/>
	<title>Bibliothèque | Livres</title>
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
			Library lib2 = (Library) application.getAttribute("LIB");

			if (lib2 != null) {
				ArrayList<Book> books = lib2.getBooks();

				out.print("<tr><td>Title<td>Author</td><td>Year</td><td></td></tr>");
				for (Book b : books) {
					out.print("<form action=\"books.jsp\" method=\"post\">");
					out.print("<tr>");
					out.print("<td><input type=\"text\" name=\"titre\" value=\"" + b.getTitle()
							+ "\" disabled></td>");
					out.print("<td><input type=\"text\" name=\"auteur\" value=\"" + b.getAuthor()
							+ "\" disabled></td>");
					out.print("<td><input type=\"text\" name=\"annee\" value=\"" + b.getYear()
							+ "\" disabled></td>");
					out.print("<td><input type=\"submit\" value=\"Add to basket\"></td>");
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