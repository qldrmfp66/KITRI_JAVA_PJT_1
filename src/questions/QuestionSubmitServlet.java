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
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String userNo = URLDecoder.decode(request.getParameter("userNo"), "UTF-8");
		String chatCode = URLDecoder.decode(request.getParameter("chatCode"), "UTF-8");
		String questionContent = URLDecoder.decode(request.getParameter("questionContent"), "UTF-8");
		if(userNo == null || userNo.equals("") || chatCode == null || chatCode.equals("") ||questionContent == null || questionContent.equals("")) {
			response.getWriter().write("0");
		} else {
			response.getWriter().write(new QuestionDAO().submit(Integer.parseInt(chatCode), Integer.parseInt(userNo), questionContent)+ "");
		}
		
	}

}
