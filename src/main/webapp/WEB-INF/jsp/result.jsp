<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Result Page</title>
</head>
<body>
<h2>Result</h2>
<br>
<c:forEach var="movie" items="${message}">
    ${movie}
    <br>
</c:forEach>
</table>

<br>
<a href="/">Go to start</a>
<br>
</body>
</html>
