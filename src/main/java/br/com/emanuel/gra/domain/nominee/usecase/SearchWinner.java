package br.com.emanuel.gra.domain.nominee.usecase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.emanuel.gra.domain.nominee.NomineeRepository;
import br.com.emanuel.gra.domain.nominee.Winner;
import br.com.emanuel.gra.domain.nominee.WinningInterval;
import br.com.emanuel.gra.domain.nominee.WinningIntervalGroup;

@Component
public class SearchWinner {

	@Autowired
	NomineeRepository nomineeRepository;

	public WinningIntervalGroup getWinningIntervalGroup() {
		List<WinningInterval> winingIntervals = listWiningIntervals();
		WinningIntervalGroup winningIntervalGroup = new WinningIntervalGroup(winingIntervals);
		return winningIntervalGroup;
	}
	
	/**
	 * @see SearchWinnerTest
	 */
	public List<WinningInterval> listWiningIntervals() {
		
		List<WinningInterval> result = new ArrayList<>();
		
		List<Winner> winners = nomineeRepository.queryProducersWithMoreThanOneWin();
		
		Map<String, List<Winner>> winsPerProducer = winners.stream().collect(Collectors.groupingBy(w -> w.getProducer()));
		
		for (String producer : winsPerProducer.keySet()) {
			
			List<Winner> wins = winsPerProducer.get(producer);
			Collections.sort(wins);
			
			Winner previousWin = null;
			
			for (Winner win : wins) {
				
				if (previousWin != null) {
					WinningInterval interval = new WinningInterval();
					interval.setProducer(producer);
					interval.setPreviousWin(previousWin.getYear());
					interval.setFollowingWin(win.getYear());
					
					result.add(interval);
				}
				
				previousWin = win;
			}
		}
		
		return result;
	}
	
}
