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
				int likeCount = rs.getInt("LIKE_COUNT");
				
				Question question = new Question(questionNo,chatCode, userNo, questionContent, qCreateTime, likeCount);
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
	
	// 초기 출력(최신순)
	ArrayList<Question> getQuestionListByRecent(int number) {
		ArrayList<Question> questionList = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		
		String sql = "SELECT * FROM QUESTIONS WHERE QUESTION_NO > (SELECT MAX(QUESTION_NO) - ? FROM QUESTIONS) ORDER BY LIKE_COUNT DESC, Q_CREATE_TIME";
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
				int likeCount = rs.getInt("LIKE_COUNT");

				
				Question question = new Question(questionNo,chatCode, userNo, questionContent, qCreateTime, likeCount);
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
		
	
	// 좋아요 update
	public int likeUpdate(String qNo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		String sql = "UPDATE QUESTIONS SET LIKE_COUNT = LIKE_COUNT +1 WHERE QUESTION_NO = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(qNo));
			
			return pstmt.executeUpdate();
			
				
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
		
		return -1;
	}
	
	// 좋아요 취소 
	public int likeUpdateDelete(String qNo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		String sql = "UPDATE QUESTIONS SET LIKE_COUNT = LIKE_COUNT -1 WHERE QUESTION_NO = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(qNo));			
			return pstmt.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if(rs != null) rs.close();
					if(pstmt != null) pstmt.close();
					//if(conn != null) conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		
		return -1;
	}
	
	// like count 불러오기
	public String getLike(String qNo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		String sql = "SELECT LIKE_COUNT FROM QUESTIONS WHERE QUESTION_NO = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(qNo));
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getString(1);
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
		return null;
	}

	
	//질문 create
	public int submit(int chatCode, int userNo, String questionContent) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "INSERT INTO QUESTIONS( QUESTION_NO, Q_CREATE_TIME, CHAT_CODE, USER_NO, QUESTION_CONTENT, LIKE_COUNT)"
				+ "VALUES(NO_SEQ.NEXTVAL, SYSDATE, ?, ?, ?, 0)";
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
