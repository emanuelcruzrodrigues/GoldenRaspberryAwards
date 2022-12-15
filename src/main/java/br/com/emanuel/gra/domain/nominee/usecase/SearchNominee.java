package br.com.emanuel.gra.domain.nominee.usecase;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.emanuel.gra.domain.nominee.Nominee;
import br.com.emanuel.gra.domain.nominee.NomineeRepository;

@Component
public class SearchNominee {
	
	@Autowired
	private NomineeRepository nomineeRepository;
	
	public List<Nominee> listAll() {
		return nomineeRepository.listAll();
	}

}
