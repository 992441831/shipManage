package com.ai.frame.export;

import java.sql.Date;

/**
 * Created by Administrator on 2018/8/11.
 */
public class Ship {
    int id;
    Date access_port_date;
    String name;
    Double length;
    Double width;
    Double tonnage;
    Double tonnage_net;
    Double tonnage_load;
    Date  anchor_date;
    String target_port;
    Date   weigh_date;
    int    anchor_days;
    String  telephone;
    String break_rules;
    //String nameAndDate;


    public Ship(int id, String name, Date access_port_date, Double length, Double width, Double tonnage, Double tonnage_net, Double tonnage_load, Date anchor_date, String target_port, Date weigh_date, int anchor_days, String telephone, String break_rules) {
        this.id = id;
        this.name = name;
        this.access_port_date = access_port_date;
        this.length = length;
        this.width = width;
        this.tonnage = tonnage;
        this.tonnage_net = tonnage_net;
        this.tonnage_load = tonnage_load;
        this.anchor_date = anchor_date;
        this.target_port = target_port;
        this.weigh_date = weigh_date;
        this.anchor_days = anchor_days;
        this.telephone = telephone;
        this.break_rules = break_rules;
    }

    public Ship() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getAccess_port_date() {
        return access_port_date;
    }

    public void setAccess_port_date(Date access_port_date) {
        this.access_port_date = access_port_date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public Double getTonnage() {
        return tonnage;
    }

    public void setTonnage(Double tonnage) {
        this.tonnage = tonnage;
    }

    public Date getAnchor_date() {
        return anchor_date;
    }

    public void setAnchor_date(Date anchor_date) {
        this.anchor_date = anchor_date;
    }

    public String getTarget_port() {
        return target_port;
    }

    public void setTarget_port(String target_port) {
        this.target_port = target_port;
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getBreak_rules() {
        return break_rules;
    }

    public void setBreak_rules(String break_rules) {
        this.break_rules = break_rules;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getTonnage_net() {
        return tonnage_net;
    }

    public void setTonnage_net(Double tonnage_net) {
        this.tonnage_net = tonnage_net;
    }

    public Double getTonnage_load() {
        return tonnage_load;
    }

    public void setTonnage_load(Double tonnage_load) {
        this.tonnage_load = tonnage_load;
    }

    @Override
    public String toString() {
        return "Ship{" +
                "id=" + id +
                ", access_port_date=" + access_port_date +
                ", name='" + name + '\'' +
                ", length=" + length +
                ", width=" + width +
                ", tonnage=" + tonnage +
                ", tonnage_net=" + tonnage_net +
                ", tonnage_load=" + tonnage_load +
                ", anchor_date=" + anchor_date +
                ", target_port='" + target_port + '\'' +
                ", weigh_date=" + weigh_date +
                ", anchor_days=" + anchor_days +
                ", telephone='" + telephone + '\'' +
                ", break_rules='" + break_rules + '\'' +
                '}';
    }
}
