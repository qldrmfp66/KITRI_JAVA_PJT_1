<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <title>채팅방</title>
    <meta charset="UTF-8">
    <title>채팅방이름</title>
</head>

<body>
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