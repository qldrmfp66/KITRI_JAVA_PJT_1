<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
	.head{
		text-align:center;
		background-color : yellow-green;
	}
	.head_item{
		display:inline-block;
		background-color : yellow-green;
	}
	.vcode-input {
	  width: 40px;
	  height: 40px;
	  text-align: center;
	  font-size: 18px;
	}
	
	.container {
	  width: 800px;
	  margin:0 auto;
	  padding: 100px;
	  background:#FFF;
	}

	.btn-animate {
	  float: left;
	  font-weight: 700;
	  text-transform: uppercase;
	  font-size: 13px;
	  text-align: center;
	  color: rgba(255,255,255, 1);
	  padding-top: 8px;
	  width: 100px;
	  height: 35px;
		border: none;
		border-radius: 20px;
	  margin-top: 23px;
	  margin-right:10px;
	  background-color: gray;
	  left: 0px;
	  top: 0px;
	  transition: all .5s ease, top .5s ease .5s, height .5s ease .5s, background-color .5s ease .75s; 
}
	
</style>
</head>

<body>

<!-- 헤더 -->
 <div class="head">
 	<h2 class="head_item"><a href="/index"><span>Do you Have Any Question?</span></a></h2>
	<!-- 로그인 했을때 -->
	<c:if test = "${not empty user_id}">
		<div class="head_item" style="float:right;">
			<ul class = "js-login" style="float:right;">
			    <li>${user_id}</li>
			    <li><a href="mypage">마이페이지</a></li>
			    <li><a href="logout" onclick="return logout();">로그아웃</a></li>
			</ul>
		 </div>
	</c:if>
	<c:if test = "${logout eq 'success'}">
		<script> alert ('로그아웃되었습니다.')</script>
	</c:if>
	<!-- 로그인 안했을때만 -->
	<c:if test = "${empty user_id}">
		<div class="head_item" style="float:right;">
	       <ul>
	          <li><a href="login">로그인</a></li>
	          <li><a href="signup">회원가입</a></li>
	       </ul>
	    </div>
   </c:if>
 </div>			
 
  <!-- 시계 -->
  	<div class="js-clock">
    	<h1 id="clock">00:00</h1>
    </div>

  <!-- 코드입력 -->
<form action="index" method="post">
	<div class="container">
	  <label for="vcode1">Enter 4-digit verification code</label>
	  <div class="vcode" id="vcode">
	    <input type="text" class="vcode-input" name="first_code" maxlength="1">
	    <input type="text" class="vcode-input" name="second_code" maxlength="1">
	    <input type="text" class="vcode-input" name="third_code" maxlength="1">
	    <input type="text" class="vcode-input" name="fourth_code" maxlength="1">
	  </div>
	  <div>
		  <div>
		  <button class="btn-animate" type="submit" formmethod="POST" value="join">참가하기</button>
		  </div>
		  <c:if test = "${not empty user_id}"> 
	  	  <div class="btn-animate"><a href="/createchat">새로운 채팅 만들기</a></div>
	  	  </c:if>
	  </div>
	</div>
	<c:if test = "${EnterResult == 0}">
			<script>
				alert("4자리 코드를 전부 입력해주세요.");
			</script>
	</c:if>
	<c:if test = "${EnterResult == -1}">
			<script>
				alert("잘못된 코드번호입니다.");
			</script>
	</c:if>

</form>

  <script type="text/javascript" src="/js/clock.js"></script>

</body>
</html>