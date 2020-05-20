package questions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class QuestionDAO {
	
	private Connection conn;
	
	public QuestionDAO() {
		try {
			String URL = "jdbc:oracle:thin:@localhost:1521:xe";
			String ID = "c##ora_user";
			String PW = "12345";
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(URL, ID, PW);
		} catch (Exception e ) {
			e.printStackTrace();
		}
	}
	
	//question content 불러오기 (시간 순으로)
	ArrayList<Question> getQuestionListByRecent(String qNo) {
		ArrayList<Question> questionList = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		
		String sql = "SELECT * FROM QUESTIONS WHERE QUESTION_NO > ? ORDER BY Q_CREATE_TIME";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(qNo));
			rs = pstmt.executeQuery();
			
			questionList = new ArrayList<Question>();
			while(rs.next()) {
				int questionNo = rs.getInt("QUESTION_NO");
				int chatCode = rs.getInt("CHAT_CODE");
				int userNo = rs.getInt("USER_NO");
				// 특수 기호 처리
				String questionContent = rs.getString("QUESTION_CONTENT").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>");
				String qCreateTime = rs.getString("Q_CREATE_TIME");
				
				Question question = new Question(questionNo,chatCode, userNo, questionContent, qCreateTime);
				questionList.add(question);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if(rs != null) rs.close();
					if(pstmt != null) pstmt.close();
					if(conn != null) conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		
		return questionList;
	}
	
	ArrayList<Question> getQuestionListByRecent(int number) {
		ArrayList<Question> questionList = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		
		String sql = "SELECT * FROM QUESTIONS WHERE QUESTION_NO > (SELECT MAX(QUESTION_NO) - ? FROM QUESTIONS) ORDER BY Q_CREATE_TIME";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, number);
			rs = pstmt.executeQuery();
			
			questionList = new ArrayList<Question>();
			while(rs.next()) {
				int questionNo = rs.getInt("QUESTION_NO");
				int chatCode = rs.getInt("CHAT_CODE");
				int userNo = rs.getInt("USER_NO");
				// 특수 기호 처리
				String questionContent = rs.getString("QUESTION_CONTENT").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>");
				String qCreateTime = rs.getString("Q_CREATE_TIME");
				
				Question question = new Question(questionNo,chatCode, userNo, questionContent, qCreateTime);
				questionList.add(question);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if(rs != null) rs.close();
					if(pstmt != null) pstmt.close();
					if(conn != null) conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		
		return questionList;
	}
	
	//[++추가해야할 기능] question 전체 리스트 불러오기 (좋아 순으로) 
	
	
	public int submit(int chatCode, int userNo, String questionContent) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "INSERT INTO QUESTIONS( QUESTION_NO, Q_CREATE_TIME, CHAT_CODE, USER_NO, QUESTION_CONTENT)"
				+ "VALUES(NO_SEQ.NEXTVAL, SYSDATE, ?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, chatCode);
			pstmt.setInt(2, userNo);
			pstmt.setString(3, questionContent);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return -1;

	}

}
