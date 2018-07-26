package bean;

import java.util.Date;

public class CommentBean {
	private int BOARD_NUM;
	private String MEMBER_ID;
	private int COMMENT_NUM;
	private String COMMENT_CON;
	private Date COMMENT_DATE;
	private int COMMENT_BLIND;
	
	public int getBOARD_NUM() {
		return BOARD_NUM;
	}
	public void setBOARD_NUM(int bOARD_NUM) {
		BOARD_NUM = bOARD_NUM;
	}
	public String getMEMBER_ID() {
		return MEMBER_ID;
	}
	public void setMEMBER_ID(String mEMBER_ID) {
		MEMBER_ID = mEMBER_ID;
	}
	public int getCOMMENT_NUM() {
		return COMMENT_NUM;
	}
	public void setCOMMENT_NUM(int cOMMENT_NUM) {
		COMMENT_NUM = cOMMENT_NUM;
	}
	public String getCOMMENT_CON() {
		return COMMENT_CON;
	}
	public void setCOMMENT_CON(String cOMMENT_CON) {
		COMMENT_CON = cOMMENT_CON;
	}
	public Date getCOMMENT_DATE() {
		return COMMENT_DATE;
	}
	public void setCOMMENT_DATE(Date cOMMENT_DATE) {
		COMMENT_DATE = cOMMENT_DATE;
	}
	public int getCOMMENT_BLIND() {
		return COMMENT_BLIND;
	}
	public void setCOMMENT_BLIND(int cOMMENT_BLIND) {
		COMMENT_BLIND = cOMMENT_BLIND;
	}
	
	
	
	
}
