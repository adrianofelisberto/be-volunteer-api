package br.com.symplus.challenger.bevolunteer.api.enuns;

public enum MessageCode {
	CREATE_SUCCESS("create.success"), NOT_FOUND("not.found"), REQUIRED_FIELD("required.field"),
	METHOD_NOT_SUPPORTED("method.not.supported"), ERROR_SERVER("server.error"),
	INVALID_ID("invalid.id"), INTEREST_ERROR("interest.error");

	private String code;

	private MessageCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

}
