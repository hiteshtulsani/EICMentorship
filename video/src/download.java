

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;


//////<a href="indexnew2.jsp">
/**
 * Servlet implementation class download
 */
@WebServlet("/download")
public class download extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public download() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("-------------------------");
		System.out.println(request.getParameter("user").toString());
		System.out.println("-------------------------");
		
		
		MongoClient mongo = new MongoClient("localhost");
		DB db = mongo.getDB("Video_Project");
		
		File f1 = new File("D://civilwars.mp4");
		if(!f1.exists())
			f1.createNewFile();
		System.out.println("Hi.....................");
		
		GridFS gfs = new GridFS(db,"Videos");
		DBCollection files = db.getCollection("Videos.files");
		DBCollection chunks = db.getCollection("Videos.chunks");
		BasicDBObject dbobj = new BasicDBObject();
		
		dbobj.put("filename","civilwars.mp4");
		DBCursor cu1 = files.find(dbobj);	
		String id = cu1.next().get("_id").toString();
		FileOutputStream fos = null;
		System.out.println(cu1.count()+"-----------"+id);
		dbobj = new BasicDBObject();
		dbobj.put("files_id",new ObjectId(id));
		DBCursor cu2 = chunks.find(dbobj);
		System.out.println(cu2.count()+"-----------"+id);
		fos = new FileOutputStream(f1,true);
		int count = 0;
		String h="";
		StringBuilder sb = new StringBuilder(h);
		while(cu2.hasNext()){
			byte data[]  = (byte []) cu2.next().get("data");
			sb.append(new String(data));
			fos.write(data);
			count++;
		}
		String da = sb+"";
		byte data_final[]  = da.getBytes();
		fos.close();
		
		/*
		response.reset();
		response.getOutputStream().write(data_final);
		*/
		
		response.sendRedirect("indexnew2.jsp");
	}

}
