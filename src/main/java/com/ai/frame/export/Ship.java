package com.ai.frame.export;

import java.sql.Date;

/**
 * Created by Administrator on 2018/8/11.
 */
public class Ship {
    int id;
    Date create_date;
    String name;
    Double length;
    Double tonnage;
    Date  anchor_date;
    String target_port;
    Date   weigh_date;
    int    anchor_days;
    String  telephone;
    String break_rules;

    public Ship(int id, Date create_date, String name, Double length, Double tonnage, Date anchor_date, String target_port, Date weigh_date, int anchor_days, String telephone, String break_rules) {
        this.id = id;
        this.create_date = create_date;
        this.name = name;
        this.length = length;
        this.tonnage = tonnage;
        this.anchor_date = anchor_date;
        this.target_port = target_port;
        this.weigh_date = weigh_date;
        this.anchor_days = anchor_days;
        this.telephone = telephone;
        this.break_rules = break_rules;
    }

    public Ship() {

    }

    public String getName() {
        return name;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public int getId() {
        return id;
    }

    public Double getLength() {
        return length;
    }

    public Double getTonnage() {
        return tonnage;
    }

    public Date getAnchor_date() {
        return anchor_date;
    }

    public String getTarget_port() {
        return target_port;
    }

    public Date getWeigh_date() {
        return weigh_date;
    }

    public int getAnchor_days() {
        return anchor_days;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getBreak_rules() {
        return break_rules;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public void setTonnage(Double tonnage) {
        this.tonnage = tonnage;
    }

    public void setAnchor_date(Date anchor_date) {
        this.anchor_date = anchor_date;
    }

    public void setWeigh_date(Date weigh_date) {
        this.weigh_date = weigh_date;
    }

    public void setTarget_port(String target_port) {
        this.target_port = target_port;
    }

    public void setAnchor_days(int anchor_days) {
        this.anchor_days = anchor_days;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setBreak_rules(String break_rules) {
        this.break_rules = break_rules;
    }

    @Override
    public String toString() {
        return "Ship{" +
                "id=" + id +
                ", create_date=" + create_date +
                ", name='" + name + '\'' +
                ", length=" + length +
                ", tonnage=" + tonnage +
                ", anchor_date=" + anchor_date +
                ", target_port='" + target_port + '\'' +
                ", weigh_date=" + weigh_date +
                ", anchor_days=" + anchor_days +
                ", telephone='" + telephone + '\'' +
                ", break_rules='" + break_rules + '\'' +
                '}';
    }
}
