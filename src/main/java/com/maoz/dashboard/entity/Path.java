package com.maoz.dashboard.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PATHS")
public class Path {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private Double distance;
	private String type;
	private Long originId;
	private Long destId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getOriginId() {
		return originId;
	}

	public void setOriginId(Long originId) {
		this.originId = originId;
	}

	public Long getDestId() {
		return destId;
	}

	public void setDestId(Long destId) {
		this.destId = destId;
	}
}
