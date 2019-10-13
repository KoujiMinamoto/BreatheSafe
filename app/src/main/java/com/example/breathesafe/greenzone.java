package com.example.breathesafe;

public class greenzone{


    private int Id;
    private String c_medication;
    private String c_dose;
    private String c_form;
    private String c_freq;
    private String r_medication;
    private String r_dose;
    private String r_form;
    private String r_freq;
    private int WorkersId;

    public greenzone(int id,String c_medication,String c_dose,String c_form,String c_freq,String r_medication,String r_dose,String r_form,String r_freq,int workid){

        this.Id = id;
        this.c_medication=c_medication;
        this.c_dose = c_dose;
        this.c_form = c_form;
        this.c_freq = c_freq;
        this.r_medication=r_medication;
        this.r_dose = r_dose;
        this.r_form = r_form;
        this.r_freq = r_freq;
        this.WorkersId = workid;

    }

}