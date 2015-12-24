

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		MongoClient mongo = new MongoClient("localhost");
		DB db = mongo.getDB("Video_Project");
		DBCollection table = db.getCollection("User_Details");
		BasicDBObject obj1 = new BasicDBObject();
		DBObject data = new BasicDBObject();
		
		obj1.put("Email", email);
		obj1.put("Password", password);
		
		DBCursor cu = table.find(obj1);
		if(cu.hasNext()){
		String id = cu.next().get("_id").toString();	
		System.out.println("I m in Login.");
		request.setAttribute("id",id);
		ServletContext sc = getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher("/next.jsp");
		rd.forward(request, response);
		}
		//Error page user not authenticated.
		else{
			
		}
	}
}
