package search.api;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.connection.DBConnection;

@WebServlet("/SearchByYearandCategory")
public class SearchByYearandCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public SearchByYearandCategory() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
		response.setContentType("application/json");
		String years=request.getParameter("year");
		String categories=request.getParameter("category");
		//System.out.println("Year"+years);
		//System.out.println("Category"+categories);
		Search sr=new Search(DBConnection.getConnection());
		try {
			//System.out.println("Function call");
			String resJson = sr.searchByYearAndCategory(years,categories);
			PrintWriter out = response.getWriter();
			out.println(resJson);
			//System.out.println(resJson);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
