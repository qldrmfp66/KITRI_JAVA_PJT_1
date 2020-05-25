package like;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LikeDAO {
	
	private Connection conn;
	
	public LikeDAO() {
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
	
	// 좋아요 Insert
	public int likeInsert(String userNo, String questionNo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "INSERT INTO Q_LIKE (LIKE_NO, USER_NO, QUESTION_NO) VALUES(LIKE_NO.NEXTVAL, ?, ?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(userNo));
			pstmt.setInt(2, Integer.parseInt(questionNo));
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
	
	// 좋아요 delete
	public int likeDelete(String userNo, String questionNo) {
	
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "DELETE FROM Q_LIKE WHERE USER_NO=? AND QUESTION_NO =? ";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(userNo));
			pstmt.setInt(2, Integer.parseInt(questionNo));
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
	
	// 무결성 확인용 getQuestionNo()
	public int getQuestionNo(String userNo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		String sql = "SELECT QUESTION_NO FROM Q_LIKE WHERE USER_NO = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(userNo));
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
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
		return 0;
	}
	

}
