package br.com.emanuel.gra.domain.nominee;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class NomineeTest {

	@Test
	void test_Get_Winners_With_No_Winner() {
		Nominee nominee = new Nominee();
		nominee.setProducers("Sean S. Cunningham");
		nominee.refreshWinners();
		
		assertEquals(0, nominee.getWinners().size(), 0);
	}
	
	@Test
	void test_Get_Winners_With_1_Winner() {
		Nominee nominee = new Nominee();
		nominee.setProducers("Sean S. Cunningham");
		nominee.setWinner(true);
		nominee.refreshWinners();
		
		assertEquals(1, nominee.getWinners().size(), 0);
		assertEquals("Sean S. Cunningham", nominee.getWinners().get(0).getProducer());
	}
	
	@Test
	void test_Get_Winners_With_2_Winners() {
		Nominee nominee = new Nominee();
		nominee.setProducers("Adam Sandler and Rob Schneider");
		nominee.setWinner(true);
		nominee.refreshWinners();
		
		assertEquals(2, nominee.getWinners().size(), 0);
		assertEquals("Adam Sandler", nominee.getWinners().get(0).getProducer());
		assertEquals("Rob Schneider", nominee.getWinners().get(1).getProducer());
	}
	
	@Test
	void test_Get_Winners_With_3_Winners() {
		Nominee nominee = new Nominee();
		nominee.setProducers("Kevin Costner, Lawrence Kasdan and Jim Wilson");
		nominee.setWinner(true);
		nominee.refreshWinners();
		
		assertEquals(3, nominee.getWinners().size(), 0);
		assertEquals("Kevin Costner", nominee.getWinners().get(0).getProducer());
		assertEquals("Lawrence Kasdan", nominee.getWinners().get(1).getProducer());
		assertEquals("Jim Wilson", nominee.getWinners().get(2).getProducer());
	}
	
	@Test
	void test_Get_Winners_With_4_Winners() {
		Nominee nominee = new Nominee();
		nominee.setProducers("Kevin Costner, John Davis, Charles Gordon and Lawrence Gordon");
		nominee.setWinner(true);
		nominee.refreshWinners();
		
		assertEquals(4, nominee.getWinners().size(), 0);
		assertEquals("Kevin Costner", nominee.getWinners().get(0).getProducer());
		assertEquals("John Davis", nominee.getWinners().get(1).getProducer());
		assertEquals("Charles Gordon", nominee.getWinners().get(2).getProducer());
		assertEquals("Lawrence Gordon", nominee.getWinners().get(3).getProducer());
	}

}
