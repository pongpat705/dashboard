package com.maoz.dashboard.bean;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.maoz.dashboard.entity.Path;
import com.maoz.dashboard.entity.Station;



public class ShortestPathBean {
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
	private Station origin;
	private Station destination;
	private int type;
	private Double totalDistance;
	private String summary;
	private List<Path> edges;
	private List<Station> nodes;
	private List<Long> directionList;
	private Map<Long, Map<Long, Double>> hueristic;
	
	public Station getOrigin() {
		return origin;
	}
	public void setOrigin(Station origin) {
		this.origin = origin;
	}
	public Station getDestination() {
		return destination;
	}
	public void setDestination(Station destination) {
		this.destination = destination;
	}
	public Double getTotalDistance() {
		return totalDistance;
	}
	public void setTotalDistance(Double totalDistance) {
		this.totalDistance = totalDistance;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public List<Long> getDirectionList() {
		return directionList;
	}
	public void setDirectionList(List<Long> directionList) {
		this.directionList = directionList;
	}
	public Map<Long, Map<Long, Double>> getHueristic() {
		return hueristic;
	}
	public void setHueristic(Map<Long, Map<Long, Double>> hueristic) {
		this.hueristic = hueristic;
	}
	public List<Path> getEdges() {
		return edges;
	}
	public void setEdges(List<Path> edges) {
		this.edges = edges;
	}
	public List<Station> getNodes() {
		return nodes;
	}
	public void setNodes(List<Station> nodes) {
		this.nodes = nodes;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
}
