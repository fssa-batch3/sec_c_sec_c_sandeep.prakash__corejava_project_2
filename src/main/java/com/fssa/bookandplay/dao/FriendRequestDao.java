package com.fssa.bookandplay.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.fssa.bookandplay.errors.FriendRequestDaoErrors;
import com.fssa.bookandplay.exceptions.DAOException;
import com.fssa.bookandplay.model.FriendRequest;
import com.fssa.bookandplay.model.User;
import com.fssa.bookandplay.util.ConnectionUtil;
import com.fssa.bookandplay.util.Logger;

public class FriendRequestDao {

	public FriendRequestDao() {
		// TODO Auto-generated constructor stub
	}
	 static Logger logger = new Logger();

	public boolean createFriendRequest(FriendRequest friendRequest) throws DAOException, SQLException {

		/**
		 * The Query for calling insertground from sql
		 */
		String sql = "INSERT INTO Friend_requests (sender_id, receiver_id, status, sent_at) VALUES (?, ?, ?, ?)";
		/**
		 * Getting the ground details and inserting in sql
		 */
		try (Connection con = ConnectionUtil.getConnection()) {

			try (PreparedStatement pst = con.prepareStatement(sql)) {

				pst.setInt(1, friendRequest.getSenderId());
				pst.setInt(2, friendRequest.getReceiverId());
				pst.setString(3, friendRequest.getStatus());
				pst.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
				pst.executeUpdate();
			}
		} catch (SQLException e) {

			throw new DAOException(FriendRequestDaoErrors.INSERT_FRIENDREEQUEST_ERROR);
		}

		return true;

	}

	public boolean hasSentFriendRequest(int senderId, int receiverId) throws SQLException, DAOException {

		/**
		 * The Query for calling insertground from sql
		 */

		String sql = "SELECT COUNT(*) FROM Friend_requests WHERE sender_id = ? AND receiver_id = ? AND status = 'Pending'";


		/**
		 * Getting the ground details and inserting in sql
		 */
		try (Connection con = ConnectionUtil.getConnection()) {

			try (PreparedStatement pst = con.prepareStatement(sql)) {

				pst.setInt(1, senderId);
				pst.setInt(2, receiverId);

				try (ResultSet rs = pst.executeQuery()) {
					if (rs.next()) {
						int count = rs.getInt(1);
						return count > 0;
					}
				}
			}
		} catch (SQLException e) {

			throw new DAOException(FriendRequestDaoErrors.INSERT_FRIENDREEQUEST_ERROR);
		}

		return false;

	}

	
	
	
	public boolean areMutuallyFriends(int userId1, int userId2) throws SQLException, DAOException {
	    String sql = "SELECT COUNT(*) FROM Friend_requests " +
	                 "WHERE ((sender_id = ? AND receiver_id = ?) OR (sender_id = ? AND receiver_id = ?)) " +
	                 "AND status = 'Accepted'";

	    try (Connection con = ConnectionUtil.getConnection()) {
	        try (PreparedStatement pst = con.prepareStatement(sql)) {
	            pst.setInt(1, userId1);
	            pst.setInt(2, userId2);
	            pst.setInt(3, userId2);
	            pst.setInt(4, userId1);

	            try (ResultSet rs = pst.executeQuery()) {
	                if (rs.next()) {
	                    int count = rs.getInt(1);
	                    return count >= 2; 
	                }
	            }
	        }
	    } catch (SQLException e) {
	        throw new DAOException(FriendRequestDaoErrors.INSERT_FRIENDREQUEST_ERROR);
	    }

	    return false;
	}

	public List<FriendRequest> getFriendRequest(int receiverId) throws DAOException, SQLException {

		List<FriendRequest> pendingfriendreqList = new ArrayList<>();

		/**
		 * The Query for selecting all grounddetails from all the table
		 */

//		String selectQuery = "SELECT * FROM Friend_requests WHERE receiver_id = ? AND status = 'Pending'";
	    String sql = "SELECT fr.*, u.* " +
                "FROM Friend_requests fr " +
                "JOIN User u ON fr.sender_id = u.id " +
                "WHERE fr.receiver_id = ? AND fr.status = 'Pending'";
		try (Connection con = ConnectionUtil.getConnection()) {

			try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
				preparedStatement.setInt(1, receiverId);

				try (ResultSet rs = preparedStatement.executeQuery()) {
					while (rs.next()) {
					FriendRequest friendRequest = new FriendRequest();
					friendRequest.setRequestId(rs.getInt("request_id"));
					friendRequest.setSenderId(rs.getInt("sender_id"));
					friendRequest.setReceiverId(rs.getInt("receiver_id"));
					friendRequest.setStatus(rs.getString("status"));
					// friendRequest.setSentAt(rs.getTimestamp("sent_at"));
					  User sender = new User();
					    sender.setUserId(rs.getInt("id"));
					    sender.setFirstName(rs.getString("first_name"));
					    sender.setEmail(rs.getString("email"));
					    sender.setImage(rs.getString("imageUrl"));

					    // Set the sender information within the FriendRequest object
					    friendRequest.setSender(sender);
					pendingfriendreqList.add(friendRequest);
				}
				}
			}

		}

		catch (SQLException e) {
			throw new DAOException(FriendRequestDaoErrors.READ_FRIENDREEQUEST_ERROR);
		}

		return pendingfriendreqList;
	}
	
	
	 public boolean acceptFriendRequest(int requestId) throws SQLException, DAOException {
		 
		 try (Connection con = ConnectionUtil.getConnection()) {
			 
			 String sql = "UPDATE Friend_requests SET status = 'Accepted' WHERE request_id = ?";
				try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
					 preparedStatement.setInt(1, requestId);
			            preparedStatement.executeUpdate();
			            return true;
					
				}
			 
		 }
		 
		 catch (SQLException e) {
				throw new DAOException(FriendRequestDaoErrors.ACCEPT_FRIENDREEQUEST_ERROR);
			}

		
		 
	 }
	 
	 public boolean rejectFriendRequest(int requestId) throws SQLException, DAOException {
		 
		 try (Connection con = ConnectionUtil.getConnection()) {
			 
			 String sql = "UPDATE Friend_requests SET status = 'Rejected' WHERE request_id = ?";
				try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
					 preparedStatement.setInt(1, requestId);
			            preparedStatement.executeUpdate();
			            return true;
					
				}
			 
		 }
		 
		 catch (SQLException e) {
				throw new DAOException(FriendRequestDaoErrors.ACCEPT_FRIENDREEQUEST_ERROR);
			}

		
		 
	 }
	 
	 
	 
	 
		public List<FriendRequest> getFriendList(int receiverId) throws DAOException, SQLException {

			List<FriendRequest> friendList = new ArrayList<>();

			/**
			 * The Query for selecting all grounddetails from all the table
			 */

//			String selectQuery = "SELECT * FROM Friend_requests WHERE receiver_id = ? AND status = 'Pending'";
		    String sql = "SELECT fr.*, u.* " +
	                "FROM Friend_requests fr " +
	                "JOIN User u ON fr.sender_id = u.id " +
	                "WHERE fr.receiver_id = ? AND fr.status = 'Accepted'";
			try (Connection con = ConnectionUtil.getConnection()) {

				try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
					preparedStatement.setInt(1, receiverId);

					try (ResultSet rs = preparedStatement.executeQuery()) {
						while (rs.next()) {
						FriendRequest friendRequest = new FriendRequest();
						friendRequest.setRequestId(rs.getInt("request_id"));
						friendRequest.setSenderId(rs.getInt("sender_id"));
						friendRequest.setReceiverId(rs.getInt("receiver_id"));
						friendRequest.setStatus(rs.getString("status"));
						// friendRequest.setSentAt(rs.getTimestamp("sent_at"));
						  User sender = new User();
						    sender.setUserId(rs.getInt("id"));
						    sender.setFirstName(rs.getString("first_name"));
						    sender.setEmail(rs.getString("email"));
						    sender.setImage(rs.getString("imageUrl"));

						    // Set the sender information within the FriendRequest object
						    friendRequest.setSender(sender);
						    friendList.add(friendRequest);
					}
					}
				}

			}

			catch (SQLException e) {
				throw new DAOException(FriendRequestDaoErrors.READ_FRIENDREEQUEST_ERROR);
			}

			return friendList;
		}
		
	 
	 
	 
	 
	 
	
	public static void main(String[] args) {
		
		boolean pendingfriendreqList=false;
		FriendRequestDao fsda=new FriendRequestDao();
		try {
			pendingfriendreqList=fsda.areMutuallyFriends(3, 5);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info(pendingfriendreqList);
		
	}

}
