package com.fssa.bookandplay.service;

import java.sql.SQLException;
import java.util.List;

import com.fssa.bookandplay.dao.FriendRequestDao;
import com.fssa.bookandplay.exceptions.DAOException;
import com.fssa.bookandplay.model.FriendRequest;


public class FriendRequestService {

	
	FriendRequestDao friendRequestDao =new FriendRequestDao();
	
	/**
	 * The add user
	 */
	public boolean createFriendRequest(FriendRequest friendRequest) throws DAOException, SQLException {
		
		if(friendRequestDao.createFriendRequest(friendRequest)){
			return true;
		}
		return false;
		
	}
	
	
	
	public boolean checkFriendRequest(int senderId, int receiverId) throws DAOException, SQLException {
		
		if(friendRequestDao.hasSentFriendRequest(senderId, receiverId)){
			return true;
		}
		return false;
		
		
	}

	public boolean hasFriendAlready(int senderId, int receiverId) throws DAOException, SQLException {
		
		if(friendRequestDao.areMutuallyFriends(senderId, receiverId)){
			return true;
		}
		return false;
		
		
	}

	public List<FriendRequest> getAllFriendReq(int receiverId) throws DAOException, SQLException {

		return friendRequestDao.getFriendRequest(receiverId);

	}
	
	
	public boolean acceptFriendRequest(int requestId) throws DAOException, SQLException {
		
		if(friendRequestDao.acceptFriendRequest(requestId)){
			return true;
		}
		return false;
		
		
		
	}
	
	
public boolean RejectFriendRequest(int requestId) throws DAOException, SQLException {
		
		if(friendRequestDao.rejectFriendRequest(requestId)){
			return true;
		}
		return false;
		
		
	}



public List<FriendRequest> getAllFriendList(int receiverId) throws DAOException, SQLException {

	return friendRequestDao.getFriendList(receiverId);

}


}
