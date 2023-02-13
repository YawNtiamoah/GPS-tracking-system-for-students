package com.yaw.finalproject;

public class tutReport {

    private   String Mon;
    private   String Tue;
    private   String Wed;
    private   String Thurs;
    private   String Fri;

    public tutReport(String mon, String tue, String wed, String thurs, String fri) {
        Mon = mon;
        Tue = tue;
        Wed = wed;
        Thurs = thurs;
        Fri = fri;
    }

    public String getMon() {
        return Mon;
    }

    public void setMon(String mon) {
        Mon = mon;
    }

    public String getTue() {
        return Tue;
    }

    public void setTue(String tue) {
        Tue = tue;
    }

    public String getWed() {
        return Wed;
    }

    public void setWed(String wed) {
        Wed = wed;
    }

    public String getThurs() {
        return Thurs;
    }

    public void setThurs(String thurs) {
        Thurs = thurs;
    }

    public String getFri() {
        return Fri;
    }

    public void setFri(String fri) {
        Fri = fri;
    }
}
