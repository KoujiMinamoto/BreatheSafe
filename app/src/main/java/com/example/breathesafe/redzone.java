package com.example.breathesafe;

public class redzone {

    private int Id;
    private String r_medication;
    private String r_dose;
    private String r_form;
    private String r_freq;
    private int WorkersId;

    public redzone(int id,String r_medication,String r_dose,String r_form,String r_freq,int WorkersId){

        this.Id = id;
        this.r_medication=r_medication;
        this.r_dose = r_dose;
        this.r_form = r_form;
        this.r_freq = r_freq;
        this.WorkersId = WorkersId;

    }

}