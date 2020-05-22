package UserDB;

public class User {
	private int user_no;
	private String user_name;
	private String user_id;
	private String user_pw;
	private int user_authority;
	
	public User() {}
	public User(int user_no, String user_name, String user_id,
			String user_pw, int user_authority) {
		this.user_no = user_no;
		this.user_name = user_name;
		this.user_id = user_id;
		this.user_pw = user_pw;
		this.user_authority = user_authority;
	}
	public int getUser_no() {
		return user_no;
	}
	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_pw() {
		return user_pw;
	}
	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
	}
	public int getUser_authority() {
		return user_authority;
	}
	public void setUser_authority(int user_authority) {
		this.user_authority = user_authority;
	}
	
}
