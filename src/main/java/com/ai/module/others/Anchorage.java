package com.ai.module.others;


import java.sql.Date;

/**
 * Created by Administrator on 2018/8/19.
 */
public class Anchorage {
    String name;
    Date anchor_date;
    Date weigh_date;
    int anchor_days;
    String nameAndDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getAnchor_date() {
        return anchor_date;
    }

    public void setAnchor_date(Date anchor_date) {
        this.anchor_date = anchor_date;
    }

    public Date getWeigh_date() {
        return weigh_date;
    }

    public void setWeigh_date(Date weigh_date) {
        this.weigh_date = weigh_date;
    }

    public int getAnchor_days() {
        return anchor_days;
    }

    public void setAnchor_days(int anchor_days) {
        this.anchor_days = anchor_days;
    }

    public String getNameAndDate() {
        return nameAndDate;
    }

    public void setNameAndDate(String nameAndDate) {
        this.nameAndDate = nameAndDate;
    }

    @Override
    public String toString() {
        return "Anchorage{" +
                "name='" + name + '\'' +
                ", anchor_date=" + anchor_date +
                ", weigh_date=" + weigh_date +
                ", anchor_days=" + anchor_days +
                ", nameAndDate='" + nameAndDate + '\'' +
                '}';
    }
}
