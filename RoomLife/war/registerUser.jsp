<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
	</head>
	
	<body>
		<h1>New user</h1>
		<form action="/registerUser" method = "POST">
			First name: 
			<input type="text" name="firstname">
			<br>
			<br>
			
			Last name:
			<input type="text" name="secondname">
			<br>
			<br>
			
			Group:
			<input type="text" name="group">
			<br>
			<br>
			
			Email:
			<input type="text" name="email">
			<br>
			<br>
			
			<input type="submit" value="Submit">
		
		</form>
	</body>
</html>