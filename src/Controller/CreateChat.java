package Controller;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ChatDB.Chat;
import ChatDB.ChatDAO;

@WebServlet("/createchat")
public class CreateChat extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request
		.getRequestDispatcher("/WEB-INF/view/createchat.jsp")
		.forward(request, response);
	}//doGet

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String event_name = request.getParameter("eventname");
		String host = request.getParameter("host");
		String event_code = request.getParameter("eventcode");
		
		
		/*
		 * response.setCharacterEncoding("UTF-8");
		 * response.setContentType("text/html; charset=UTF-8"); PrintWriter out =
		 * response.getWriter(); out.printf("%s %s<br>", event_name,
		 * event_name.getClass().getName()); out.printf("%s %s<br>",
		 * host,host.getClass().getName()); out.printf("%s %s<br>",
		 * event_code,event_code.getClass().getName());
		 */
		 
		  Chat chat = new Chat(); chat.setChat_title(event_name);
		  chat.setChat_code(Integer.parseInt(event_code)); 
		  chat.setUser_no(Integer.parseInt(host));
		  ChatDAO chatDao = ChatDAO.getInstance(); 
		  chatDao.insertChat(chat); //DB»ðÀÔ
		  response.sendRedirect("mypage");
	}//doPost
}
