<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
</head>
<body>
<!-- 로그인 폼 -->

	<div>
		<form action="login" method="post">
			<h3 style="text-align: center;">Login to your account</h3>
				<div>
					<input type="text" name="userid" maxlength="20">
				</div>
			   <div>
				    <input type="text" name="password" maxlength="20">
			   </div>
			<button type="submit" formmethod="POST">Login</button>			             
		</form>
		<a href="signup"><p>If you don't have an account, please join us!</p></a>	
		<c:if test = "${loginResult !=1 }">
			<script>
				alert("다시 로그인하세요.");
			</script>
		</c:if>
	</div>
</body>
</html>