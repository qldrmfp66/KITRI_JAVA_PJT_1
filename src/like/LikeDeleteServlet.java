package like;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import questions.QuestionDAO;


@WebServlet("/likeDeleteAction")
public class LikeDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		String userId = URLDecoder.decode(request.getParameter("userId"), "UTF-8");
		String questionNo = request.getParameter("questionNo");
		
		if(userId == null || userId.equals("") || questionNo == null || questionNo.equals("")) {
			response.getWriter().write("0");
		} else {
			QuestionDAO questionDAO = new QuestionDAO();
			LikeDAO likeDAO = new LikeDAO();
			
			//test
			System.out.println("좋아요 취소");
			System.out.println(userId);
			System.out.println(questionNo);
			
			response.getWriter().write(likeDAO.likeDelete(userId, questionNo)+"");
//			response.getWriter().write(questionDAO.likeUpdateDelete(questionNo)+"");
			
			System.out.println(questionDAO.likeUpdateDelete(questionNo));
			
			//test
			System.out.println("좋아요 db 작업 후.....");
//			System.out.println(questionDAO.getLike(questionNo));
		}
	
	}

}
