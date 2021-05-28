package br.com.hudson.devsuperior.domain.exception;

public class ResourceNotFoundException extends DomainException {

	private static final long serialVersionUID = 1L;
	
	public ResourceNotFoundException(String msg) {
		super(msg);		
	}	
}
