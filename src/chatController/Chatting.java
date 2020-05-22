package chatController;
import java.io.IOException;
import java.net.InetAddress;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ChatDB.Chat;
import ChatDB.ChatDAO;
import UserDB.User;

@WebServlet(urlPatterns = {"/chatting"})
public class Chatting extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println("chatting.java get메소드 호출");
		
		
		/*     채팅코드에 맞게 채팅 불러옴         */
		String chat_code = request.getParameter("chat_code"); //mypage.jsp에서 넘어올때
		ChatDAO chatDao = ChatDAO.getInstance();
		Chat chat = null;
		
		try {
			chat = chatDao.getChat(chat_code); //Ŭ���� ��ȣ �޾ƿ���
		} catch (Exception e) {
			e.printStackTrace();
		}
	    request.setAttribute("chat",chat);//index.java에서 넘어온 request값의 chat OR mypage.jsp에서 선택한 chat값
		//System.out.println("chat : " + chat);
		
	
		//forward
		request
		.getRequestDispatcher("/WEB-INF/view/chatting.jsp")
		.forward(request, response);
	}//doGet
}
