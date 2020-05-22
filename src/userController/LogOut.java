package userController;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import UserDB.User;

@WebServlet("/logout")
public class LogOut extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		  HttpSession session = request.getSession(true); //있으면 반환 없으면 null
		  System.out.println("session : "+session.getAttribute("user"));
		  System.out.println("로그아웃");
		  if(session!= null && session.getAttribute("user") != null) {
			  		session.invalidate();
			  		request.setAttribute("logout", "success"); //로그아웃성공
		  }
		request
		.getRequestDispatcher("/WEB-INF/view/index.jsp")
		.forward(request, response);
	}
	
}
