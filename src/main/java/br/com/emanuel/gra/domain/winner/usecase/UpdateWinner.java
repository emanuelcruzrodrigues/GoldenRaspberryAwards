package br.com.emanuel.gra.domain.winner.usecase;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.emanuel.gra.domain.nominee.Nominee;
import br.com.emanuel.gra.domain.winner.Winner;
import br.com.emanuel.gra.domain.winner.WinnerRepository;

@Component
public class UpdateWinner {
	
	@Autowired
	private WinnerRepository winnerRepository;
	
	@Autowired
	private DeleteWinner deleteWinner;
	
	public void updateWinnersRelated(Nominee nominee) {
		
		deleteWinner.deleteWinnersRelated(nominee);
		
		List<Winner> winners = nominee.getWinners();
		for (Winner producerWin : winners) {
			winnerRepository.save(producerWin);
		}
	}

}
