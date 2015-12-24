

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.bson.types.ObjectId;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSInputFile;

/**
 * Servlet implementation class upload
 */
//indexnew
@WebServlet("/upload")
public class upload extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public upload() {
        super();
      }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	System.out.println("I m in Upload.");
	
	String id = request.getParameter("user");
	System.out.println(id+"-*******************");
	
	File newfile = new File(request.getParameter("file"));
	String uploadfile = newfile.getName();
	
	MongoClient mongo = new MongoClient("localhost");
	DB db = mongo.getDB("Video_Project");
	
	GridFS gfs = new GridFS(db,"Videos");
	GridFSInputFile gfsf = gfs.createFile(newfile);
	gfsf.setFilename(uploadfile.toString());
	ArrayList<String> users = new ArrayList<String>();
	gfsf.put("user",new ObjectId(id));
	gfsf.save();
	System.out.println("Video uploaded...........");
	response.sendRedirect("video1.jsp");
	System.out.println("I m out now...");	
	}
}
