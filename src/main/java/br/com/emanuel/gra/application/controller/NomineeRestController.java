package br.com.emanuel.gra.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.emanuel.gra.domain.nominee.Nominee;
import br.com.emanuel.gra.domain.nominee.usecase.DeleteNominee;
import br.com.emanuel.gra.domain.nominee.usecase.LoadNominee;
import br.com.emanuel.gra.domain.nominee.usecase.NewNominee;
import br.com.emanuel.gra.domain.nominee.usecase.SearchNominee;
import br.com.emanuel.gra.domain.nominee.usecase.UpdateNominee;

@RestController
public class NomineeRestController {
	
	@Autowired
	private NewNominee newNominee;
	
	@Autowired
	private UpdateNominee updateNominee;
	
	@Autowired
	private SearchNominee searchNominee;
	
	@Autowired
	private LoadNominee loadNominee;
	
	@Autowired
	private DeleteNominee deleteNominee;

	@GetMapping("/nominees")
	List<Nominee> listAll() {
		return searchNominee.listAll();
	}
	
	@GetMapping("/nominees/{id}")
	Nominee get(@PathVariable Long id) {
	    return loadNominee.findById(id);
	}

	@PostMapping("/nominees")
	Nominee newNominee(@RequestBody Nominee nominee) {
		return newNominee.save(nominee);
	}
	
	@PutMapping("/nominees/{id}")
	Nominee update(@RequestBody Nominee nominee, @PathVariable Long id) {
		return updateNominee.update(nominee, id);
	}
	
	@PatchMapping("/nominees/{id}")
	Nominee patch(@RequestBody Nominee nominee, @PathVariable Long id) {
		return updateNominee.patch(nominee, id);
	}
	
	@DeleteMapping("/nominees/{id}")
	void delete(@PathVariable Long id) {
		deleteNominee.deleteById(id);
	}

}
