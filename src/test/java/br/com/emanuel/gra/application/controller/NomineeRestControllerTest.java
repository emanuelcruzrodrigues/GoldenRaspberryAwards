package br.com.emanuel.gra.application.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;

import br.com.emanuel.gra.domain.nominee.Nominee;
import br.com.emanuel.gra.domain.nominee.NomineeRepository;
import jakarta.transaction.Transactional;

@TestMethodOrder(OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class NomineeRestControllerTest {
	
	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@Autowired
	private NomineeRepository nomineeRepository;

	private Nominee newNominee;

	@Test
	@Order(1)
	void test_Get_List() {
		List<?> list = testRestTemplate.getForObject("/nominees", List.class);
		assertEquals(206, list.size(), 0);
	}
	
	@Test
	@Order(2)
	void test_Get() {
		
		Nominee nominee = testRestTemplate.getForObject("/nominees/1", Nominee.class);
		assertEquals("Can't Stop the Music", nominee.getTitle());
		assertEquals("Allan Carr", nominee.getProducers());
		assertEquals(1980, nominee.getYear());
		assertTrue(nominee.getWinner());
		
	}
	
	@Test
	@Transactional
	@Order(3)
	void test_Post() {
		
		Nominee nominee = new Nominee();
		nominee.setProducers("new producer, p2 and p3");
		nominee.setStudios("new studio");
		nominee.setTitle("new title");
		nominee.setYear(2022);
		nominee.setWinner(true);
		
		newNominee = testRestTemplate.postForObject("/nominees", nominee, Nominee.class);
		assertEquals(nominee.getTitle(), newNominee.getTitle());
		assertEquals(nominee.getProducers(), newNominee.getProducers());
		assertEquals(nominee.getYear(), newNominee.getYear());
		assertEquals(nominee.getWinner(), newNominee.getWinner());
		
		Nominee stored = nomineeRepository.load(newNominee.getId());
		assertEquals(3, stored.getWinners().size(), 0);
		assertEquals("new producer", stored.getWinners().get(0).getProducer());
		assertEquals("p2", stored.getWinners().get(1).getProducer());
		assertEquals("p3", stored.getWinners().get(2).getProducer());
		
	}
	
	@Test
	@Transactional
	@Order(4)
	void test_Put() {
		
		Nominee nominee = new Nominee();
		nominee.setProducers("p1");
		nominee.setStudios("s1");
		nominee.setTitle("t1");
		nominee.setYear(1900);
		nominee.setWinner(true);
		
		testRestTemplate.put(String.format("/nominees/%d", newNominee.getId()), nominee);
		
		Nominee stored = nomineeRepository.load(newNominee.getId());
		assertEquals(nominee.getTitle(), stored.getTitle());
		assertEquals(nominee.getProducers(), stored.getProducers());
		assertEquals(nominee.getYear(), stored.getYear());
		assertEquals(nominee.getWinner(), stored.getWinner());
		
		assertEquals(1, stored.getWinners().size(), 0);
		assertEquals("p1", stored.getWinners().get(0).getProducer());
		
	}
	
	@Test
	@Order(5)
	void test_Delete() {
		testRestTemplate.delete("/nominees/1");
		assertNull(nomineeRepository.load(1l));
	}

}
