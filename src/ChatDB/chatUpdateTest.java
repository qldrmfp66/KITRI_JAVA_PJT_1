package ChatDB;

public class chatUpdateTest {
	public static void main(String[] args){
		ChatDAO chatDao = ChatDAO.getInstance();
		Chat chat = new Chat();
		chat.setChat_code(2256);
		chat.setChat_title("æ∆¿Ã∞Ì");
		chat.setUser_no(34557);
		chatDao.insertChat(chat);
	}
}
