package chatController;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ChatDB.Chat;
import ChatDB.ChatDAO;
import UserDB.User;
//���������� ���� �ѷ��ִ��ڵ�
@WebServlet("/mypage")
public class MyPage extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		ChatDAO chatDao = ChatDAO.getInstance();
		List<Chat> chats = new ArrayList<Chat>();
		HttpSession session = request.getSession(true); //있으면 반환 없으면 null
		int findUserNum = 0;
		if(session!= null && session.getAttribute("user") != null) {
			  User user = (User)(session.getAttribute("user"));
			  findUserNum = user.getUser_no();
			  request.setAttribute("user_name",user.getUser_name());
			  System.out.println("session : "+session.getAttribute("user") + "의 채팅방불러옵니다");
			  try {
					chats = chatDao.getMyChat(findUserNum);
			  } catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			  }
			  request.setAttribute("chats", chats);
		}
		request
		.getRequestDispatcher("/WEB-INF/view/mypage.jsp")
		.forward(request, response);
	}//doGet
}
