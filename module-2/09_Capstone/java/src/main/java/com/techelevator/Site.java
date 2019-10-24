package com.techelevator;

public class Site {
	private Long siteId;
	private Long campgroundId;
	private int siteNumber;
	private int maxOccupancy;
	private boolean accessible;
	private int maxRVLength;
	private boolean utilities;
	
	public Site() {}
	public Site(Long siteId, Long campgroundId, int siteNumber, int maxOccupancy, boolean accessible, int maxRVLength, boolean utilities) {
		this.siteId = siteId;
		this.campgroundId = campgroundId;
		this.siteNumber = siteNumber;
		this.maxOccupancy = maxOccupancy;
		this.accessible = accessible;
		this.maxRVLength = maxRVLength;
		this.utilities = utilities;
	}
	
	public Long getSiteId() {
		return siteId;
	}
	public Long getCampgroundId() {
		return campgroundId;
	}
	public int getSiteNumber() {
		return siteNumber;
	}
	public int getMaxOccupancy() {
		return maxOccupancy;
	}
	public boolean isAccessible() {
		return accessible;
	}
	public int getMaxRVLength() {
		return maxRVLength;
	}
	public boolean isUtilities() {
		return utilities;
	}
	public void setSiteId(Long siteId) {
		this.siteId = siteId;
	}
	public void setCampgroundId(Long campgroundId) {
		this.campgroundId = campgroundId;
	}
	public void setSiteNumber(int siteNumber) {
		this.siteNumber = siteNumber;
	}
	public void setMaxOccupancy(int maxOccupancy) {
		this.maxOccupancy = maxOccupancy;
	}
	public void setAccessible(boolean accessible) {
		this.accessible = accessible;
	}
	public void setMaxRVLength(int maxRVLength) {
		this.maxRVLength = maxRVLength;
	}
	public void setUtilities(boolean utilities) {
		this.utilities = utilities;
	}
	
	
	
	
	
}
