package com.doodle.pollschallenge.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseBody
    @ExceptionHandler(HandlerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String handlerNotFoundHandler(HandlerNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(NoPollsForDateException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String pollAfterDateNotFoundHandler(NoPollsForDateException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(NoPollsForTitleException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String pollForTitleNotFoundHandler(NoPollsForTitleException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(NoPollsForUserException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String pollForUserNotFoundHandler(NoPollsForUserException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleAll(final Exception ex, final WebRequest request) {
        logger.info(ex.getClass().getName());
        logger.error("exceptions:", ex);
        //
        final ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage(), "exceptions occurred");
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    }

}
