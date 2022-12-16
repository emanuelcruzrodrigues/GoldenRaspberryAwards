package br.com.emanuel.gra.domain.nominee;

import java.util.List;

public interface NomineeRepository {

	Nominee save(Nominee nominee);

	List<Nominee> listAll();

	Nominee load(Long id);

	void delete(Nominee stored);
	
	List<Winner> queryProducersWithMoreThanOneWin();
	
}
