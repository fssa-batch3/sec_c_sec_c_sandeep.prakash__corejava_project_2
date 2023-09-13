package com.fssa.bookandplay.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.fssa.bookandplay.exceptions.DAOException;
import com.fssa.bookandplay.model.GroundOwner;
import com.fssa.bookandplay.util.Logger;

class TestGroundOwnerService {
	GroundOwnerService groundOwnerService = new GroundOwnerService();
	static Logger logger = new Logger();

	GroundOwner getOwnerDetail() {

		GroundOwner user1 = new GroundOwner("sandeep", "FC Marina", "sandeepUdnf@gmail.com", 9878487899l, "sand@U2208892*7",
				"https://example.com/image1.jpg");
		return user1;
	}
	
	GroundOwner getOwnerDetailWithId() {

		GroundOwner user1 = new GroundOwner(1,"Ramesh", "Sanity", "sasndeepU@gmail.com", 9878456899l, "sand@U2208892*7",
				"https://example.com/image1.jpg");
		return user1;
	}
	
	 @Test
		//@Disabled("This test is currently disabled ")
		void testAddGroundOwner() throws DAOException, SQLException {
		 GroundOwner user = getOwnerDetail();

			Assertions.assertTrue(groundOwnerService.addgroundOwner(user));

		}

		//@Test
		@Disabled("This test is currently disabled ")
		void testUpdateGroundOwner() throws DAOException, SQLException {
			 GroundOwner user = getOwnerDetailWithId();

				Assertions.assertTrue(groundOwnerService.updategroundOwner(user));
		}

		
		@Test
		void testGetGroundOwnerDetail() throws DAOException, SQLException {

			List<GroundOwner>  groundOwnerList=groundOwnerService.getgroundOwnerDetails();
			 assertNotNull(groundOwnerList); 
			for(GroundOwner gr:groundOwnerList) {
				logger.info( gr);
				
			}

		}
		
		@Test
		void testGetGroundOwneremailpass() throws DAOException, SQLException {

			GroundOwner groundOwnerList=groundOwnerService.getgroundOwneremailpass("sasbu@gmail.com","sand@U2208892*7");
	
			 assertNotNull(groundOwnerList); 
				logger.info( groundOwnerList);
				
			
		}

}
