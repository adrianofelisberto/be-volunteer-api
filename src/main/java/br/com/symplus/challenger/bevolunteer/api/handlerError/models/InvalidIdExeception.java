package br.com.symplus.challenger.bevolunteer.api.handlerError.models;

import br.com.symplus.challenger.bevolunteer.api.config.MessageComponent;
import br.com.symplus.challenger.bevolunteer.api.enuns.MessageCode;

public class InvalidIdExeception extends Exception {
	private static final long serialVersionUID = 1L;
	private final static String message = MessageComponent.getMessage(MessageCode.INVALID_ID.getCode());
	
	public InvalidIdExeception() {
		super(message);
	}
}
