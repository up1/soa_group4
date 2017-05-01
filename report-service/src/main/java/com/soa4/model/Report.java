package com.soa4.model;

/**
 * Created by Boeing on 4/28/2017.
 */
public class Report {
    private int bannerID;
    private int bannedAccountID;
    private String description;

    public int getBannerID() {
        return bannerID;
    }

    public void setBannerID(int bannerID) {
        this.bannerID = bannerID;
    }

    public int getBannedAccountID() {
        return bannedAccountID;
    }

    public void setBannedAccountID(int bannedAccountID) {
        this.bannedAccountID = bannedAccountID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
