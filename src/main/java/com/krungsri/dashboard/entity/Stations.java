package com.krungsri.dashboard.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

@Entity
@Table(name = "STATIONS")
@ApiObject
public class Stations {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@ApiObjectField
    private Long stationId;
	@ApiObjectField(description = "name of station")
    private String stationName;
	@ApiObjectField(description = "latitude of station")
    private BigDecimal stationLat;
	@ApiObjectField(description = "longitude of station")
	private BigDecimal stationLng;
	@ApiObjectField(description = "type of station")
	private String stationType;
	@ApiObjectField(description = "initial price of station")
	private BigDecimal stationPrice;
	@ApiObjectField(description = "is extended station")
	private Long stationExtend;
    
	public Long getStationId() {
		return stationId;
	}

	public void setStationId(Long stationId) {
		this.stationId = stationId;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public BigDecimal getStationLat() {
		return stationLat;
	}

	public void setStationLat(BigDecimal stationLat) {
		this.stationLat = stationLat;
	}

	public BigDecimal getStationLng() {
		return stationLng;
	}

	public void setStationLng(BigDecimal stationLng) {
		this.stationLng = stationLng;
	}

	public String getStationType() {
		return stationType;
	}

	public void setStationType(String stationType) {
		this.stationType = stationType;
	}

	public BigDecimal getStationPrice() {
		return stationPrice;
	}

	public void setStationPrice(BigDecimal stationPrice) {
		this.stationPrice = stationPrice;
	}

	public Long getStationExtend() {
		return stationExtend;
	}

	public void setStationExtend(Long stationExtend) {
		this.stationExtend = stationExtend;
	}



	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("station[id=%d, name='%s', lat='%s' lng='%s' type='%s' price='%s' ext='%s']", 
							this.stationId, this.stationName, this.stationLat.toString(), this.stationLng.toString(), this.stationType, this.stationPrice.toString(), this.stationExtend.toString());
	}
}