package br.com.emanuel.gra.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.emanuel.gra.domain.winner.WinningIntervalGroup;
import br.com.emanuel.gra.domain.winner.usecase.SearchWinner;

@RestController
public class WinnerRestController {
	
	@Autowired
	private SearchWinner searchWinner;
	
	/**
	 * @see WinnerRestControllerTest
	 */
	@GetMapping("/winners")
	WinningIntervalGroup listWinners() {
		return searchWinner.getWinningIntervalGroup();
	}

}
