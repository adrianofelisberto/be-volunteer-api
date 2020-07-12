package br.com.symplus.challenger.bevolunteer.api.enuns;

public enum MessageCode {
	CREATE_SUCCESS("create.success"),
	NOT_FOUND("not.found"),
	ERROR_SERVER("server.error");

	private String code;

	private MessageCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

}
