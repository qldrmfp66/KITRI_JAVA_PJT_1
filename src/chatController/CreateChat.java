package chatController;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ChatDB.Chat;
import ChatDB.ChatDAO;
import UserDB.User;

@WebServlet("/createchat")
public class CreateChat extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println("createchat의 get메소드호출");
		HttpSession session = request.getSession(true);
		
		if(session!= null && session.getAttribute("user") != null) {
			  User user = (User)(session.getAttribute("user"));
			  String user_name = user.getUser_name();
			  request.setAttribute("user_name", user_name);
		}
		
		request
		.getRequestDispatcher("/WEB-INF/view/createchat.jsp")
		.forward(request, response);
	}//doGet

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String event_name = request.getParameter("eventname");
		//String event_code = request.getParameter("eventcode");
		
		HttpSession session = request.getSession(true); //있으면 반환 없으면 null
		int sessionUserNum = 0;
		int randCode = 1000;
		if(session!= null && session.getAttribute("user") != null) {
			  User user = (User)(session.getAttribute("user"));
			  sessionUserNum = user.getUser_no();
			  System.out.println("CreateChat Post메소드 - 현재 세션 : "+ session.getAttribute("user") + "의 새로운 채팅 생성중");
			  try {
				  	Chat chat = new Chat();
				  	chat.setChat_title(event_name);
				  	chat.setUser_no(sessionUserNum); //현재세션의 유저넘버 저장
				  	//채팅번호는 자동생성
					ChatDAO chatDao = ChatDAO.getInstance(); 
					randCode = chatDao.insertChat(chat); //DB삽입성공하면 만든 randCode반환
					request.setAttribute("randCode", randCode);
					request.setAttribute("user_id", user.getUser_id());
					chat.setChat_code(randCode); 
					Chat temp = chatDao.getChat(Integer.toString(randCode));
					chat.setChat_date(temp.getChat_date());
					
					request.setAttribute("user_name", user.getUser_name());
					request.setAttribute("chat",chat);
					request.getRequestDispatcher("/WEB-INF/view/chatting.jsp") 
					  	   .forward(request, response);
					//response.sendRedirect("mypage");
			  } catch (Exception e) {
					e.printStackTrace();
			  }
		}//if 세션존재
		
	}//doPost
}
