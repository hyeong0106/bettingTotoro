package Message.model.vo;

import java.sql.Date;

public class Message {
	private int messageNo;
	private String sendId;
	private String receiveId;
	private String content;
	private Date sendTime;
	
	public Message() {}

	public Message(int messageNO, String sendId, String receiveId, String content, Date sendTime) {
		this.messageNo = messageNo;
		this.sendId = sendId;
		this.receiveId = receiveId;
		this.content = content;
		this.sendTime = sendTime;
	}

	public int getMessageNo() {
		return messageNo;
	}

	public void setMessageNo(int messageNo) {
		this.messageNo = messageNo;
	}

	public String getSendId() {
		return sendId;
	}

	public void setSendId(String sendId) {
		this.sendId = sendId;
	}

	public String getReceiveId() {
		return receiveId;
	}

	public void setReceiveId(String receiveId) {
		this.receiveId = receiveId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	@Override
	public String toString() {
		return "Message [messageNo=" + messageNo + ", sendId=" + sendId + ", receiveId=" + receiveId + ", content="
				+ content + ", sendTime=" + sendTime + "]";
	}
	
	
}
