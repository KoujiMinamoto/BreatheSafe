package com.example.breathesafe;

public class notifications {

    private int Id;
    private String date;
    private String time;
    private String status;
    private int WorkersId;


    public notifications(int id, String date, String time, String status, int WorkersId) {

        this.Id = id;
        this.date = date;
        this.time = time;
        this.status = status;
        this.WorkersId = WorkersId;
    }
}
