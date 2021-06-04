package search.api;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.connection.DBConnection;
@WebServlet("/SearchByName")
public class SearchByName extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public SearchByName() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				//doGet(request, response);
				response.setContentType("application/json");
				String firstname=request.getParameter("firstname");
				String surname=request.getParameter("surname");
				//System.out.println("firstname"+firstname);
				//System.out.println("surname"+surname);
				Search sr=new Search(DBConnection.getConnection());
				try {
					//System.out.println("Function call");
					String resJson = sr.searchByName(firstname, surname);
					PrintWriter out = response.getWriter();
					out.println(resJson);
					//System.out.println(" after Function call");
				} catch (Exception e) {
					e.printStackTrace();
				}
	}
}