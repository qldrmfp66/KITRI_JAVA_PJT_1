package like;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LikeDAO {
	
	private Connection conn;
	
	public LikeDAO() {
		try {
			String URL = "jdbc:oracle:thin:@192.168.0.21:1521:xe";
			String ID = "c##ora_user";
			String PW = "12345";
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(URL, ID, PW);
		} catch (Exception e ) {
			e.printStackTrace();
		}
	}
	
	// 좋아요 Insert
	public int likeInsert(String userId, String questionNo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "INSERT INTO Q_LIKE (LIKE_NO, USER_ID, QUESTION_NO) VALUES(LIKE_NO.NEXTVAL, ?, ?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
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
	public int likeDelete(String userId, String questionNo) {
	
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "DELETE FROM Q_LIKE WHERE USER_ID=? AND QUESTION_NO =? ";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
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
	public int getQuestionNo(String userId) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		String sql = "SELECT QUESTION_NO FROM Q_LIKE WHERE USER_ID = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
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
