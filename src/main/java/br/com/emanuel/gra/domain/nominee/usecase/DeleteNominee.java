package br.com.emanuel.gra.domain.nominee.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.emanuel.gra.domain.nominee.Nominee;
import br.com.emanuel.gra.domain.nominee.NomineeRepository;
import br.com.emanuel.gra.domain.winner.usecase.DeleteWinner;
import jakarta.transaction.Transactional;

@Component
public class DeleteNominee {
	
	@Autowired
	private LoadNominee loadNominee;
	
	@Autowired
	private DeleteWinner deleteWinner;
	
	@Autowired
	private NomineeRepository nomineeRepository;
	
	@Transactional
	public void deleteById(Long id) {
		
		Nominee stored = loadNominee.findById(id);
		
		deleteWinner.deleteWinnersRelated(stored);
		
		nomineeRepository.delete(stored);
	}

}
