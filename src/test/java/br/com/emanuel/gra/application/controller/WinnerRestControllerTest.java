package br.com.emanuel.gra.application.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;

import br.com.emanuel.gra.domain.winner.WinningInterval;
import br.com.emanuel.gra.domain.winner.WinningIntervalGroup;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class WinnerRestControllerTest {
	
	@Autowired
	private TestRestTemplate testRestTemplate;

	@Test
	void test_API() {
		WinningIntervalGroup response = testRestTemplate.getForObject("/winners", WinningIntervalGroup.class);
		
		List<WinningInterval> minimumIntervals = response.getMinimumIntervals();
		assertEquals(1, minimumIntervals.size(), 0);
		assertWinningIntervalEquals(minimumIntervals.get(0), "Joel Silver", 1990, 1991);
		
		List<WinningInterval> maximumIntervals = response.getMaximumIntervals();
		assertEquals(1, maximumIntervals.size(), 0);
		assertWinningIntervalEquals(maximumIntervals.get(0), "Matthew Vaughn", 2002, 2015);
		
	}
	
	private void assertWinningIntervalEquals(WinningInterval winningInterval, String producer, int previousWin, int followingWin) {
		assertEquals(producer, winningInterval.getProducer());
		assertEquals(previousWin, winningInterval.getPreviousWin(), 0);
		assertEquals(followingWin, winningInterval.getFollowingWin(), 0);
	}

}
