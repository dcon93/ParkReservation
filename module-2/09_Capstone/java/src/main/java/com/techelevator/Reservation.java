package com.techelevator;

import java.sql.Date;

public class Reservation {
	private Long reservationId;
	private Long siteId;
	private String name;
	private Date fromDate;
	private Date toDate;
	private Date createDate;
	
	public Reservation() {}
	
	public Reservation(Long reservationId, Long siteId, String name, Date fromDate, Date toDate, Date createDate) {
		this.reservationId = reservationId;
		this.siteId = siteId;
		this.name = name;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.createDate = createDate;
	}
	
	
	public Long getReservationId() {
		return reservationId;
	}
	public Long getSiteId() {
		return siteId;
	}
	public String getName() {
		return name;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public Date getCreateDate() {
		return createDate;
	}

	public void setReservationId(Long reservationId) {
		this.reservationId = reservationId;
	}

	public void setSiteId(Long siteId) {
		this.siteId = siteId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	

	
}