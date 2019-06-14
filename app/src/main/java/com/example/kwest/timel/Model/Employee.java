package com.example.kwest.timel.Model;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;

public class Employee {


  //  private ArrayList<TimeLog> timelog;
  @Expose
  private String str_Name;
    @Expose
    private TimeLogString timeLogString;


    public Employee(TimeLogString timeLog, String str_Names){
        this.timeLogString=timeLog;
        this.str_Name=str_Names;
    }

    public TimeLogString getTimeLogString() {
        return timeLogString;
    }

    public void setTimeLogString(TimeLogString timeLogString) {
        this.timeLogString = timeLogString;
    }

    public String getStr_Name() {
        return str_Name;
    }

    public void setStr_Name(String str_Name) {
        this.str_Name = str_Name;
    }
}
