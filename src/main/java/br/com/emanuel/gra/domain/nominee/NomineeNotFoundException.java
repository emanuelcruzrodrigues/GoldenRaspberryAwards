package br.com.emanuel.gra.domain.nominee;

@SuppressWarnings("serial")
public class NomineeNotFoundException extends RuntimeException { 
	
	public NomineeNotFoundException(Long id) {
		super(String.format("No nominee found with the giving id: %d", id));
	}

}
