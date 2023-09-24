package com.fssa.bookandplay.model;

public class Message {

	 private int id;
	    private String text;
	    private String sender;
	    private long timestamp;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getText() {
			return text;
		}
		public void setText(String text) {
			this.text = text;
		}
		public String getSender() {
			return sender;
		}
		public void setSender(String sender) {
			this.sender = sender;
		}
		public long getTimestamp() {
			return timestamp;
		}
		public void setTimestamp(long timestamp) {
			this.timestamp = timestamp;
		}
		@Override
		public String toString() {
			return "Message [id=" + id + ", text=" + text + ", sender=" + sender + ", timestamp=" + timestamp + "]";
		}
}
