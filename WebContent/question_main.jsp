<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-type" content="text/html; charset=UTF-8">
	<meta name = "viewport" content="width=dvice-width, initial-scale=1">
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	
	<title>질문 없으시죠</title>

	<!-- session 수정해야함 -->
	<% 
		/* int userNo = 1;
		if(session.getAttribute("USER_NO") != null) {
			userID = (Integer) session.getAttribute("USER_NO");
		}
		
		String chatCode = null;
		if(session.getAttribute("CHAT_CODE") != null) {
			userID = (Integer) session.getAttribute("CHAT_CODE");
		} */
		
		String userNo = "1";
		String chatCode = "1111";
		
	%>

	<script type="text/javascript">
	var lastNo = 0;
	/* 질문 불러오기 */
	function questionListFunction(type) {
		<%-- var userNo = '<%= userNo %>'; --%>
		var chatCode = '<%= chatCode %>';
		$.ajax({
			type: "POST",
			url: "./questions",
			data: {
				/* userNo: encodeURIComponent(userNo),*/
				chatCode: encodeURIComponent(chatCode),
				listType: type
			},
		success: function(data){
			if(data == "") return;
			var parsed = JSON.parse(data);
			var result = parsed.result;
			for(var i = 0; i < result.length; i++) {
				addChat(result[i][0].value);
			}
			lastNo = Number(parsed.last);
		}
		});
	}
		
		/* content만 출력
		++ 좋아요 갯수 추가 해야함 */
		function addChat(chatContent) {
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
<body>
	<div>
		<h4>채팅방코드: <%= chatCode %></h4>
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