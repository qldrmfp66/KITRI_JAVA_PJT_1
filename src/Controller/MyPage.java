package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ChatDB.Chat;
import ChatDB.ChatDAO;
//마이페이지 내용 뿌려주는코드
@WebServlet("/mypage")
public class MyPage extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		ChatDAO chatDao = ChatDAO.getInstance();
		List<Chat> chats = new ArrayList<Chat>();
		try {
			chats = chatDao.getAllChat();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("chats", chats);
		/*
		 * response.setCharacterEncoding("UTF-8");
		 * response.setContentType("text/html; charset=UTF-8"); PrintWriter out =
		 * response.getWriter(); for(Chat chat:chats) { System.out.printf("%d ,",
		 * chat.getChat_code()); System.out.printf("%s ,", chat.getChat_title());
		 * System.out.printf("%d ,", chat.getUser_no()); }
		 */
		
		
		//forward-mypage.jsp에서 사용함
		request
		.getRequestDispatcher("/WEB-INF/view/mypage.jsp")
		.forward(request, response);
	}//doGet
}
