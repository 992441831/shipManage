package com.ai.module.shipManage;

import com.ai.frame.export.Ship;
import com.ai.module.others.Anchorage;

import java.sql.Date;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
public interface ShipManageMapper {
    /**
     * 获取船舶基本信息
     * @author yc
     * @since 2018/8/4
     * */
    public List<Ship> queryShip(Map paramMap);
    public int queryCount(Map paramMap);
    public void refreshData();

    public List<Map> queryMaxMinDate();
    public List<Date> queryEveryDate(String name);
    public void insertAnchorage(Anchorage an);

    public void insertAnchorageSource(Map paramMap);
    public void insertWharfsSource(Map paramMap);
    public void insertVesselsSource(Map paramMap);

}
