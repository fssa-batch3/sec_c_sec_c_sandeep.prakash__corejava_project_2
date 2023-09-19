package com.fssa.bookandplay.model;

import com.google.protobuf.Timestamp;

public class FriendRequest {

	public FriendRequest() {
		// TODO Auto-generated constructor stub
	}


	

	@Override
	public String toString() {
		return "FriendRequest [requestId=" + requestId + ", senderId=" + senderId + ", receiverId=" + receiverId
				+ ", status=" + status + ", sentAt=" + sentAt + ", sender=" + sender + "]";
	}




	private int requestId;
	private int senderId;
	private int receiverId;
	private String status;
	private Timestamp sentAt;
	private User sender;
	



	public FriendRequest( int senderId, int receiverId, String status) {
		super();
	
		this.senderId = senderId;
		this.receiverId = receiverId;
		this.status = status;
	
	}


	
	public User getSender() {
		return sender;
	}


	public void setSender(User sender) {
		this.sender = sender;
	}
	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public int getSenderId() {
		return senderId;
	}

	public void setSenderId(int senderId) {
		this.senderId = senderId;
	}

	public int getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(int receiverId) {
		this.receiverId = receiverId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getSentAt() {
		return sentAt;
	}

	public void setSentAt(Timestamp sentAt) {
		this.sentAt = sentAt;
	}

}
