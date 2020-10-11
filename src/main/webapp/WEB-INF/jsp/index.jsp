<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Main Page</title>
</head>
<body>
<h2>Specify the path to the csv file with water pipes data</h2>
<h3>The file must be on the computer. Specify the absolute path to the file</h3>
<h3>In the file path, the file name must end with - FileName.csv</h3>
<br>
<br>
<form action="/water-pipeline" method="post">
    <input type="text" size="30" name="filePath" placeholder="Enter the path to the file" required/>
    <br>
    <input type="submit" value="ENTER"/>
</form>
</body>
</html>