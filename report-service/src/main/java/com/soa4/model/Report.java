package com.soa4.model;

public class Report {
    private int reporter_id;
    private int reported_id;
    private String report_topic;

    public int getReporter_id() {
        return reporter_id;
    }

    public void setReporter_id(int reporter_id) {
        this.reporter_id = reporter_id;
    }

    public int getReported_id() {
        return reported_id;
    }

    public void setReported_id(int reported_id) {
        this.reported_id = reported_id;
    }

    public String getReport_topic() {
        return report_topic;
    }

    public void setReport_topic(String report_topic) {
        this.report_topic = report_topic;
    }
}
