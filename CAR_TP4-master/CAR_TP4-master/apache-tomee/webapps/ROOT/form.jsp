<%@ page import="car.tp4.*"%>

<html>
<head>
<style type="text/css">
.book td {
	border: 1px solid black;
	padding: 5px;
}

table {
	margin-bottom: 10px;
}
</style>
</head>
<body>

	<%
		String author = request.getParameter("author");
		String title = "";
		String year = "";
		if (author != null && !author.isEmpty()) {
			title = request.getParameter("title");
			year = request.getParameter("year");
			out.print("<table class='book'>");
			out.print("<tr><td>Auteur : </td><td>" + author + "</td></tr>");
			out.print("<tr><td>Titre : </td><td>" + title + "</td></tr>");
			out.print("<tr><td>Année : </td><td>" + year + "</td></tr>");
			out.print("</table>");

			Bibliotheque bibliotheque = (Bibliotheque) application.getAttribute("LIB");

			if (bibliotheque != null) {
				bibliotheque.add(title, author, year);
			}
		}
	%>

	<form action="form.jsp">
		<table>
			<tr>
				<td>Auteur :</td>
				<td><input type="text" name="author"
					value="<%if (author != null) {
				out.print(author);
			}%>"></td>
			</tr>
			<tr>
				<td>Titre :</td>
				<td><input type="text" name="title" value="<%=title%>"></td>
			</tr>
			<tr>
				<td>Année :</td>
				<td><input type="text" name="year" value="<%=year%>"></td>
			</tr>
		</table>

		<input type="submit">
	</form>
</body>
</html>
