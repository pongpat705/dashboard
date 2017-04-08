package com.maoz.dashboard.entity;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "STATIONS")
public class Station {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String name;
    private BigDecimal lat;
	private BigDecimal lng;
	private String type;
	private BigDecimal price;
	private Long extend;
	
	@OneToMany(mappedBy="origin", fetch=FetchType.LAZY)
	private List<Path> pathOrigin;
	
	@OneToMany(mappedBy="dest", fetch=FetchType.LAZY)
	private List<Path> pathDest;
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getLat() {
		return lat;
	}

	public void setLat(BigDecimal lat) {
		this.lat = lat;
	}

	public BigDecimal getLng() {
		return lng;
	}

	public void setLng(BigDecimal lng) {
		this.lng = lng;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Long getExtend() {
		return extend;
	}

	public void setExtend(Long extend) {
		this.extend = extend;
	}

	public List<Path> getPathOrigin() {
		return pathOrigin;
	}

	public void setPathOrigin(List<Path> pathOrigin) {
		this.pathOrigin = pathOrigin;
	}

	public List<Path> getPathDest() {
		return pathDest;
	}

	public void setPathDest(List<Path> pathDest) {
		this.pathDest = pathDest;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("station[id=%d, name='%s', lat='%s' lng='%s' type='%s' price='%s' ext='%s']", 
							this.id, this.name, this.lat.toString(), this.lng.toString(), this.type, this.price.toString(), this.extend.toString());
	}
}