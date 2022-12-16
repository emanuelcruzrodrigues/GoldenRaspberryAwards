package br.com.emanuel.gra.domain.nominee;

public class WinningInterval {

	private String producer;
	private Integer interval;
	private Integer previousWin;
	private Integer followingWin;
	
	public String getProducer() {
		return producer;
	}
	public void setProducer(String producer) {
		this.producer = producer;
	}
	
	public Integer getInterval() {
		return interval;
	}
	public void setInterval(Integer interval) {
		this.interval = interval;
	}
	
	public Integer getPreviousWin() {
		return previousWin;
	}
	public void setPreviousWin(Integer previousWin) {
		this.previousWin = previousWin;
		calculateInterval();
	}
	
	public Integer getFollowingWin() {
		return followingWin;
	}
	public void setFollowingWin(Integer followingWin) {
		this.followingWin = followingWin;
		calculateInterval();
	}
	
	private void calculateInterval() {
		if (previousWin == null || followingWin == null) {
			setInterval(null);
		} else {
			setInterval(followingWin -previousWin);
		}
	}
	
}
