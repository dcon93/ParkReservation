package com.techelevator.dao;

import java.util.ArrayList;

import com.techelevator.Site;

public interface SiteDAO {
	public Site getSiteBySiteId(Long siteId);
	public ArrayList<Site> getAllSitesByCampgroundId(Long campgroundId); // in order by site number
	public ArrayList<Site> getAllSitesByParkId(Long parkId); // in order by campground and then by site number
    

}
