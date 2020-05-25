<%@page import="ChatDB.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<link rel ="stylesheet" href="style.css"/>
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
	border: 0.2em inset black;
	width: 400px;
	height:200px;
	background-color : white;
	padding : 5%;
	margin: 10px;
}
.head{
	text-align:center;
	background-color : yellow-green;
}
.head_item{
	display:inline-block;
	background-color : yellow-green;
}

</style>
</head>
<body>
<!-- 헤더 -->
 <div class="head">
 	<h1 class="head_item"><a href="/index"><span>Do you Have Any Question?</span></a></h1>
	<div class="head_item" style="float:right;">
			<ul class = "js-login" style="float:right;">
			    <li>${user_name}의 마이페이지</li>
			    <li><a href="logout" onclick="return logout();">로그아웃</a></li>
			</ul>
	</div>
 </div>			
  
<!-- 바디 -->
<div class="container">
<c:forEach var ="n" items="${chats}">
	<div class="item">
		<h1>${n.chat_title}</h1>
		<a href="question_main?chat_code=${n.chat_code}">${n.chat_code}</a>
		<p>${n.chat_date}</p>
		<a href="deletechat?chat_code=${n.chat_code}">삭제하기</a>
	</div>
</c:forEach>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
</body>
</html>