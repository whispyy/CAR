package car.tp4;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/FormServlet"})
public class FormServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

		/**
	     * Processes requests for both HTTP
	     * <code>GET</code> and
	     * <code>POST</code> methods.
	     *
	     * @param request servlet request
	     * @param response servlet response
	     * @throws ServletException if a servlet-specific error occurs
	     * @throws IOException if an I/O error occurs
	     */
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        response.setContentType("text/html;charset=UTF-8");
	        PrintWriter out = response.getWriter();
	        try {
	            out.println("<!DOCTYPE html>");
	            out.println("<html>");
	            out.println("<head>");
	            out.println("<meta charset='UTF-8'/>");
	            out.println("<title>Servlet FormServlet</title>");            
	            out.println("</head>");
	            out.println("<body>");
	            out.println("<p>");
	            out.println("The title of the book is ");
	            String title = request.getParameter("title");
	            out.println(title);
	            out.println("</p>");
	            out.println("<p>");
	            out.println("It was written by");
	            String author = request.getParameter("author");
	            out.println(author);
	            out.println("and published the ");
	            String date = request.getParameter("date");
	            out.println(date);
	            out.println("</p>");
	            out.println("</body>");
	            out.println("<button> <a href=\"index.jsp?title=" + title + "&author=" + author + "&date=" + date + "\">");
	            out.println("Go to form </a> </button>");
	            out.println("</body>");
	            out.println("</html>");
	        } finally {            
	            out.close();
	        }
	    }

}
