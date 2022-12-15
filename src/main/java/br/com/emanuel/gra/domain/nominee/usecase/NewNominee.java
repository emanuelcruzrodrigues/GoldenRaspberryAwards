package br.com.emanuel.gra.domain.nominee.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.emanuel.gra.domain.nominee.Nominee;
import br.com.emanuel.gra.domain.nominee.NomineeRepository;
import br.com.emanuel.gra.domain.winner.usecase.UpdateWinner;
import jakarta.transaction.Transactional;

@Component
public class NewNominee {
	
	@Autowired
	private NomineeRepository nomineeRepository;
	
	@Autowired
	private UpdateWinner updateWinner;
	
	@Transactional
	public Nominee save(Nominee nominee) {
		
		Nominee stored = nomineeRepository.save(nominee);
		
		updateWinner.updateWinnersRelated(nominee);
		
		return stored;
	}

}
