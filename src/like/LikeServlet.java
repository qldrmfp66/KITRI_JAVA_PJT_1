package like;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import questions.QuestionDAO;

@WebServlet("/likeAction")
public class LikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = URLDecoder.decode(request.getParameter("userId"), "UTF-8");
		String questionNo = request.getParameter("questionNo");
		if(userId == null || userId.equals("") || questionNo == null || questionNo.equals("")) {
			response.getWriter().write("0");
			
		} else if(new LikeDAO().getQuestionNo(userId) == 0){
			response.getWriter().write(new LikeDAO().likeInsert(userId, questionNo)+"");
			response.getWriter().write(new QuestionDAO().likeUpdate(questionNo)+"");
			
			//return 11
			
		} else {
			// 값이 있으면 삭제
			response.getWriter().write(new LikeDAO().likeDelete(userId, questionNo)+"");
			response.getWriter().write(new QuestionDAO().likeUpdateDelete(questionNo)+"1");
			// return 111
			

			}	 
			 
		}
	
}
