package UserDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ChatDB.Chat;
import ChatDB.ChatDAO;

public class UserDAO {
	private static final String Driver = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@192.168.0.21:1521:xe";
	private static final String ID = "c##ora_user";
	private static final String PW = "12345";

	private static UserDAO instance = null;//singleton pattern
	private UserDAO() {
		try {
			Class.forName(Driver);
			try {
			Connection conn = DriverManager.getConnection(URL,ID,PW);
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}	
	}
	
	public static UserDAO getInstance() {
		if(instance==null) {
		instance = new UserDAO();}
		return instance;
	}
	
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL,ID,PW);
	}
	
	public void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        try {
            if (rs != null)
                rs.close();// ��ȿ���˻� �� �ڿ� �ݳ�
            if (pstmt != null)
                pstmt.close();
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            System.out.println("DB close");
            e.printStackTrace();
        }
	}
	
	//회원가입
	public int insertUser(String user_name, String user_id, String user_pw) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String query = "insert into userdata(user_no, user_name, user_id, user_pw, user_authority) values(USER_NO.NEXTVAL,?,?,?,1)";
		int result = -1;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user_name);
			pstmt.setString(2, user_id);
			pstmt.setString(3, user_pw);
			if(pstmt.executeUpdate()==1) {
				result =1;
				System.out.println("[" + user_name + "]" + "정상적으로 생성되었습니다.");
				conn.commit();
			}else {
				System.out.println("회원가입 실패");
			}	
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			this.close(conn, pstmt, null);
		}
		return result;
	}
	
	//로그인 - 아이디 없을경우 : -1 , 패스워드불일치 : 0, 로그인성공:1
	public int loginCheck(String id, String pw) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String query = "select user_pw from userdata where user_id = ?";
		String correct_pw = "";
		ResultSet rs = null;
		int result = -1; //로그인 상태 반환 매개변수
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				correct_pw = rs.getString("user_pw");
				if(correct_pw.equals(pw)) {
					result = 1; //로그인 성공
				}else {
					result = 0; //비밀번호 불일치
				}
			}else {
				result = -1;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			this.close(conn, pstmt, rs);
		}
		return result;
	}
	//유저아이디에 해당하는 유저정보 가져오기
	public User getUser(String user_id) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String query = "select * from userdata where user_id = ?";
		ResultSet rs = null;
		User user = new User();
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				user.setUser_no(rs.getInt("user_no"));
				user.setUser_id(user_id);
				user.setUser_pw(rs.getString("user_pw"));
				user.setUser_name(rs.getString("user_name"));
				user.setUser_authority(rs.getInt("user_authority"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			this.close(conn, pstmt, rs);
		}
		return user;
	}

}
