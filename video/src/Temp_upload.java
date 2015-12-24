

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Temp_upload
 */
@WebServlet("/Temp_upload")
public class Temp_upload extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public Temp_upload() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("user1");
		request.setAttribute("id",id);
		ServletContext sc = getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher("/indexnew.jsp");
		rd.forward(request, response);
	}

}
