package com.example.breathesafe;

public class User {

    private int Id;
    private String first_name;
    private String last_name;
    private String email;
    private String ec_name;
    private String ec_workloc;
    private int organisationId;
    private int departmentId;

    public User(int id,String fname,String lname,String email,String ecname,String ecworlc,int oid,int did){

        this.Id = id;
        this.first_name=fname;
        this.last_name = lname;
        this.email = email;
        this.ec_name = ecname;
        this.ec_workloc =ecworlc;
        this.organisationId = oid;
        this.departmentId = did;



    }


}
