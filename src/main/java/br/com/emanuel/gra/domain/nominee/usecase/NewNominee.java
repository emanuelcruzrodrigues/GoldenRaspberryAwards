package br.com.emanuel.gra.domain.nominee.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.emanuel.gra.domain.nominee.Nominee;
import br.com.emanuel.gra.domain.nominee.NomineeRepository;
import jakarta.transaction.Transactional;

@Component
public class NewNominee {
	
	@Autowired
	private NomineeRepository nomineeRepository;
	
	@Transactional
	public Nominee save(Nominee nominee) {
		nominee.refreshWinners();
		Nominee stored = nomineeRepository.save(nominee);
		return stored;
	}

}
