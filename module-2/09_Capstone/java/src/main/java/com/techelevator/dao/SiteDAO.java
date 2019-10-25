package com.techelevator.dao;

import java.util.ArrayList;

import com.techelevator.Reservation;
import com.techelevator.Site;

public interface SiteDAO {
	public Site getSiteBySiteId(Long siteId);
	public ArrayList<Site> getAllSitesByCampgroundId(Long campgroundId, Reservation reservation); // in order by site number ONLY AVAILABLE
	public ArrayList<Site> getAllSitesByParkId(Long parkId, Reservation reservation); // in order by campground and then by site number ONLY AVAILABLE
    

}
