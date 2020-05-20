<%@page import="ChatDB.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
body{
	margin : 10px;
}
h1, p{
	margin: 0;
}
.container{
	margin-left : auto;
	margin-left : auto;
}
.item{
	display: inline-block;
	margin-left : auto;
	margin-left : auto;
	border: 0.4em inset white;
	width: 400px;
	height:200px;
	background-color : white;
	padding : 5%;
	margin: 10px;
}

</style>
</head>
<body>

<div class="container">
<c:forEach var ="n" items="${chats}">
	<div class="item">
		<h1>${n.chat_title}</h1>
		<a href="mypage/chatting?chat_code=${n.chat_code}">${n.chat_code}</a>
		<p>${n.chat_date}</p>
	</div>
</c:forEach>
</div>


</body>
</html>