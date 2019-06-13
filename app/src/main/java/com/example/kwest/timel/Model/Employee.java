package com.example.kwest.timel.Model;

import java.util.ArrayList;
import java.util.List;

public class Employee {

    private ArrayList<TimeLog> timelog;
    private String str_Name;

    public String getStr_Name() {
        return str_Name;
    }

    public void setStr_Name(String str_Name) {
        this.str_Name = str_Name;
    }


    public List<TimeLog> getTimelog() {
        return timelog;
    }

    public void setTimelog(ArrayList<TimeLog> timelog) {
        this.timelog = timelog;
    }
}
