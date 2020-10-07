
<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Main Page</title>
</head>
<body>
<h2><c:out value="${message}" default="Specify the path to the csv file with water pipes data"></c:out></h2>
<br>
<br>
<form action="/water-pipeline" method="put">
    <input type="text" size="30" name="Enter the path to the file" required/>
    <br>
    <input type="submit" value="ENTER"/>
</form>
</body>
</html>