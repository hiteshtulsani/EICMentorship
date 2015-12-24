<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="user" value="${id}"></c:set>

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
            <div class="row">
                <h2>Upload Media Files &amp; Share Online</h2>
                <h3>Share your momments online easily</h3>    
                <form action="upload" method = "post" class="dropzone" id="upload_media">
                    <div class="inner row m0">
                        <div class="row m0 plus_ico">+</div>
                        	<input type = "hidden" name ="user" value="${user}"> 
                        <input type="file" name="file" id="myFile" class="sr-only"></input>
                        <label for="media_up_btn">Upload Media</label>
                    </div>
                    <button type="submit" class="button button-block"/>Upload it !</button>
                </form>
            </div>
        </div>
    </section> <!--Upload Form-->
  
  
<button onclick="playVid()" type="button">Play Video</button>
<button onclick="pauseVid()" type="button">Pause Video</button><br> 

<video id="myVideo" width="320" height="176">
  <source src="videos/xyz.mp4" type="video/mp4">
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
