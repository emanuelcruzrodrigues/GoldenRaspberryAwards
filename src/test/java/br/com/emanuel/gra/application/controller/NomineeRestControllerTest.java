package br.com.emanuel.gra.application.controller;

import static org.junit.jupiter.api.Assertions.*;

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

@TestMethodOrder(OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class NomineeRestControllerTest {
	
	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@Autowired
	private NomineeRepository nomineeRepository;

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
	@Order(3)
	void test_Post() {
		
		Nominee nominee = new Nominee();
		nominee.setProducers("new producer");
		nominee.setStudios("new studio");
		nominee.setTitle("new title");
		nominee.setYear(2022);
		nominee.setWinner(true);
		
		Nominee result = testRestTemplate.postForObject("/nominees", nominee, Nominee.class);
		assertEquals(nominee.getTitle(), result.getTitle());
		assertEquals(nominee.getProducers(), result.getProducers());
		assertEquals(nominee.getYear(), result.getYear());
		assertEquals(nominee.getWinner(), result.getWinner());
		
	}
	
	@Test
	@Order(4)
	void test_Put() {
		
		Nominee nominee = new Nominee();
		nominee.setProducers("p1");
		nominee.setStudios("s1");
		nominee.setTitle("t1");
		nominee.setYear(1900);
		nominee.setWinner(true);
		
		testRestTemplate.put("/nominees/1", nominee);
		
		Nominee stored = nomineeRepository.load(1l);
		assertEquals(nominee.getTitle(), stored.getTitle());
		assertEquals(nominee.getProducers(), stored.getProducers());
		assertEquals(nominee.getYear(), stored.getYear());
		assertEquals(nominee.getWinner(), stored.getWinner());
		
	}
	
	@Test
	@Order(5)
	void test_Delete() {
		testRestTemplate.delete("/nominees/1");
		assertNull(nomineeRepository.load(1l));
	}

}
