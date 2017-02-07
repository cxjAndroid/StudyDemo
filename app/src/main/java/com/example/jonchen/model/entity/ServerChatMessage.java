package com.example.jonchen.model.entity;

/**
 * Created by chenxujun on 16-10-31.
 */

public class ServerChatMessage {

    /**
     * imei : 867874020413514
     * timen : 1477815318057
     * rownumber : null
     * duration : 2
     * url : http://120.25.225.5:8090/kmhc-modem-restful/services/voice\download\867874020413514\1477815318057
     * src : 0
     * state : 0
     * account :
     * createdate : 1477815318100
     */

    private String imei;
    private long timen;
    private Object rownumber;
    private int duration;
    private String url;
    private int src;
    private int state;
    private String account;
    private long createdate;

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public long getTimen() {
        return timen;
    }

    public void setTimen(long timen) {
        this.timen = timen;
    }

    public Object getRownumber() {
        return rownumber;
    }

    public void setRownumber(Object rownumber) {
        this.rownumber = rownumber;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getSrc() {
        return src;
    }

    public void setSrc(int src) {
        this.src = src;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public long getCreatedate() {
        return createdate;
    }

    public void setCreatedate(long createdate) {
        this.createdate = createdate;
    }
}
