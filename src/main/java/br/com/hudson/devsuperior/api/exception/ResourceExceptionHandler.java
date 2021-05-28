package br.com.hudson.devsuperior.api.exception;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.hudson.devsuperior.domain.exception.DataBaseException;
import br.com.hudson.devsuperior.domain.exception.DomainException;
import br.com.hudson.devsuperior.domain.exception.ResourceNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(DomainException.class)
	public ResponseEntity<StandardError> entityNotFound(DomainException e, HttpServletRequest request){
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError("Erro: "+e.getMessage());
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(status.value()).body(err);			
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> entityNotFound(ResourceNotFoundException e, HttpServletRequest request){
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError("Recurso não encontrado");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(status.value()).body(err);			
	}
	
	@ExceptionHandler(DataBaseException.class)
	public ResponseEntity<StandardError> dataBase(DataBaseException e, HttpServletRequest request){
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError("Database Exception");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(status.value()).body(err);		
	}


}
