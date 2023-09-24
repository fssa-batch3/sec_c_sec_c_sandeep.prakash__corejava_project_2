package com.fssa.bookandplay.model;

import java.util.List;	

public class ChatByPlayer {

    private int id;
    private int senderId;
    private int receiverId;
    private List<Message> sentMessages;
    private List<Message> receivedMessages;
	public List<Message> getSentMessages() {
		return sentMessages;
	}
	public void setSentMessages(List<Message> sentMessages) {
		this.sentMessages = sentMessages;
	}
	public List<Message> getReceivedMessages() {
		return receivedMessages;
	}
	public void setReceivedMessages(List<Message> receivedMessages) {
		this.receivedMessages = receivedMessages;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	@Override
	public String toString() {
		return "ChatByPlayer [id=" + id + ", senderId=" + senderId + ", receiverId=" + receiverId + ", sentMessages="
				+ sentMessages + ", receivedMessages=" + receivedMessages + "]";
	}




}
