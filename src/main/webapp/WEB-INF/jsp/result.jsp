
<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Result Page</title>
</head>
<body>
<form action="/result" method="get">
    <br>
    <h2><c:out value="${message}" default="Result"/></h2>
</form>
<br>
</body>
</html>
