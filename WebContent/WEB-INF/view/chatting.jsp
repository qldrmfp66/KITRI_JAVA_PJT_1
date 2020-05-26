<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
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
 <div class="head">
<script>
	function shareKakaotalk(){
		var _title = "${chat.chat_title}"
		
		try{
			if(Kakao){
				Kakao.init("277d63deb226b1150f3a2ac34847f80f");
			};
		}catch(e){};
		
		Kakao.Link.sendDefault({
			objectType:"feed",
			content:{
				title:"${chat.chat_title}" + "질문방입니다.",
				description:"채팅번호" + "${chat.chat_code}",
				imageUrl:"/image/card_image.png",
				link:{
					mobileWebUrl:"http://localhost:8080/chatting",
					webUrl:"http://localhost:8080/chatting"
				}
			}
			,buttons:[
				{
					title:"질문 보기"
					,link :{
						mobileWebUrl:"http://localhost:8080/chatting",
						webUrl:"http://localhost:8080/chatting"
					}
				}
			]
		});
	}
</script>

 	<h1 class="head_item"><a href="/index"><span>Do you Have Any Question?</span></a></h1>
		<!-- 로그인했을때 -->
		<c:if test = "${not empty user_name}">
		<div class="head_item" style="float:right;">
			<ul class = "js-login" style="float:right;">
			    <li>${user_name}님 로그인 중입니다.</li>
			    <li><a href="mypage">마이페이지</a></li>
			    <li><a href="logout">로그아웃</a></li>
			</ul>
		 </div>
		</c:if>
		<!-- 로그인안했을때 -->
		<c:if test = "${empty user_name}">
			<div class="head_item" style="float:right;">
		       <ul>
		          <li>IP주소 :${anonymous_name}</li>
		          <li><a href="login">로그인</a></li>
		          <li><a href="signup">회원가입</a></li>
		       </ul>
		    </div>
  	    </c:if>
  	 <button type = "button" onClick="shareKakaotalk();">
   	 <img  onclick="shareKakaotalk();" src="//dev.kakao.com/assets/img/about/logos/kakaolink/kakaolink_btn_small.png" class="pointer"/>
	 </button>
 </div>			
 <!-- Createchat에서 넘어온경우 -->
 	<c:if test = "${not empty randCode}">
 		<script>
 			alert('팀원들에게 채팅코드 ${randCode}를 공유하세요!');
 		</script>
 	</c:if>

    <div class="margin-top first">
		<h3 class="hidden">${chat.chat_title}</h3>
			<table class="table">
				<tbody>
					<tr>
						<th>제목</th>
							<td class="text-align-left text-indent text-strong text-orange" colspan="3">${chat.chat_title} </td>
						</tr>
					<tr>
						<th>작성일</th>
							<td class="text-align-left text-indent" colspan="3">${chat.chat_date}</td>
					</tr>
					<tr>
						<th>입장코드</th>
							<td class="text-align-left text-indent" colspan="3">${chat.chat_code}</td>
					</tr>
				</tbody>
			</table>
	</div>
</body>
 
</html>