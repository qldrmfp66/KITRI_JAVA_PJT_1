package ChatDB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//�̱���
public class ChatDAO {
	private static final String Driver = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@192.168.219.104:1521:xe";
	private static final String ID = "c##ora_user";
	private static final String PW = "12345";

	private static ChatDAO instance = null;//singleton pattern
	
	private ChatDAO() {
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
	
	public static ChatDAO getInstance() {
		if(instance==null) {
		instance = new ChatDAO();}
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
	
	//ä�û���
	public void insertChat(Chat chat) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String query = "insert into chat(chat_code, chat_title, user_no, chat_date) values(?,?,?,sysdate)";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, chat.getChat_code());
			pstmt.setString(2, chat.getChat_title());
			pstmt.setInt(3, chat.getUser_no());
			if(pstmt.executeUpdate()==1) {
				System.out.println("[" + chat.getUser_no() + "]" + "���������� ä���� �����߽��ϴ�");
				conn.commit();
			}else {
				System.out.println("ä���ڵ� ���� ����");
			}	
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			this.close(conn, pstmt, null);
		}
	}
	
	//�ش��ϴ� ���� �������ֱ� ����(�˻���)
	public Chat getChat(String chat_code) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String query = "select * from chat where chat_code = ?";
		ResultSet rs = null;
		Chat chat = new Chat();
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, chat_code);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				chat.setChat_code(rs.getInt("chat_code"));
				chat.setChat_title(rs.getString("chat_title"));
				chat.setUser_no(rs.getInt("user_no"));
				chat.setChat_date(rs.getDate("chat_date"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			this.close(conn, pstmt, rs);
		}
		return chat;
	}
	
	//��ü��ȸ
	public List<Chat> getAllChat() throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		List<Chat> chats = new ArrayList<Chat>();
		String query = "select * from chat";
		ResultSet rs = null;
		Chat chat = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				chat = new Chat();
				chat.setChat_code(rs.getInt("chat_code"));
				chat.setChat_title(rs.getString("chat_title"));
				chat.setUser_no(rs.getInt("user_no"));
				chat.setChat_date(rs.getDate("chat_date"));
				chats.add(chat);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			this.close(conn, pstmt, rs);
		}
		return chats;
	}
}
	
