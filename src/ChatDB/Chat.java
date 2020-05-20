package ChatDB;
import java.sql.Date;

public class Chat {
	private int chat_code;
	private String chat_title = null;
	private int user_no;
	private Date chat_date = null;
	
	public Chat() {}
	public Chat(int chat_code, String chat_title,int user_no,Date chat_date) {
		this.chat_code = chat_code;
		this.chat_title = chat_title;
		this.user_no = user_no;
		this.chat_date = chat_date;
	}
	public int getChat_code() {
		return chat_code;
	}
	public void setChat_code(int chat_code) {
		this.chat_code = chat_code;
	}
	public String getChat_title() {
		return chat_title;
	}
	public void setChat_title(String chat_title) {
		this.chat_title = chat_title;
	}
	public int getUser_no() {
		return user_no;
	}
	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}
	public Date getChat_date() {
		return chat_date;
	}
	public void setChat_date(Date chat_date) {
		this.chat_date = chat_date;
	}
}
