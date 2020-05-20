package Controller;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ChatDB.Chat;
import ChatDB.ChatDAO;

@WebServlet(urlPatterns = {"/mypage/chatting"})
public class Chatting extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String chat_code_index = request.getParameter("chat_code"); //해당 chat_code
		ChatDAO chatDao = ChatDAO.getInstance();
		Chat chat = null;
		try {
			chat = chatDao.getChat(chat_code_index); //클릭한 번호 받아오기
		} catch (Exception e) {
			e.printStackTrace();
		}
	    request.setAttribute("chat",chat);
		System.out.println("chat : " + chat);
		
	
		//forward
		request
		.getRequestDispatcher("/WEB-INF/view/chatting.jsp")
		.forward(request, response);
	}//doGet
}
