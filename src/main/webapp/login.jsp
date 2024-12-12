 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>LoginPage</title>
<link rel="stylesheet" type="text/css" href="login.css">
</head>
<body>
<form action="LoginServlet1" method="post">
<div id="main">
<div id="img"><h2>My Diary</h2><img src="wel2.jpg" alt="" width="350px" height="500px"></div>
<div id="content"><h2>User Login</h2>
UserName:<br>
<input type="text" name="uname" placeholder="Enter UserName"><br><br>
Password:<br>
<input type="password" name="password" placeholder="Enter Password"><br><br><br>

<button id="login" type="submit">Login</button>
<h2>
<% String val=(String)request.getAttribute("msg");
if(val!=null)
out.print(val);%></h2>
<div id="new">
<a href="register.html">Create New Account</a><br><br>
<a href="Password.html">forgot password</a></div></div>
</div>
</form>
</body>
</html>