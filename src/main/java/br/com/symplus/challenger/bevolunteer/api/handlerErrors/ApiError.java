package br.com.symplus.challenger.bevolunteer.api.handlerErrors;

import org.springframework.http.HttpStatus;

public class ApiError {

	private HttpStatus status;
	private String message;

	public ApiError(HttpStatus status, String message) {
		this.status = status;
		this.message = message;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

}
