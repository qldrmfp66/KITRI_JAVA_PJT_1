package userController;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import UserDB.User;
import UserDB.UserDAO;

@WebServlet("/signup")
public class SignUp extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setAttribute("joinResult", 1);
		request
		.getRequestDispatcher("/WEB-INF/view/signup.jsp")
		.forward(request, response);
	}//doGet
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user_name = request.getParameter("username");
		String user_id = request.getParameter("userid");
		String user_password = request.getParameter("userpw");
		
		UserDAO userDao = UserDAO.getInstance();
		User user = null;
		int JoinResult = -1;
		HttpSession session = request.getSession(true);
		try {
			JoinResult = userDao.insertUser(user_name, user_id,user_password);
			if(JoinResult == 1) { //회원가입 성공 -> 바로 세션저장(로그인상태로이용)
				user = userDao.getUser(user_id); //회원 유저 불러오기
				session.setAttribute("user",user); //세션값저장
				response.sendRedirect("index");
				/*
				 * request .getRequestDispatcher("/WEB-INF/view/index.jsp") .forward(request,
				 * response);
				 */
			}else{ //회원가입실패
				response.sendRedirect("signup");
				/*
				 * request .getRequestDispatcher("/WEB-INF/view/signup.jsp") .forward(request,
				 * response);
				 */
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			request.setAttribute("joinResult", JoinResult);
			System.out.println(JoinResult);
		}
	}//doPost
}
