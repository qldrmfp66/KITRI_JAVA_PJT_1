package Main;
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

@WebServlet("/index")
public class Index extends HttpServlet {
	int EnterSuccessResult = 1; //채팅방 입장 성공?
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession(true); //있으면 반환 없으면 null
		   
		/*     메인에서 유저의 상태 불러옴       */
		  if(session!= null && session.getAttribute("user") != null) {
			  User user = (User)(session.getAttribute("user"));
			  request.setAttribute("user_id",user.getUser_id());
		  }
		  request.setAttribute("EnterResult", EnterSuccessResult);
		  EnterSuccessResult = 1; //초기화
		 request
		.getRequestDispatcher("/WEB-INF/view/index.jsp")
		.forward(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String first_code_ = request.getParameter("first_code");
		String second_code_ = request.getParameter("second_code");
		String third_code_ = request.getParameter("third_code");
		String fourth_code_ = request.getParameter("fourth_code");
		
		String full_code = first_code_ + second_code_ + third_code_ + fourth_code_;
		if(first_code_.equals("") || second_code_.equals("") || third_code_.equals("") || fourth_code_.equals("")) {
			EnterSuccessResult = 0; //하나라도 입력안하면
		}
		
		ChatDAO chatDao = ChatDAO.getInstance();
		Chat chat = new Chat();
		try {
			chat = chatDao.getChat(full_code);
			if(chat.getChat_title() == null) { //코드가 틀려서 해당채팅방이 없는경우
				EnterSuccessResult = -1; 
			}
			request.setAttribute("chat",chat);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		/*     채팅에서 유저의 상태 불러옴       */
		HttpSession session = request.getSession(true); //있으면 반환 없으면 null
		if(session!= null && session.getAttribute("user") != null) {
			User user = (User)(session.getAttribute("user"));
			request.setAttribute("user_name",user.getUser_name());
			//System.out.println("채팅방입장하는 user_name: " + user.getUser_name());
			
		}else if(session == null || session.getAttribute("user") == null) {
			String ip = getClientIP(request);
			request.setAttribute("anonymous_name", ip);
			//System.out.println("채팅방입장하는 비로그인자 ip: " + ip);
			//로그인인지 비로그인인지 받는 곳			
		}
		request.setAttribute("EnterResult",EnterSuccessResult);
		if(EnterSuccessResult == -1 || EnterSuccessResult ==0) {
			response.sendRedirect("index");
		}else {
		  request .getRequestDispatcher("/WEB-INF/view/question.jsp") 
		  .forward(request, response);
		}
	}//doPost
	
	/*아이피 주소 받아오기*/
	public static String getClientIP(HttpServletRequest request) {
	    String ip = request.getHeader("X-Forwarded-For");
	    
	    if (ip == null || ip.length() ==0) {
	        ip = request.getHeader("Proxy-Client-IP");
	    }
	    if (ip == null || ip.length() ==0) {
	        ip = request.getHeader("WL-Proxy-Client-IP");
	    }
	    if (ip == null || ip.length() ==0) {
	        ip = request.getHeader("HTTP_CLIENT_IP");
	    }
	    if (ip == null || ip.length() ==0) {
	        ip = request.getHeader("HTTP_X_FORWARDED_FOR");
	    }
	    if (ip == null || ip.length() ==0) {
	        ip = request.getRemoteAddr();
	    }
	    System.out.println("ip : " + ip);
	    /*아이피가 0:0:0:0:0:0:0:1 이라는건 ipv6의 주소를 가져온것으로 ipv4로 봤을때 127.0.0.1 이 맞다.
			localhost로 서버에 접속했을 때 생기는 현상이며, 10.10.10.1 과 같은 아이피로 접근했을때는 정상적으로 가져온다.
	     */
	    return ip;
	}
}


