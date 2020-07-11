package br.com.symplus.challenger.bevolunteer.api.dtos;

public class MessageDTO {

	private String message;
	
	public MessageDTO() {}
	
	public MessageDTO(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
