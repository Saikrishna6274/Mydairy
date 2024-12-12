<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Delete page</title>
</head>
<body>
<%
String s=(String)request.getAttribute("msg");
if(s!=null)
	out.print(s);
%>
<form action="DeleteDairyid" method="post">
PERSON ID:<input type="text" name="id" required placeholder="EnterPerson Id"><br>
<input type="submit" value="search">
</form>
</body>
</html>