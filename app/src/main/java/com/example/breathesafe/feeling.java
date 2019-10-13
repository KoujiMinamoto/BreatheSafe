package com.example.breathesafe;

public class feeling {

    private int Id;
    private String date;
    private String time;
    private String type;
    private int WorkersId;


    public feeling(int id,String date,String time,String type,int WorkersId){

        this.Id = id;
        this.date= date;
        this.time = time;
        this.type = type;
        this.WorkersId = WorkersId;

    }
}
