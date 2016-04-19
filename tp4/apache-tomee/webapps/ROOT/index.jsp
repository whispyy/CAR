<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8"/>
	<title>Bibliotheque</title>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.5/css/materialize.min.css">
</head>
<body>
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
