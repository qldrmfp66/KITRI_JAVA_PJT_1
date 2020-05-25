package questions;

public class Question {
	int questionNo;
	int chatCode;
	String userId;
	String questionContent;
	String qCreateTime;
	int likeCount;
	
	public Question(int questionNo, int chatCode, String userId,
			String questionContent, String qCreateTime, int likeCount) {
		
		setQuestionNo(questionNo);
		setChatCode(chatCode);
		setUserId(userId);
		setQuestionContent(questionContent);
		setqCreateTime(qCreateTime);
		setLikeCount(likeCount);
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}
	
	
}
