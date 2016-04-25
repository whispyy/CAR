<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8"/>
	<title>Bibliotheque | Accueil</title>
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
	<h1 class="center">Ajouter un livre</h1>
	<!-- formulaire -->
	<form class="container" action="index.jsp">
		<input type="text" name="auteur" placeholder="Auteur" value='<%= request.getParameter("auteur") %>'>
		<br/>
		<input type="text" name="titre" placeholder="Titre" value='<%= request.getParameter("titre") %>'>
		<br/>
		<label for="annee">Annee</label>
		<input type="number" name="annee" min="0" max="2100" value='<%= request.getParameter("annee") %>'>
		<br/>
		<button class="btn" type="submit">Send</button>
	</form>
	<h1 class="center">Livre</h1>
	<!--résultat obtenu lors du clic sur Send -->
	<div class="container center">
		<div class="section">
		<p>Auteur :</p>
		<%= request.getParameter("auteur") %>
		</div>
		<div class="divider"></div>
		<div class="section">
		<p>Titre :</p>
		<%= request.getParameter("titre") %>
		</div>
		<div class="divider"></div>
		<div class="section">
		<p>Annee :</p>
		<%= request.getParameter("annee") %>
		</div>
	</div>

</body>
</html>
