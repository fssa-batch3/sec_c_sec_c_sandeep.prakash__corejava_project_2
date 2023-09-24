package com.fssa.bookandplay.validator;

import com.fssa.bookandplay.errors.ChatMessageErrors;
import com.fssa.bookandplay.errors.GroundOwnerDetailValidationErrors;
import com.fssa.bookandplay.exceptions.InvalidMessageException;

public class ChatMessageValidator {

	public ChatMessageValidator() {
		// TODO Auto-generated constructor stub
	}

	
	
	public boolean textValidator(String text) throws InvalidMessageException {
		/**
		 * firstName null and empty string check
		 */
		if (text == null || "".equals(text)) {
			throw new InvalidMessageException(ChatMessageErrors.INVALID_TEXT_NULL);
		}
	

		return true;

	}
}
