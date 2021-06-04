package search.api;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.connection.DBConnection;
@WebServlet("/SearchByYear")
public class SearchByYear extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public SearchByYear() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request,response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//doGet(request, response);
		String years=request.getParameter("year");
		//String years="2018";
		//System.out.println("2018");
		response.setContentType("application/json");
		Search sr=new Search(DBConnection.getConnection());
		try {
			//System.out.println("Function call");
			String resJson = sr.searchByYear(years);
			PrintWriter out = response.getWriter();
			out.println(resJson);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
