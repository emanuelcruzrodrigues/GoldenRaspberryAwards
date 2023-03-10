package br.com.emanuel.gra.domain.nominee;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "NOMINEES")
public class Nominee {
	
	@Id
    @GeneratedValue
    @Column(name="ID")
    private Long id;
    
	@Column(name="TITLE")
	private String title;
	
	@Column(name="AWARD_YEAR")
	private Integer year;

	@Column(name="STUDIOS")
	private String studios;
	
	@Column(name="PRODUCERS")
	private String producers;
	
	@Column(name="WINNER")
	private Boolean winner;
	
	@JsonIgnore
	@OneToMany(mappedBy = "nominee", targetEntity = Winner.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Winner> winners;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}

	public String getStudios() {
		return studios;
	}
	public void setStudios(String studios) {
		this.studios = studios;
	}

	public String getProducers() {
		return producers;
	}
	public void setProducers(String producers) {
		this.producers = producers;
	}

	public Boolean getWinner() {
		return winner;
	}
	public void setWinner(Boolean winner) {
		this.winner = winner;
	}
	
	public void update(Nominee source) {
		setProducers(source.getProducers());
		setStudios(source.getStudios());
		setTitle(source.getTitle());
		setWinner(source.getWinner());
		setYear(source.getYear());
	}
	
	public void updateNotNull(Nominee source) {
		Optional.ofNullable(source.getProducers()).ifPresent(p -> setProducers(p));
		Optional.ofNullable(source.getStudios()).ifPresent(p -> setStudios(p));
		Optional.ofNullable(source.getTitle()).ifPresent(p -> setTitle(p));
		Optional.ofNullable(source.getWinner()).ifPresent(p -> setWinner(p));
		Optional.ofNullable(source.getYear()).ifPresent(p -> setYear(p));
	}
	
	public List<Winner> getWinners() {
		return winners;
	}
	public void setWinners(List<Winner> winners) {
		this.winners = winners;
	}
	/**
	 * @see NomineeTest
	 */
	
	public void refreshWinners() {
		
		List<Winner> result = new ArrayList<>();
		
		if (getWinner() != null && getWinner()) {
			String[] p1 = getProducers().split(" and ");
			for (String string : p1) {
				String[] producers = string.split(",");
				for (String producer : producers) {
					Winner winner = new Winner();
					winner.setProducer(producer.trim());
					winner.setNominee(this);
					winner.setYear(getYear());
					result.add(winner);
				}
			}
		}
		
		if (getWinners() == null) {
			setWinners(new ArrayList<>());
		} else {
			getWinners().clear();
		}
		
		getWinners().addAll(result);
	}
	
	
}
