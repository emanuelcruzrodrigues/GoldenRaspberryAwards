package br.com.emanuel.gra.infra.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.emanuel.gra.domain.nominee.Nominee;
import br.com.emanuel.gra.domain.nominee.NomineeRepository;
import br.com.emanuel.gra.domain.nominee.Winner;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Repository
public class NomineeRepositoryImpl implements NomineeRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Nominee save(Nominee nominee) {
		entityManager.persist(nominee);
		return nominee;
	}
	
	@Override
	public List<Nominee> listAll(){
		
		StringBuilder ql = new StringBuilder();
		ql.append(" select n from Nominee n ");
		
		Query query = entityManager.createQuery(ql.toString());
		
		@SuppressWarnings("unchecked")
		List<Nominee> resultList = query.getResultList();
		
		return resultList;
	}

	@Override
	public Nominee load(Long id) {
		Nominee nominee = entityManager.find(Nominee.class, id);
		return nominee;
	}

	@Override
	public void delete(Nominee nominee) {
		entityManager.remove(nominee);
	}

	@Override
	public List<String> queryProducersWithMoreThanOneWin() {
		
		StringBuilder ql = new StringBuilder();
		ql.append(" select w.producer from Winner w ");
		ql.append(" group by w.producer ");
		ql.append(" having count(w) > 1 ");
		
		Query query = entityManager.createQuery(ql.toString());
		
		@SuppressWarnings("unchecked")
		List<String> result = query.getResultList();
		return result;
	}
	
	@Override
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
}
