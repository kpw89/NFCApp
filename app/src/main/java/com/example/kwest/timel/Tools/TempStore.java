package com.example.kwest.timel.Tools;

public class TempStore {

    private static TempStore tempStore=null;

    private TempStore(){}
    private String pwd;
    private String id;

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static TempStore getTempStore() {
        if (tempStore==null)
            tempStore = new TempStore();
        return tempStore;
    }

}
