package chatController;

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

@WebServlet("/deletechat")
public class DeleteChat extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String chat_code = request.getParameter("chat_code");
		ChatDAO chatDao = ChatDAO.getInstance();
		int result = 0;
		try {
			result = chatDao.deleteChat(chat_code); // Ŭ���� ��ȣ �޾ƿ���
			System.out.println("delete.java" + result);
			
			  if(result ==1) { 
				  System.out.println("삭제성공"); 
			  }else {
			  System.out.println("삭제실패"); 
			  }
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect("mypage");
	}// doGet

}
