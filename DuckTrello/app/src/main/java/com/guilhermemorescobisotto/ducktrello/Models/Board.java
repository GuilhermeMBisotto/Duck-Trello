package com.guilhermemorescobisotto.ducktrello.Models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by ranzi on 19/05/16.
 */
public class Board extends AppModel {

    private String id;
    private String name;
    private String desc;
    private boolean closed;
    private String idOrganization;
    private boolean pinned;
    private String url;
    private Map<String, String> labelNames;
    private boolean invited;
    private List<String> invitations;
    // private List<Membership> memberships;
    private String shortUrl;
    private boolean subscribed;
    // private Prefs prefs;
    private Date dateLastActivity;
    private Date dateLastView;
    private String shortLink;
    private List<String> powerUps;
    //private List<TList> lists  = new ArrayList<>();


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public String getIdOrganization() {
        return idOrganization;
    }

    public void setIdOrganization(String idOrganization) {
        this.idOrganization = idOrganization;
    }

    public boolean isPinned() {
        return pinned;
    }

    public void setPinned(boolean pinned) {
        this.pinned = pinned;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, String> getLabelNames() {
        return labelNames;
    }

    public void setLabelNames(Map<String, String> labelNames) {
        this.labelNames = labelNames;
    }

    public boolean isInvited() {
        return invited;
    }

    public void setInvited(boolean invited) {
        this.invited = invited;
    }

    public List<String> getInvitations() {
        return invitations;
    }

    public void setInvitations(List<String> invitations) {
        this.invitations = invitations;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public boolean isSubscribed() {
        return subscribed;
    }

    public void setSubscribed(boolean subscribed) {
        this.subscribed = subscribed;
    }

    public Date getDateLastActivity() {
        return dateLastActivity;
    }

    public void setDateLastActivity(Date dateLastActivity) {
        this.dateLastActivity = dateLastActivity;
    }

    public Date getDateLastView() {
        return dateLastView;
    }

    public void setDateLastView(Date dateLastView) {
        this.dateLastView = dateLastView;
    }

    public String getShortLink() {
        return shortLink;
    }

    public void setShortLink(String shortLink) {
        this.shortLink = shortLink;
    }

    public List<String> getPowerUps() {
        return powerUps;
    }

    public void setPowerUps(List<String> powerUps) {
        this.powerUps = powerUps;
    }
}
