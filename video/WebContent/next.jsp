<!DOCTYPE html>
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
<c:set var="user" value="${id}"></c:set>
<%
	MongoClient mongo = new MongoClient("localhost");
	DB db = mongo.getDB("Video_Project");

	DBCollection files = db.getCollection("Videos.files");
	
	BasicDBObject dbobj = new BasicDBObject();
	
	dbobj.put("user",new ObjectId(request.getAttribute("id")+""));
	DBCursor cu2 = files.find(dbobj);	
	
	ArrayList<String> list = new ArrayList<String>(); 
	int i = 0;
	while(cu2.hasNext()){
		String name = cu2.next().get("filename").toString();
		list.add(name);
		System.out.println(""+list.get(i));
		i++;
	}
	pageContext.setAttribute("video",list);
%>

<html lang="en">
<head>
  <title>Options</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="css/bootstrap.min.css">
  <script src="js/jquery.min.js"></script>
  <script src="js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
  <h1>choose option</h1>
	
  <form action = "Temp_upload" method = "post" >
    	<input type = "hidden" name = "user1" value="${user}"> 
   <input type="submit" value = "Upload Video"></input>  
  </form>
  <form action="download" method="post">
 	  <br>
 	  <br>
 	  <input type="submit" value="Play Video"></input>   	
  		<input type = "hidden" name ="user1" value="${user}"> 
		<br>
		<br>
		<c:forEach items="${video}" var="current">
			<br>
        	<tr>
          	<td><input type = "radio" name = "radio" /><c:out value="${current}"></c:out><td>
        	</tr>
      	</c:forEach>
	</form>
</div>

</body>
</html>
