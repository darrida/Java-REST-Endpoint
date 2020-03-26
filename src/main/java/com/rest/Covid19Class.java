package com.rest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

class Covid19Class {
    private int cases;
    private int deaths;
    private int recovered;
    private String updated;

    public Covid19Class(int cases, int deaths, int recovered, float updated) {
        this.cases = cases;
        this.deaths = deaths;
        this.recovered = recovered;
        this.updated = convertToDate(updated);
    }

    public Covid19Class(String cases, String deaths, String recovered, String updated) {
        this.cases = Integer.parseInt(cases);
        this.deaths = Integer.parseInt(deaths);
        this.recovered = Integer.parseInt(recovered);
        this.updated = updated;
    }

    private String convertToDate(float updated) {
        Date date = new Date((long) updated);
        //            SimpleDateFormat sdf = new SimpleDateFormat("MMMM d,yyyy h:mm a", Locale.ENGLISH);
        SimpleDateFormat sdf = new SimpleDateFormat("MM/d/yyyy h:mm a", Locale.ENGLISH);
        sdf.setTimeZone(TimeZone.getTimeZone("CDT"));
        String formattedDate = sdf.format(date);
        return formattedDate;
        //this.updated = formattedDate
    }

    // Getter Methods

    public int getCases() {
        return cases;
    }

    public int getDeaths() {
        return deaths;
    }

    public int getRecovered() {
        return recovered;
    }

    public String getUpdated() {
        return updated;
    }

    // Setter Methods

    public void setCases(int cases) { this.cases = cases; }

    public void setDeaths(int deaths) { this.deaths = deaths; }

    public void setRecovered(int recovered) { this.recovered = recovered; }

    public void setUpdated(float updated) { this.updated = convertToDate(updated); }
}