package br.com.emanuel.gra.infra.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.emanuel.gra.domain.nominee.Nominee;
import br.com.emanuel.gra.domain.winner.Winner;
import br.com.emanuel.gra.domain.winner.WinnerRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Repository
public class WinnerRepositoryImpl implements WinnerRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void save(Winner winner) {
		entityManager.persist(winner);
	}

	@Override
	public List<Winner> queryProducersWithMoreThanOneWin() {
		
		StringBuilder ql = new StringBuilder();
		ql.append(" select w.producer, count(w.id) from Winner w ");
		ql.append(" group by w.producer ");
		ql.append(" having count(w) > 1 ");
		
		Query query = entityManager.createQuery(ql.toString());
		
		@SuppressWarnings("unchecked")
		List<Object[]> resultList = query.getResultList();
		
		List<Winner> result = new ArrayList<>();
		
		for (Object[] objects : resultList) {
			String producer = (String)objects[0];
			List<Winner> winsFromProducer = listWins(producer);
			result.addAll(winsFromProducer);
		}
		
		return result;
	}
	
	public List<Winner> listWins(String producer) {
		
		StringBuilder ql = new StringBuilder();
		ql.append(" select w from Winner w ");
		ql.append(" where w.producer = :prod ");
		
		Query query = entityManager.createQuery(ql.toString());
		query.setParameter("prod", producer);
		
		@SuppressWarnings("unchecked")
		List<Winner> resultList = query.getResultList();
		
		return resultList;
	}

	@Override
	public List<Winner> listWinnersRelated(Nominee nominee) {
		StringBuilder ql = new StringBuilder();
		ql.append(" select w from Winner w ");
		ql.append(" where w.nominee.id = :idNominee ");
		
		Query query = entityManager.createQuery(ql.toString());
		query.setParameter("idNominee", nominee.getId());
		
		@SuppressWarnings("unchecked")
		List<Winner> resultList = query.getResultList();
		
		return resultList;
	}

	@Override
	public void delete(Winner winner) {
		entityManager.remove(winner);
	}

}
