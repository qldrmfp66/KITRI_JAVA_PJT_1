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
	private static final String URL = "jdbc:oracle:thin:@192.168.0.21:1521:xe";
	private static final String ID = "c##ora_user";
	private static final String PW = "12345";

	private static ChatDAO instance = null;//singleton pattern
	private List<Integer> codeList = new ArrayList<Integer>(); //여태껏 저장했던 code
	
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
	
	//채팅방만들기
	public int insertChat(Chat chat) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String query = "insert into chat(chat_code, chat_title, user_no, chat_date) values(?,?,?,sysdate)";
		int randCode = 1000; 
		do {
			randCode = (int)((Math.random() * 9000) + 1000); //1000~9999
			try {
				getExistedCode(); //이미 만들어둔 코드리스트 뽑아오기
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("생성된 randCode:" + randCode);
		}while(codeList != null && codeList.contains(randCode)); //중복검사
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, randCode);
			pstmt.setString(2, chat.getChat_title());
			pstmt.setInt(3, chat.getUser_no());
			if(pstmt.executeUpdate()==1) {
				System.out.println("[" + chat.getChat_title() + "]" + "방이 생성되었습니다.");
				conn.commit();
			}else {
				System.out.println("ä���ڵ� ���� ����");
			}	
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			this.close(conn, pstmt, null);
		}
		return randCode;
	}
	
	//채팅방삭제하기
		public int deleteChat(String chat_code) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			String query = "delete from chat where chat_code = ?";
			int result = 0;
			try {
				conn = getConnection();
				conn.setAutoCommit(false); //자동커밋 false
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, chat_code);
				if(pstmt.executeUpdate()==1) {
					System.out.println("[" + chat_code + "]" + "방이 삭제되었습니다.");
					conn.commit();
					result = 1; 
				}else {
					result = -1;
					System.out.println("실패했습니다");
				}	
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				this.close(conn, pstmt, null);
			}
			return result;
		}
	//코드 중복피하려고 있던 코드 불러옴
	public void getExistedCode() throws Exception{
		//List<Integer> codeList = null;
		codeList = null; //호출시마다 비우고 다시 찾기
		Connection conn = null;
		PreparedStatement pstmt = null;
		String query = "select chat_code from chat";
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				codeList.add(rs.getInt("chat_code"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			this.close(conn, pstmt, rs);
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
	public List<Chat> getMyChat(int user_no) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		List<Chat> chats = new ArrayList<Chat>();
		String query = "select * from chat where user_no = ?";
		ResultSet rs = null;
		Chat chat = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, user_no);
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
	
