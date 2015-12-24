<!DOCTYPE html>
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
  <select onchange="javascript:handleSelect(this)">
  <option value="video">Videos</option>
  <option value="video 1" ><a href="aaa.html">video 1</a></option>
  <option value="video 2">video 2</option>
  <option value="video 3">video 3</option>
</select>
<script type="text/javascript">
function handleSelect(elm)
{
window.location = elm.value+".html";
}
</script>
</body>
</html>
