package com.example.gtrice.nytimessearch.Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by gtrice on 8/14/16.
 */
public class SearchSettings implements Serializable {
    public String getSortOrder() {
        return sortOrder;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    Date beginDate;
    String sortOrder;

    public SearchSettings(Date beginDate) {
        this.beginDate = beginDate;
    }

    public ArrayList<String> getNewsDesk() {
        return newsDesk;
    }

    ArrayList<String> newsDesk;
    public SearchSettings(String sortOrder, Date beginDate, ArrayList<String> newsDesk) {
        this.sortOrder = sortOrder;
        this.beginDate = beginDate;
        this.newsDesk = newsDesk;
    }
}
