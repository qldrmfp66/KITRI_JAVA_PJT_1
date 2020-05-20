package ChatDB;
import java.util.List;

public class chatSelectAllTest {
	public static void main(String[] args) throws Exception{
		ChatDAO chatDao = ChatDAO.getInstance();
		List<Chat> chats = chatDao.getAllChat();
		for(Chat chat : chats) {
			System.out.println("chat : " + chat);
		}
	}
}
