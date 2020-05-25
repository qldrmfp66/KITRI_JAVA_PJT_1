package questions;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/questions")
public class QuestionListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String listType = request.getParameter("listType");
		
		if(listType == null || listType.equals("")) {
			response.getWriter().write("");
		} else if(listType.equals("ten") ) {
			response.getWriter().write(getTen());
		}else {
			try {
				//가장 마지막으로 받은 q_no
				Integer.parseInt(listType);
				//q_no을 받았다면 넣음.
				response.getWriter().write(getListByID(listType));
			} catch (Exception e) {
				response.getWriter().write("");
			}
		}
	}
	
	// 질문 불러옴
	public String getListByID(String questionNo) {
		StringBuffer result = new StringBuffer("");
		result.append("{\"result\":[");
		QuestionDAO questionDAO = new QuestionDAO();
		ArrayList<Question> questionList = questionDAO.getQuestionListByRecent(questionNo);
		if(questionList.size() == 0 ) return "";
		for(int i = 0; i < questionList.size(); i++) {
			result.append("[{\"value\": \"" + questionList.get(i).getQuestionContent()+ "\"},");
			result.append("{\"value\": \"" + questionList.get(i).getLikeCount()+ "\"},");
			result.append("{\"value\": \"" + questionList.get(i).getQuestionNo()+ "\"}]");
			if(i != questionList.size() -1) result.append(",");
		}
		result.append("], \"last\":\"" + questionList.get(questionList.size()-1).getQuestionNo()+"\"}");
		return result.toString();
	}
	
	// 문서 처음 불러올때 10개 질문만 불러옴
	public String getTen() {
		StringBuffer result = new StringBuffer("");
		result.append("{\"result\":[");
		QuestionDAO questionDAO = new QuestionDAO();
		ArrayList<Question> questionList = questionDAO.getQuestionListByRecent(10);
		if(questionList.size() == 0 ) return "";
		for(int i = 0; i < questionList.size(); i++) {
			result.append("[{\"value\": \"" + questionList.get(i).getQuestionContent()+ "\"},");
			result.append("{\"value\": \"" + questionList.get(i).getLikeCount()+ "\"},");
			result.append("{\"value\": \"" + questionList.get(i).getQuestionNo()+ "\"}]");
			if(i != questionList.size() -1) result.append(",");
		}
		result.append("], \"last\":\"" + questionList.get(questionList.size()-1).getQuestionNo()+"\"}");
		return result.toString();
	}

}
