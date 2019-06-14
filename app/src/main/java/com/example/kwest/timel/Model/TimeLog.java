package com.example.kwest.timel.Model;

import com.google.gson.annotations.Expose;

import java.sql.Timestamp;

public class TimeLog {
//timestamp
    @Expose
    private Timestamp t_start;
    @Expose
    private Timestamp t_stop;

    public TimeLog(Timestamp t_start,Timestamp t_stop){
        this.t_start=t_start;
        this.t_stop=t_stop;
    }

 /*   public String getT_start() {
        return t_start;
    }

    public void setT_start(String t_start) {
        this.t_start = t_start;
    }

    public String getT_stop() {
        return t_stop;
    }

    public void setT_stop(String t_stop) {
        this.t_stop = t_stop;
    }

*/

    public Timestamp getT_start() {
        return t_start;
    }
    public void setT_start(Timestamp t_start) {
        this.t_start = t_start;
    }
    public Timestamp getT_stop() {
        return t_stop;
    }
    public void setT_stop(Timestamp t_stop) {
        this.t_stop = t_stop;
    }

}
