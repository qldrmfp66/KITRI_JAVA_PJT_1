package questions;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/questionSubmit")
public class QuestionSubmitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = URLDecoder.decode(request.getParameter("userId"), "UTF-8");
		String chatCode = URLDecoder.decode(request.getParameter("chatCode"), "UTF-8");
		String questionContent = URLDecoder.decode(request.getParameter("questionContent"), "UTF-8");
		 
		if(userId == null || userId.equals("") || chatCode == null || chatCode.equals("") ||questionContent == null || questionContent.equals("")) {
			response.getWriter().write("0");
		} else {
			response.getWriter().write(new QuestionDAO().submit(Integer.parseInt(chatCode), userId , questionContent)+ "");
		}
		
	}

}
