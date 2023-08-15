package com.fssa.bookandplay.service;


import java.sql.SQLException;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fssa.bookandplay.builder.UserBuilder;
import com.fssa.bookandplay.exceptions.DAOException;
import com.fssa.bookandplay.model.Ground;
import com.fssa.bookandplay.model.User;
 class TestUserService {

	 UserService userService=new UserService();
	 
 User getUser() {
	 List<String> validsports = Arrays.asList("cricket", "football", "tennis");
		LocalTime startTime = LocalTime.of(10, 30); // 10:00 AM
		LocalTime endTime = LocalTime.of(11, 30); // 5:00 PM
	 User user1 = new UserBuilder().userIdBuild(1).firstNameBuild("sandeep").lastNameBuild("sand")
				.emailBuild("sandeep@gmail.com").phoneNumberBuild("9922920022").passwordBuild("sand@U2208892*7")
				.playerStatusBuild(true)
				.displayNameBuid("sandeep").ageBuild(27).genderBuild("male").knownSportsBuild(validsports)
				.locationBuild("chennai").timingAvailFromBuild(startTime).timingAvailToBuild(endTime)
				.aboutBuilder("HelloWorld").imageBuilder("https://example.com/image1.jpg").build();
return user1;
	 }
 
 
 
User getUserOnly() {

 User user1 = new UserBuilder().userIdBuild(1).firstNameBuild("sandeep").lastNameBuild("sand")
			.emailBuild("sandeep@gmail.com").phoneNumberBuild("9922920022").passwordBuild("sand@U2208892*7")
			.playerStatusBuild(false)
			.imageBuilder("https://example.com/image1.jpg").build();
return user1;
 }
	 @Test
		void testAddUser() throws DAOException, SQLException {
			User user = getUser();

			Assertions.assertTrue(userService.addUserPlayer(user));

		}

		@Test
		void testUpdateUser() throws DAOException, SQLException {
			User user = getUser();

			Assertions.assertTrue(userService.updateUserPlayer(user));

		}

		 @Test
			void testAddUserOnly() throws DAOException, SQLException {
				User user = getUserOnly();

				Assertions.assertTrue(userService.addUserOnly(user));

			}

			@Test
			void testUpdateUserOnly() throws DAOException, SQLException {
				User user =getUserOnly();

				Assertions.assertTrue(userService.updateUserOnly(user));

			}




		@Test
		void testGetUserDetail() throws DAOException, SQLException {

			Assertions.assertTrue(userService.getUserDetails());

		}
	 
	 
}
