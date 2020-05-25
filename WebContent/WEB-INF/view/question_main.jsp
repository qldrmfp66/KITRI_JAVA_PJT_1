<%@page import="UserDB.User"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-type" content="text/html; charset=UTF-8">
	<meta name = "viewport" content="width=dvice-width, initial-scale=1">
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
	<title>질문 없으시죠</title>

	<!-- session 수정해야함 -->
	<% 
		String userId = "Anonymous";
		String chatCode = "1111";
	
		if(session!= null && session.getAttribute("user") != null) {
			  User user = (User)(session.getAttribute("user"));
			  userId = user.getUser_id();
		}else{ //접속안했을때.... 
			String ip = (String)request.getAttribute("anonymous_name");
			userId = ip;
		}
		 chatCode = request.getParameter("chat_code");
		 System.out.println( "마이페이지의 챗코드" + chatCode);
	%>

	<script type="text/javascript">
	var lastNo = 0;

	/* 질문 불러오기 */
	function questionListFunction(type) {
 		var chatCode = '<%= chatCode %>';
 		
		$.ajax({
			type: "POST",
			url: "./questions",
			data: {
				listType: type,
				chatCode: encodeURIComponent(chatCode)
			},
		success: function(data){
			if(data == "") return;
			var parsed = JSON.parse(data);
			var result = parsed.result;
			for(var i = 0; i < result.length; i++) {
				addChat(result[i][0].value, result[i][1].value, result[i][2].value);
			}
			lastNo = Number(parsed.last);
		}
		});
	}
		
		/* content만 출력
		++ 좋아요 갯수 추가 해야함 */
		function addChat(chatContent,likeCount, qNo) {
			$('#chatList').append(
					'<div id="chat_div'+ qNo +'"">' +
					'<div>' +
					chatContent +
					'</div>' +
					'<div id="like_count'+ qNo +'"">' +
					likeCount +
					'</div>' +
					'</div>' +
					'<hr>');
			
			/* 자동 스코롤  */
			$('#chatList').scrollTop($('#chatList')[0].scrollHeight);
		}
		
		/* 1초에 한번씩 정보불러오기  */
		function getInfiniteChat() {
			setInterval(function() {
				questionListFunction(lastNo);
			}, 1000);
		}
	</script>
	
</head>
<body>
<!-- 헤더 -->
<div class="head">
<script>
	function shareKakaotalk(){
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
					mobileWebUrl:"http://localhost:8080/chatting?chat_code="+ "${chat.chat_code}",
					webUrl:"http://localhost:8080/chatting?chat_code=" + "${chat.chat_code}"
				}
			}
			,buttons:[
				{
					title:"질문 보기"
					,link :{
						mobileWebUrl:"http://localhost:8080/chatting?chat_code="+ "${chat.chat_code}",
						webUrl: "http://localhost:8080/chatting?chat_code=" + "${chat.chat_code}"
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
 <!-- 채팅방 -->
	<div>
		<h4>채팅방코드: ${chat.chat_code}</h4>
	</div>
	<div id="chatList">
           <!-- 채팅 리스트 출력 -->       
    </div>
	
	<script type="text/javascript">
	$(document).ready(function() {
		questionListFunction("ten");
		getInfiniteChat();
	});
	</script>          
</body>
</html>