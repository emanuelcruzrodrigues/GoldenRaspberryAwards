package br.com.emanuel.gra.domain.winner;

import java.util.List;

import br.com.emanuel.gra.domain.nominee.Nominee;

public interface WinnerRepository {

	void save(Winner winner);

	List<Winner> queryProducersWithMoreThanOneWin();

	List<Winner> listWinnersRelated(Nominee nominee);

	void delete(Winner winner);

}
