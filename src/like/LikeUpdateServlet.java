package like;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import questions.QuestionDAO;

@WebServlet("/likeUpdateServlet")
public class LikeUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = URLDecoder.decode(request.getParameter("userId"), "UTF-8");
		String questionNo = request.getParameter("questionNo");
		
		if(userId == null || userId.equals("") || questionNo == null || questionNo.equals("")) {
			response.getWriter().write("");
		} else {
			try {
				response.getWriter().write(getLikeCount(questionNo));
			} catch (Exception e) {
				response.getWriter().write("");
			}
		}
	}
	
	public String getLikeCount(String questionNo) {
		StringBuffer result = new StringBuffer("");
		result.append("{\"result\":[");
		
		String likeCount = new QuestionDAO().getLike(questionNo);
		
		
		result.append("[{\"value\": \"" + likeCount+ "\"}]");
		
		// json error 방지용
		result.append("], \"last\":\"" + "1" +"\"}");

		return result.toString();
	}

}
