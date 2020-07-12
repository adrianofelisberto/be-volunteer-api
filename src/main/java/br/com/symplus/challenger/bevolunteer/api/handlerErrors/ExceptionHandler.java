package br.com.symplus.challenger.bevolunteer.api.handlerErrors;

import org.hibernate.PropertyValueException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.symplus.challenger.bevolunteer.api.config.MessageComponent;
import br.com.symplus.challenger.bevolunteer.api.enuns.MessageCode;
import br.com.symplus.challenger.bevolunteer.api.handlerError.models.InvalidIdExeception;
import javassist.NotFoundException;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {


	@org.springframework.web.bind.annotation.ExceptionHandler({ Exception.class })
	public ResponseEntity<Object> handleGenericException(Exception ex, WebRequest request) {
		ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler({ JpaObjectRetrievalFailureException.class })
	public ResponseEntity<Object> handleJpaObjectRetrievalFailureException(JpaObjectRetrievalFailureException ex, WebRequest request) {
		String message = MessageComponent.getMessage(MessageCode.INTEREST_ERROR.getCode());
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, message);
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}
	
	
	
	@org.springframework.web.bind.annotation.ExceptionHandler({ PropertyValueException.class })
	public ResponseEntity<Object> handleConstraintViolation(PropertyValueException ex, WebRequest request) {
		String message = MessageComponent.getMessage(MessageCode.REQUIRED_FIELD.getCode(), ex.getPropertyName());
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, message);
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}
	

	@org.springframework.web.bind.annotation.ExceptionHandler({ InvalidIdExeception.class })
	public ResponseEntity<Object> handleInvalidIdException(InvalidIdExeception ex, WebRequest request) {
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage());
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}
	

	@org.springframework.web.bind.annotation.ExceptionHandler({ NotFoundException.class })
	public ResponseEntity<Object> handleNotFoundException(NotFoundException ex, WebRequest request) {
		ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage());
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		StringBuilder builderMethodSupported = new StringBuilder();
		ex.getSupportedHttpMethods().forEach(typeMethod -> builderMethodSupported.append(typeMethod + " "));
		
		String message = MessageComponent.getMessage(MessageCode.METHOD_NOT_SUPPORTED.getCode(), ex.getMethod(), builderMethodSupported.toString().trim());

		ApiError apiError = new ApiError(HttpStatus.METHOD_NOT_ALLOWED, message);
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}
}
