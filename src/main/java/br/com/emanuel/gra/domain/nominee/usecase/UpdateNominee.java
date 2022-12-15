package br.com.emanuel.gra.domain.nominee.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.emanuel.gra.domain.nominee.Nominee;
import br.com.emanuel.gra.domain.winner.usecase.UpdateWinner;
import jakarta.transaction.Transactional;

@Component
public class UpdateNominee {
	
	@Autowired
	private LoadNominee loadNominee;
	
	@Autowired
	private UpdateWinner updateWinner;
	
	@Transactional
	public Nominee update(Nominee nominee, Long id) {
		return update(nominee, id, false);
	}

	@Transactional
	public Nominee patch(Nominee nominee, Long id) {
		return update(nominee, id, true);
	}
	
	private Nominee update(Nominee nominee, Long id, boolean onlyNotNull) {
		
		Nominee stored = loadNominee.findById(id);
		
		if (onlyNotNull) {
			stored.updateNotNull(nominee);
		} else {
			stored.update(nominee);
		}
		
		updateWinner.updateWinnersRelated(stored);
		
		return stored;
	}

}
