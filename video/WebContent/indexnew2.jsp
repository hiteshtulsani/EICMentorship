<!DOCTYPE html>  
<%-- 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import = "org.bson.types.ObjectId" %>
<%@page import = "com.mongodb.BasicDBObject" %>
<%@page import = "com.mongodb.DB" %>
<%@page import = "com.mongodb.DBCollection" %>
<%@page import = "com.mongodb.DBCursor" %>
<%@page import = "com.mongodb.MongoClient" %>
<%@page import = "com.mongodb.gridfs.GridFS" %>
<%@page import = "com.mongodb.gridfs.GridFSDBFile" %>
<%@page import = "java.io.File" %>
<%@page import = "java.io.FileOutputStream" %>
<%@page import = "java.io.IOException" %>
<%@page language="java" import="java.util.*" pageEncoding="ISO-8859-1" %>
<%
	MongoClient mongo = new MongoClient("localhost");
	DB db = mongo.getDB("Video_Project");

	File f1 = new File("D://civilwars1.mp4");
	if(!f1.exists())
		f1.createNewFile();

	GridFS gfs = new GridFS(db,"Videos");
	DBCollection files = db.getCollection("Videos.files");
	DBCollection chunks = db.getCollection("Videos.chunks");
	BasicDBObject dbobj = new BasicDBObject();

	dbobj.put("filename","java.mp4");
	DBCursor cu1 = files.find(dbobj);	
	String id = cu1.next().get("_id").toString();
	FileOutputStream fos = null;
	System.out.println(cu1.count()+"-----------"+id);
	dbobj = new BasicDBObject();
	dbobj.put("files_id",new ObjectId(id));
	DBCursor cu2 = chunks.find(dbobj);
	fos = new FileOutputStream(f1,true);
	int count = 0;
	String t = "";
	StringBuilder sb = new StringBuilder(t);
	int pos = 0;
	while(cu2.hasNext()){
		byte data[]  = (byte []) cu2.next().get("data");
		for(int i = 0 ; i < data.length ; i++)
			sb.append(data[i]);
		fos.write(data);
	count++;
	}
	String data_video = sb+"";
	byte d[] = data_video.getBytes();
	fos.close();
%>	
--%>
<html >
  <head>
    <meta charset="UTF-8">
    <title>Popular Videos Playlist</title>    
        <link rel="stylesheet" href="css/style.css">
        <script type="text/javascript">
			function get() {
   			var fil = document.getElementById("myFile");
   			return fil;
		}
</script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  </head>

  <body>

    <h1>Popular Videos Playlist</h1>
<div>
<section class="row upload_media">
        <div class="container">
         
        </div>
    </section> <!--Upload Form-->
  
  
<button onclick="playVid()" type="button">Play Video</button>
<button onclick="pauseVid()" type="button">Pause Video</button><br> 

<!--  
<c:set var="videot" value="${d}" scope="request"></c:set>
-->
<video id="myVideo" width="640" height="480" >
    <source value= "videos/abc.mp4" type="video/mp4"> 
</video>

<script> 
var vid = document.getElementById("myVideo"); 

function playVid() { 
    myVideo.play(); 
} 

function pauseVid() { 
    myVideo.pause(); 
} 
</script>     
<pre id="responseData"></pre>
        <script src="js/index.js"></script>
  </body>
</html>
