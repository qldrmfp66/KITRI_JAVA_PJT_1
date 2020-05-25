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
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String userNo = URLDecoder.decode(request.getParameter("userNo"), "UTF-8");
		String questionNo = request.getParameter("questionNo");

		if(userNo == null || userNo.equals("") || questionNo == null || questionNo.equals("")) {
			response.getWriter().write("0");
			
		} else if(new LikeDAO().getQuestionNo(userNo) == 0){
			response.getWriter().write(new LikeDAO().likeInsert(userNo, questionNo)+"");
			response.getWriter().write(new QuestionDAO().likeUpdate(questionNo)+"");
			
			//return 11
			
		} else {
			// 값이 있으면 삭제
			response.getWriter().write(new LikeDAO().likeDelete(userNo, questionNo)+"");
			response.getWriter().write(new QuestionDAO().likeUpdateDelete(questionNo)+"1");
			// return 111
			

			}	 
			 
		}
	
}
