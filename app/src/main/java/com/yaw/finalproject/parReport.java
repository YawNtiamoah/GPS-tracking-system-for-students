package com.yaw.finalproject;

public class parReport {

    private String Name;
    private String Ward;
    private String Report;

    public parReport(String name, String ward, String report) {
        Name = name;
        Ward = ward;
        Report = report;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getWard() {
        return Ward;
    }

    public void setWard(String ward) {
        Ward = ward;
    }

    public String getReport() {
        return Report;
    }

    public void setReport(String report) {
        Report = report;
    }
}
