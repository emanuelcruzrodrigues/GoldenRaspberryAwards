package br.com.emanuel.gra.domain.winner.usecase;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.emanuel.gra.domain.nominee.Nominee;
import br.com.emanuel.gra.domain.winner.Winner;
import br.com.emanuel.gra.domain.winner.WinnerRepository;

@Component
public class DeleteWinner {
	
	@Autowired
	private SearchWinner searchWinner;
	
	@Autowired
	private WinnerRepository winnerRepository;
	
	public void deleteWinnersRelated(Nominee nominee) {
		List<Winner> winners = searchWinner.searchWinnersRelated(nominee);
		
		for (Winner winner : winners) {
			winnerRepository.delete(winner);
		}
	}

}
