package com.example.kwest.timel.Model;

import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;

public class Employee {


  //  private ArrayList<TimeLog> timelog;
    private TimeLog timeLog;
    private String str_Name;

    public Employee(TimeLog timeLog, String str_Names){
        this.timeLog=timeLog;
        this.str_Name=str_Names;
    }


    public TimeLog getTimeLog() {
        return timeLog;
    }

    public void setTimeLog(TimeLog timeLog) {
        this.timeLog = timeLog;
    }

    public  Employee(String str_Name){
        this.timeLog=timeLog;
        this.str_Name = str_Name;
    }

    public String getStr_Name() {
        return str_Name;
    }

    public void setStr_Name(String str_Name) {
        this.str_Name = str_Name;
    }


   // public List<TimeLog> getTimelog() {
  //      return timelog;
  //  }

  /*  public void setTimelog(ArrayList<TimeLog> timelog) {
        this.timelog = timelog;
    }*/
}
