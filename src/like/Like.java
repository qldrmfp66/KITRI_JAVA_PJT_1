package like;

public class Like {
	
	int LikeNo;
	int UserNo;
	int QuestionNo;
	
	public Like(int likeNo, int userNo, int questionNo) {
		super();
		LikeNo = likeNo;
		UserNo = userNo;
		QuestionNo = questionNo;
	}
	
	public Like() {
	}
	
	public int getLikeNo() {
		return LikeNo;
	}
	public void setLikeNo(int likeNo) {
		LikeNo = likeNo;
	}
	public int getUserNo() {
		return UserNo;
	}
	public void setUserNo(int userNo) {
		UserNo = userNo;
	}
	public int getQuestionNo() {
		return QuestionNo;
	}
	public void setQuestionNo(int questionNo) {
		QuestionNo = questionNo;
	}
}
