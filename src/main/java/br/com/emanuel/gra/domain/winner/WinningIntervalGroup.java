package br.com.emanuel.gra.domain.winner;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class WinningIntervalGroup {
	
	private final List<WinningInterval> intervals;
	
	private final List<WinningInterval> minimumIntervals;
	
	private final List<WinningInterval> maximumIntervals;
	
	public WinningIntervalGroup(List<WinningInterval> intervals) {
		this();
		this.intervals.addAll(intervals);
		
		
		Map<Integer, List<WinningInterval>> winningPerInterval = intervals.stream().collect(Collectors.groupingBy(i -> i.getInterval()));
		
		Integer minimalInterval = winningPerInterval.keySet().stream().min(Comparator.naturalOrder()).orElse(-1);
		if (minimalInterval > 0) {
			minimumIntervals.addAll(winningPerInterval.get(minimalInterval));
		}
		
		Integer maximumInterval = winningPerInterval.keySet().stream().max(Comparator.naturalOrder()).orElse(-1);
		if (maximumInterval > 0) {
			maximumIntervals.addAll(winningPerInterval.get(maximumInterval));
		}
	}

	public WinningIntervalGroup() {
		super();
		this.intervals = new ArrayList<WinningInterval>();
		this.minimumIntervals = new ArrayList<WinningInterval>();
		this.maximumIntervals = new ArrayList<WinningInterval>();
	}

	@JsonIgnore
	public List<WinningInterval> getIntervals() {
		return intervals;
	}

	@JsonProperty("min")
	public List<WinningInterval> getMinimumIntervals() {
		return minimumIntervals;
	}

	@JsonProperty("max")
	public List<WinningInterval> getMaximumIntervals() {
		return maximumIntervals;
	}
	
	

}
