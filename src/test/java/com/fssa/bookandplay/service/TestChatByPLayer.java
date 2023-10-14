package com.fssa.bookandplay.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fssa.bookandplay.exceptions.DAOException;
import com.fssa.bookandplay.model.ChatByPlayer;
import com.fssa.bookandplay.model.Message;
import com.fssa.bookandplay.util.Logger;

class TestChatByPLayer {
	static Logger logger = new Logger();
	ChatService chatService = new ChatService();

	@Test
	void testAddChat() throws DAOException, SQLException {
		ChatByPlayer chat = new ChatByPlayer();
		chat.setSenderId(1); // Replace with the sender's ID
		chat.setReceiverId(1); // Replace with the receiver's ID
		List<Message> messages = new ArrayList<>();
		Message message1 = new Message();
		message1.setText("Hi sandeep");
		message1.setSender("Sender");
		message1.setTimestamp(System.currentTimeMillis());
		messages.add(message1);
		chat.setSentMessages(messages);
		Assertions.assertTrue(chatService.addChat(chat));

	}

	@Test
	void testDeleteChat() throws DAOException, SQLException {

		Assertions.assertTrue(chatService.deleteMessage(1));

	}

	@Test
	void testGetChat() throws DAOException, SQLException {

		ChatByPlayer chat = chatService.getChatHistory(1, 3);
		assertNotNull(chat);
		logger.info(chat);

	}
}
