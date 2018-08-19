package com.ai.module.shipManage;


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
