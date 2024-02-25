package com.example.task;

public class Events {

    private String tname;
    private String tid;
    private String description;
    private String deadline;
    private String status;

    // Default constructor
    public Events() {
        // Default constructor is needed for JavaFX TableView
    }

    // Parameterized constructor
    public Events(String tname, String tid, String description, String deadline, String status) {
        this.tname = tname;
        this.tid = tid;
        this.description = description;
        this.deadline = deadline;
        this.status = status;
    }

    // Getters and Setters

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
