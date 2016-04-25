<%@ page import="java.util.ArrayList"%>
<%@ page import="car.tp4.*"%>

<html>
	<body>
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
