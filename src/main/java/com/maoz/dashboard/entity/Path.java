package com.maoz.dashboard.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PATHS")
public class Path {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private Double distance;
	private String type;
	
	@ManyToOne
	@JoinColumn(name = "origin_id", insertable = true, updatable = true)
	private Station origin;

	@ManyToOne
	@JoinColumn(name = "dest_id", insertable = true, updatable = true)
	private Station dest;

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

	public Station getOrigin() {
		return origin;
	}

	public void setOrigin(Station origin) {
		this.origin = origin;
	}

	public Station getDest() {
		return dest;
	}

	public void setDest(Station dest) {
		this.dest = dest;
	}
	
	
}
