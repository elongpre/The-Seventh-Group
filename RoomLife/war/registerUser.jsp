<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="RoomLife.css">
		<title>Insert title here</title>
	</head>
	
	<body>
		<h1>New user</h1>
		<form action="/registerUser" method = "POST">
			<fieldset>
				<legend>User Information</legend>
				
				<label class="field" for="firstName">First Name:</label>
				<p><input type="text" name="firstname"></p>
				
				<label class="field" for="lastName">Last Name:</label>
				<p><input type="text" name="secondname"></p>
				
				<label class="field" for="Group">Group:</label>
				<p><input type="text" name="group"></p>
				
				<label class="field" for="Email">Email:</label>
				<p><input type="text" name="email"></p>
				
				<input type="submit" value="Register">
			</fieldset>
		
		</form>
	</body>
</html>