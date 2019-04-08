<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml"
  xmlns:th="http://www.thymeleaf.org"> 
	<head>
		<title>Registration Success</title>
	</head>
	<body>
		<center>
			<span th:text="'A verification email has been sent to: ' + ${emailId}"></span>
		</center>
		<div class="text-center"> <a href="localhost:8080">Go back to login page</a></div>
	</body>
</html>