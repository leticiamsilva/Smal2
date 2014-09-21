package org.smal2.infrastructure.presentation.view.http;

import org.apache.log4j.Logger;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandlerViewJSON {
	private Logger logger = Logger.getLogger(ExceptionHandlerViewJSON.class);

	// [CMP] useful methods to log bad requests
	// from http://spring.io/blog/2013/11/01/exception-handling-in-spring-mvc
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> defaultErrorHandler(Exception ex)
			throws Exception {
		logger.error("Got exception:" + ex.getMessage());

		// If the exception is annotated with @ResponseStatus rethrow it and let
		// the framework handle it
		// AnnotationUtils is a Spring Framework utility class.
		if (AnnotationUtils.findAnnotation(ex.getClass(), ResponseStatus.class) != null) {
			throw ex;
		}

		String message;

		if (ex.getMessage() == null || ex.getMessage().trim() == "") {
			message = "Unknown exception";
		} else {
			message = ex.getMessage();
		}

		return new ResponseEntity<String>(message,
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
