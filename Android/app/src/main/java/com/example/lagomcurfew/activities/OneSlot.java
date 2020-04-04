package com.example.lagomcurfew.activities;

import java.util.Objects;

public class OneSlot {

    private Long startTime;
    private String date;

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OneSlot oneSlot = (OneSlot) o;
        return startTime.equals(oneSlot.startTime) &&
                date.equals(oneSlot.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startTime, date);
    }
}
