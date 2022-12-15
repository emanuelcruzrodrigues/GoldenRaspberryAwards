package br.com.emanuel.gra.domain.nominee.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.emanuel.gra.domain.nominee.Nominee;
import br.com.emanuel.gra.domain.nominee.NomineeNotFoundException;
import br.com.emanuel.gra.domain.nominee.NomineeRepository;

@Component
public class LoadNominee {
	
	@Autowired
	private NomineeRepository nomineeRepository;
	
	public Nominee findById(Long id) {
		Nominee nominee = nomineeRepository.load(id);
		if (nominee == null) {
			throw new NomineeNotFoundException(id);
		}
		return nominee;
	}

}
