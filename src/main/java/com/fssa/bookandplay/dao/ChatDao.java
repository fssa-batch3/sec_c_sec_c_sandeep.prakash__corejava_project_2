package com.fssa.bookandplay.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fssa.bookandplay.errors.FriendRequestDaoErrors;
import com.fssa.bookandplay.exceptions.DAOException;
import com.fssa.bookandplay.model.ChatByPlayer;
import com.fssa.bookandplay.model.Message;
import com.fssa.bookandplay.util.ConnectionUtil;

public class ChatDao {

	public boolean addChat(ChatByPlayer chat) throws DAOException, SQLException {


		  try (Connection con = ConnectionUtil.getConnection()) {

	            String messageInsertSQL = "INSERT INTO messages (sender_id, receiver_id, text, timestamp) VALUES (?, ?, ?, ?)";

	            try (PreparedStatement pst = con.prepareStatement(messageInsertSQL)) {

	                for (Message message : chat.getSentMessages()) {
	                	pst.setInt(1, chat.getSenderId());
	                	pst.setInt(2, chat.getReceiverId());
	                	pst.setString(3, message.getText());
	                	pst.setLong(4, message.getTimestamp());

	                	pst.executeUpdate();
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	                throw new DAOException("fail to send message");
	            }
	            return true;
	        }
		
		  
		  


}

	
	
	  
	  public ChatByPlayer getChatHistory(int loggedInUserId, int otherUserId) throws DAOException, SQLException {
		    ChatByPlayer chat = new ChatByPlayer();
		    chat.setSenderId(loggedInUserId);
		    chat.setReceiverId(otherUserId);

		    chat.setSentMessages(getMessages(loggedInUserId, otherUserId, "user"));

		    // Retrieve received messages
		    chat.setReceivedMessages(getMessages(otherUserId, loggedInUserId, "user"));

		    return chat;
		}
	
	  
	  
	  private List<Message> getMessages(int senderId, int receiverId, String senderType) throws DAOException, SQLException {
		    List<Message> messages = new ArrayList<>();

		    try (Connection con = ConnectionUtil.getConnection()) {
		    	 String messageSelectSQL = "SELECT id, text, timestamp FROM messages " +
		                 "WHERE sender_id = ? AND receiver_id = ? AND is_deleted = 0"; 

		        try (PreparedStatement pst = con.prepareStatement(messageSelectSQL)) {
		            pst.setInt(1, senderId);
		            pst.setInt(2, receiverId);

		            try (ResultSet rs = pst.executeQuery()) {
		                while (rs.next()) {
		                    Message message = new Message();
		                    message.setId(rs.getInt("id"));
		                    message.setText(rs.getString("text"));
		                    message.setTimestamp(rs.getLong("timestamp"));
		                    message.setSender(senderType);
		                    messages.add(message);
		                }
		            }
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		        throw new DAOException("Failed to retrieve messages");
		    }

		    return messages;
		}
	  
	  
		 public boolean deleteMessage(int id) throws SQLException, DAOException {
			 
			 try (Connection con = ConnectionUtil.getConnection()) {
				 
				 String deleteMessageSQL = "UPDATE messages SET is_deleted = 1 WHERE id = ?";
					try (PreparedStatement preparedStatement = con.prepareStatement(deleteMessageSQL)) {
						 preparedStatement.setInt(1, id);
				            preparedStatement.executeUpdate();
				            return true;
						
					}
				 
			 }
			 
			 catch (SQLException e) {
					throw new DAOException("Failed to delete the message");
				}

			
			 
		 }
		 
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	
	public static void main(String[] args) throws DAOException, SQLException {
		ChatDao da = new ChatDao();
	    ChatByPlayer chat = da.getChatHistory(1, 3);
System.out.println(chat);
	}
}
