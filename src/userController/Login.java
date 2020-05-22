package userController;
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
import UserDB.UserDAO;

@WebServlet("/login")
public class Login extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setAttribute("loginResult", 1);
		request
		.getRequestDispatcher("/WEB-INF/view/login.jsp")
		.forward(request, response);
	}//doGet
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user_id = request.getParameter("userid");
		String user_password = request.getParameter("password");
		
		UserDAO userDao = UserDAO.getInstance();
		User user = null;
		int loginResult = -1;
		HttpSession session = request.getSession(true);
		try {
			loginResult = userDao.loginCheck(user_id,user_password);
			if(loginResult == 1) { //로그인 성공
				user = userDao.getUser(user_id);
				session.setAttribute("user",user); //세션값
				response.sendRedirect("/index");
//				request
//				.getRequestDispatcher("/index")
//				.forward(request, response);
			}else{ //로그인 실패 
				request
				.getRequestDispatcher("/WEB-INF/view/login.jsp")
				.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			request.setAttribute("loginResult", loginResult);
			//System.out.println(loginResult);
		}
	}//doPost
}
