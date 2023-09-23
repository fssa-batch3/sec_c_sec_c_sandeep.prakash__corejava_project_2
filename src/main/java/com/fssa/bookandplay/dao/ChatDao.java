package com.fssa.bookandplay.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fssa.bookandplay.exceptions.DAOException;
import com.fssa.bookandplay.model.ChatByPlayer;
import com.fssa.bookandplay.model.Message;
import com.fssa.bookandplay.util.ConnectionUtil;

public class ChatDao {

	public void addChat(ChatByPlayer chat) throws DAOException, SQLException {

		/**
		 * The Query for calling insertground from sql
		 */
		/**
		 * Getting the ground details and inserting in sql
		 */
		  try (Connection con = ConnectionUtil.getConnection()) {

	            String messageInsertSQL = "INSERT INTO messages (sender_id, receiver_id, text, timestamp) VALUES (?, ?, ?, ?)";

	            try (PreparedStatement messageStatement = con.prepareStatement(messageInsertSQL)) {

	                for (Message message : chat.getMessages()) {
	                    messageStatement.setInt(1, chat.getSenderId());
	                    messageStatement.setInt(2, chat.getReceiverId());
	                    messageStatement.setString(3, message.getText());
	                    messageStatement.setLong(4, message.getTimestamp());

	                    messageStatement.executeUpdate();
	                }
	            } catch (SQLException e) {
	                e.printStackTrace(); // Handle the exception appropriately in your application
	            }
	        }
		
		

}

	
	public static void main(String[] args) throws DAOException, SQLException {
		ChatDao da=new ChatDao();
		
		
		ChatByPlayer chat = new ChatByPlayer();
		chat.setSenderId(1); // Replace with the sender's ID
		chat.setReceiverId(2); // Replace with the receiver's ID

		List<Message> messages = new ArrayList<>();
		Message message1 = new Message();
		message1.setText("Hello");
		message1.setSender("Sender");
		message1.setTimestamp(System.currentTimeMillis());

		Message message2 = new Message();
		message2.setText("Hi");
		message2.setSender("Sender");
		message2.setTimestamp(System.currentTimeMillis() + 1000); // Add some time difference

		messages.add(message1);
		messages.add(message2);

		chat.setMessages(messages);
		da.addChat(chat);
	}
}
