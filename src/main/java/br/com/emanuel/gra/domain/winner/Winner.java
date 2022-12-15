package br.com.emanuel.gra.domain.winner;

import br.com.emanuel.gra.domain.nominee.Nominee;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "WINNERS")
public class Winner implements Comparable<Winner> {
	
	@Id
    @GeneratedValue
    @Column(name="ID")
    private Long id;
	
	@Column(name="PRODUCER")
	private String producer;
	
	@Column(name="AWARD_YEAR")
	private Integer year;
	
	@ManyToOne
	@JoinColumn(name = "ID_NOMINEE")
	private Nominee nominee;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getProducer() {
		return producer;
	}
	public void setProducer(String producer) {
		this.producer = producer;
	}

	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	
	public Nominee getNominee() {
		return nominee;
	}
	public void setNominee(Nominee nominee) {
		this.nominee = nominee;
	}
	
	@Override
	public int compareTo(Winner o) {
		return getYear().compareTo(o.getYear());
	}
	
}
