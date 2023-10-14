package com.fssa.bookandplay.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fssa.bookandplay.exceptions.DAOException;
import com.fssa.bookandplay.model.FriendRequest;
import com.fssa.bookandplay.util.Logger;

public class TestFriendRequest {

	static Logger logger = new Logger();
	ChatService chatService=new ChatService();
	FriendRequestService frs = new FriendRequestService();
	FriendRequest frsdata = new FriendRequest(2, 1, "Pending");
	@Test
	void testAddFriendRequest() throws DAOException, SQLException {
		Assertions.assertTrue(frs.createFriendRequest(frsdata));

	}
	@Test
	void testCheckFriendRequest() throws DAOException, SQLException {
		Assertions.assertTrue(frs.checkFriendRequest(1, 2));

	}
	@Test
	void testhHasFriendAlready() throws DAOException, SQLException {
		Assertions.assertTrue(frs.hasFriendAlready(1, 2));

	}
	@Test
	void testAcceptFriendRequest() throws DAOException, SQLException {
		Assertions.assertTrue(frs.acceptFriendRequest(1));

	}
	@Test
	void testRejectFriendRequest() throws DAOException, SQLException {
		Assertions.assertTrue(frs.RejectFriendRequest(1));

	}

	@Test
	void testGetAllFriendReq() throws DAOException, SQLException {

		List<FriendRequest> friendRequest = frs.getAllFriendReq(1);
		assertNotNull(friendRequest);
		for (FriendRequest fr : friendRequest) {

			logger.info(fr);

		}

	}

	@Test
	void testGetAllFriendList() throws DAOException, SQLException {

		List<FriendRequest> friendList = frs.getAllFriendList(1);
		assertNotNull(friendList);
		for (FriendRequest fr : friendList) {

			logger.info(fr);

		}

	}

}
