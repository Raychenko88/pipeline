
<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Result Page</title>
</head>
<body>
<h2>In the file path, the file name must end with .tsv</h2>
    <br>
    <h3><c:out value="${message}" default="Result"/></h3>
    <br>
    <a href="/">Go to start</a>
<br>
</body>
</html>
