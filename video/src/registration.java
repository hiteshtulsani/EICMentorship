

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
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
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;

/**
 * Servlet implementation class registration
 */
@WebServlet("/registration")
public class registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public registration() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fname = request.getParameter("firstname");
		String lname = request.getParameter("lastname");
		String email = request.getParameter("email");
		String pass	= request.getParameter("password");
		
		// for connect to server
		MongoClient mongo = new MongoClient("localhost");
		
		// for select the database
		DB db = mongo.getDB("Video_Project");
		
		//to get collection from db
		DBCollection table = db.getCollection("User_Details");
		
		//creating a documment object
		BasicDBObject obj = new BasicDBObject();
		obj.put("FirstName", fname);
		obj.put("LastName", lname);
		obj.put("Email", email);
		obj.put("Password", pass);
		
		DBObject check = new BasicDBObject();
		check.put("Email",email);
		DBCursor cu = table.find(check);
		
		//give the dialog box that this email is already exists.
		if(!cu.hasNext()){
			table.insert(obj);
		}
		check = new BasicDBObject();
		check.put("Email",email);
		cu = table.find(check);
		String id = cu.next().get("_id").toString();	
		System.out.println("I m in Login.");
		request.setAttribute("id",id);
		ServletContext sc = getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher("/next.jsp");
		rd.forward(request, response);
		response.sendRedirect("next.jsp");
	}
}
