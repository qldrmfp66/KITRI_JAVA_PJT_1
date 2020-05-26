package questions;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ChatDB.Chat;
import ChatDB.ChatDAO;
import UserDB.User;

@WebServlet(urlPatterns = {"/question_main"})
public class Question_Main_Controller extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		/*     채팅코드에 맞게 채팅 불러옴         */
		String chat_code = request.getParameter("chat_code"); //mypage.jsp에서 넘어올때
		ChatDAO chatDao = ChatDAO.getInstance();
		Chat chat = null;
		System.out.println("question_main의 get메소드실행");
		try {
			chat = chatDao.getChat(chat_code); //Ŭ���� ��ȣ �޾ƿ���
			request.setAttribute("chat",chat);//mypage.jsp에서 선택한 chat값
		} catch (Exception e) {
			e.printStackTrace();
		}
		HttpSession session = request.getSession(true); //있으면 반환 없으면 null
		if(session!= null && session.getAttribute("user") != null) {
			 User user = (User)(session.getAttribute("user"));
			 request.setAttribute("user_name",user.getUser_name());
		}
		request
		.getRequestDispatcher("/WEB-INF/view/question_main.jsp")
		.forward(request, response);
	}
}
