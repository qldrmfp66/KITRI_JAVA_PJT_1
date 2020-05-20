package questions;

public class Question {
	int questionNo;
	int chatCode;
	int userNo;
	String questionContent;
	String qCreateTime;
	
	public Question(int questionNo, int chatCode, int userNo,
			String questionContent, String qCreateTime) {
		
		setQuestionNo(questionNo);
		setChatCode(chatCode);
		setUserNo(userNo);
		setQuestionContent(questionContent);
		setqCreateTime(qCreateTime);
	}
	
	public int getQuestionNo() {
		return questionNo;
	}
	public void setQuestionNo(int questionNo) {
		this.questionNo = questionNo;
	}
	public int getChatCode() {
		return chatCode;
	}
	public void setChatCode(int chatCode) {
		this.chatCode = chatCode;
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public String getQuestionContent() {
		return questionContent;
	}
	public void setQuestionContent(String questionContent) {
		this.questionContent = questionContent;
	}
	public String getqCreateTime() {
		return qCreateTime;
	}
	public void setqCreateTime(String qCreateTime) {
		this.qCreateTime = qCreateTime;
	}
	
}
