package com.example.kwest.timel.Model;

import java.sql.Timestamp;

public class TimeLog {

    private Timestamp t_start;
    private Timestamp t_stop;

    public TimeLog(Timestamp t_start,Timestamp t_stop){
        this.t_start=t_start;
        this.t_stop=t_stop;
    }




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
