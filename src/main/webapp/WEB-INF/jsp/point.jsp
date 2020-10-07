<html>
<head>
    <title>Main Page</title>
</head>
<body>
<h2><c:out value="${message}" default="Specify the path to the csv file with water points data"></c:out></h2>
<br>
<%--<a href="/registration">REGISTER NEW USER</a>--%>
<br>
<form action="/point" method="put">
    <input type="text" size="30" name="Enter the path to the file" required/>
    <br>
        <input type="submit" value="ENTER"/>
</form>
</body>
</html>