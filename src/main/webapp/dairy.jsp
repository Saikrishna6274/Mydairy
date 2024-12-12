<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Dairy</title>
</head>
<body>
<form action="SaveDairyUpdate" method="post">
<table>
		<tr>
		 <td>PersonId</td>
		 <td><input type="text" name="id" value=<%=request.getAttribute("id") %>></td>
		</tr>
		<tr>
		 <td>Date</td>
		 <td><input type="date" name="date" value=<%=request.getAttribute("date") %>></td>
		 </tr>
		 <tr>
		 	<td>Description</td>
		 	<td><input type="text" name="description" value=<%=request.getAttribute("description")%>></td>
</table>
<input type="submit" value="save"/>
</form>
</body>
</html>