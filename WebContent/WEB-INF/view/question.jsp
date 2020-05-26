<%@page import="UserDB.User"%>
<%@page import="ChatDB.Chat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
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

		String userId = "anonymous";
		String chatCode = "1111";
		if(session!= null && session.getAttribute("user") != null) {
			  User user = (User)(session.getAttribute("user"));
			  userId = user.getUser_id();
		}else{ //접속안했을때.... 
			String ip = (String)request.getAttribute("anonymous_name");
			userId = ip;
		}
		Chat chat = new Chat();
		chat = (Chat)(request.getAttribute("chat"));
		if(chat != null){
			chatCode = Integer.toString(chat.getChat_code());
		}
	%>

	<script type="text/javascript">
	/* 마지막 qNo */
	var lastNo = 0;
	
	/* submit */
	function submitFunction() {
		var userId = '<%= userId %>';
		var chatCode = '<%= chatCode %>';
		var questionContent = $('#questionContent').val();
		$.ajax({
			type: "POST",
			url: "./questionSubmit",
			data: {
				userId: encodeURIComponent(userId),
				chatCode: encodeURIComponent(chatCode),
				questionContent: encodeURIComponent(questionContent)
			},
		success: function(result){
			if(result == 1) {
				alert("success");
				/* 부트스트랩js 알람창 */
				/* autoClosingAlert('#successMessage', 2000); */
			} else if(result == 0){
				alert("다시 확인");
				/* autoClosingAlert('#dangerMessage', 2000); */
			} else {
				alert("데이터베이스 error");
				/* autoClosingAlert('#warningMessage', 2000); */
			}
		}
		});
		
		// 값 비우기 
		$('#questionContent').val('');
	}

	  
	// 좋아요 2안
	function likeFunction(qNo) {
		var userId= '<%= userId %>';
		
		$.ajax({
			type: "post",
			url: "./likeAction",
			data: {
				userId: encodeURIComponent(userId),
				questionNo: qNo
			},
			success: function(result){
				if(result == 11) {
					alert("좋아요!");
					likeCountUpdateFunction(userId, qNo);
					/* likeCountUpdateListFunction(lastNo); */

				} else if(result == 111) {
					alert("좋아요취소!");
					likeCountUpdateFunction(userId, qNo);
					/* likeCountUpdateListFunction(lastNo); */
					
				} else {
					alert("데이터베이스error"); 
				}
			}
		});
	} 
	
	/* 좋아요count update */
 	function likeCountUpdateFunction(userId, qNo) {
		 var questionNo = qNo;
		$.ajax({
			type: "post",
			url: "./likeUpdateServlet",
			data: {
				userId: encodeURIComponent(userId),
				questionNo: qNo
			},
			success: function(data){
				if(data == "") return;
				var parsed = JSON.parse(data);
				var result = parsed.result;
				$("#like_count"+questionNo).html(result[0][0].value);
				/* likeCountUpdateListFunction(lastNo); */
				}
			});
		}
	
 	/* 좋아요 순으로 순위 변동 --- 수정중
 	function likeCountUpdateListFunction(type){
 		$.ajax({
			type: "POST",
			url: "./questions",
			data: {
				listType: type
			},
		success: function(data){
			if(data == "") return;
			var parsed = JSON.parse(data);
			var result = parsed.result;
			console.log(result);
			result.sort(function (a,b){ return result[a][1].value > result[b][1].value ? -1 : result[a][1].value < result[b][1].valuee ? 1 : 0; });
			console.log(result);
			for(var i = 0; i < result.length; i++) {
				console.log(result[i][2]);
				$("#like_count"+result[i][2].value).insertAfter("#like_count"+result[i+1][2].value);
			}
			/* lastNo = Number(parsed.last);
		}
	});
 	}
 	
 	*/
	
	
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
		function addChat(chatContent,likeCount,qNo) {
			$('#chatList').append(
				'<div id="chat_div'+ qNo +'"">' +
				'<div>' +
				chatContent +
				'</div>' +
				'<form>' +
				'<input type="hidden" value="'+ qNo + '">'+
				'<input id = "like_btn" type="button" value="좋아요" onclick="likeFunction('+ qNo +');">' +
				'</form>' +
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
<!-- 헤더 -->
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
					mobileWebUrl:"http://localhost:8080/question_main?chat_code="+ "${chat.chat_code}",
					webUrl:"http://localhost:8080/question_main?chat_code="+ "${chat.chat_code}"
				}
			}
			,buttons:[
				{
					title:"질문 보기"
					,link :{
						mobileWebUrl:"http://localhost:8080/question_main?chat_code="+ "${chat.chat_code}",
						webUrl:"http://localhost:8080/question_main?chat_code="+ "${chat.chat_code}"
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
		<h4>채팅방코드: <%= chatCode %></h4>
		<h4>유저: <%= userId %></h4>
	</div>
	<div id="chatList">
           <!-- 채팅 리스트 출력 -->       
    </div>
    
    <textarea id="questionContent" style="height:80px;" placeholder="Enter message..." maxlength="100"></textarea>

	<button type="button" onclick="submitFunction();">Send</button>
	
	<!-- 부트스트랩js 알람창 -->
	<!-- 	
	<div class="alert alert-success" id = "successMessage" style="display: none;">
		<strong>메세지 전송에 성공하였습니다.</strong>
	</div>	
	
	<div class="alert alert-danger" id = "dangerMessage" style="display: none;">
		<strong>이름과 내용을 모두 입력해주세요</strong>
	</div>	
	
	<div class="alert alert-warning" id = "warningMessage" style="display: none;">
		<strong>데이터베이스 오류가 발생했습니다.</strong>
	</div>	
	 -->
		
	<script src="js/question.js"></script>
	
	<!-- 페이지 처음 불러올때 출력하기 -->
	<script type="text/javascript">
	$(document).ready(function() {
		questionListFunction('ten');
		getInfiniteChat();
	});
	</script>          
</body>
</html>