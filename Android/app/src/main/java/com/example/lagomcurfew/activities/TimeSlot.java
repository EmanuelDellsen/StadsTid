package com.example.lagomcurfew.activities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TimeSlot {

    private String date;
    private String month;
    private String year;
    private String day;
    private Integer formattedDate;
    private Date dateObj;
    private List<OneSlot> slots = new ArrayList<>();

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Integer getFormattedDate() {
        return formattedDate;
    }

    public void setFormattedDate(Integer formattedDate) {
        this.formattedDate = formattedDate;
    }

    public Date getDateObj() {
        return dateObj;
    }

    public void setDateObj(Date dateObj) {
        this.dateObj = dateObj;
    }

    public List<OneSlot> getSlots() {

        return slots;
    }

    public void addSlot(OneSlot slot) {

        slots.add(slot);
    }
}
