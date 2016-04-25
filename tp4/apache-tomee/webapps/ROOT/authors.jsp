<%@ page import="java.util.ArrayList"%>
<%@ page import="car.tp4.*"%>

<html>
<head>
	<meta charset="UTF-8"/>
	<title>Bibliothèque | Auteurs</title>
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
	<p><b>Authors : </b></p>
		<ul>
		
		<%	
			Library lib = (Library)application.getAttribute("LIB");
		
			if(lib != null) {
				ArrayList<Author> authors = lib.getAuthors();
				for (Author a : authors) {
					out.print("<li>" + a.getName() + "</li>");
				}
			}
		%>
		
		</ul>
  </body>
</html>
