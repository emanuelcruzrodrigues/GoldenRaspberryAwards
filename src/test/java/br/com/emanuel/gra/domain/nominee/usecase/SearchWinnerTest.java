package br.com.emanuel.gra.domain.nominee.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.emanuel.gra.domain.nominee.NomineeRepository;
import br.com.emanuel.gra.domain.nominee.Winner;
import br.com.emanuel.gra.domain.nominee.WinningInterval;

class SearchWinnerTest {
	
	private SearchWinner searchWinner;
	
	@Mock
	private NomineeRepository nomineeRepository;
	
	@BeforeEach
	public void setUp() {
		
		searchWinner = new SearchWinner();
		
		MockitoAnnotations.openMocks(this);
		searchWinner.nomineeRepository = nomineeRepository;
	}

	@Test
	void test_Get_Winning_Intervals() {
		
		List<Winner> winners = Arrays.asList(
											 createWinner("p1", 2000)
											,createWinner("p2", 2010)
											,createWinner("p1", 2001)
											,createWinner("p2", 2002)
											,createWinner("p3", 2003)
											,createWinner("p1", 1999)
											);
		
		when(nomineeRepository.queryProducersWithMoreThanOneWin()).thenReturn(winners);
		
		List<WinningInterval> winingIntervals = searchWinner.listWiningIntervals();
		assertEquals(3, winingIntervals.size(), 0);
		
		assertWinningEquals(winingIntervals.get(0), "p1", 1999, 2000);
		assertWinningEquals(winingIntervals.get(1), "p1", 2000, 2001);
		assertWinningEquals(winingIntervals.get(2), "p2", 2002, 2010);
		
	}

	private void assertWinningEquals(WinningInterval winningInterval, String producer, int previousWin, int followingWin) {
		assertEquals(producer, winningInterval.getProducer());
		assertEquals(previousWin, winningInterval.getPreviousWin(), 0);
		assertEquals(followingWin, winningInterval.getFollowingWin(), 0);
	}

	private Winner createWinner(String producer, Integer year) {
		Winner winner = new Winner();
		winner.setProducer(producer);
		winner.setYear(year);
		return winner;
	}

}
