package com.ai.module.shipManage;

import com.ai.frame.export.Ship;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.sql.*;
import java.util.*;
import java.util.Date;

@SuppressWarnings("unchecked")
@Service
public class ShipManageService {
    @Resource
    private ShipManageMapper shipManageMapper;

    /**
     * 获取船舶基本信息列表
     * @author yc
     * @since 2018/8/4
     * 入参：pno      当前页码
     *       pageSize  每页显示记录条数
     * */
    public Map queryShip(Map paramMap) {
        Map result = new HashMap();
        int pno = Integer.valueOf(paramMap.get("pno").toString());    //当前页码
        int pageSize = Integer.valueOf(paramMap.get("pageSize").toString());//每页显示记录条数
        int offset = (pno-1)*pageSize;
        paramMap.put("offset",offset);
        paramMap.put("pageSize",pageSize);
        try{
            List<Ship> list = shipManageMapper.queryShip(paramMap);
            int count = shipManageMapper.queryCount(paramMap);
            int pageCount = new Double(Math.ceil((count/(pageSize*1.0)))).intValue();

            result.put("list", list);                //查询的船的信息列表
            result.put("pageCount", pageCount);     //总页数

            //前端要求，将参数回传，为了偷懒，也是醉了
            result.put("pno", pno);
            result.put("pageSize", pageSize);

            result.put("status", 0);
            result.put("msg", "查询成功");
        }catch(Exception e) {
            e.printStackTrace();
            result.put("status", -1);
            result.put("msg", "程序异常:查询失败error:"+e.getMessage());
        }
        return result;
    }

    /**
     * 数据库后台刷新数据
     * @author yc
     * @since 2018/8/4
     * */
    public Map refreshData(Map paramMap) {
        Map result = new HashMap();

        try{
            getWeighDate();  //得到抛锚和起锚日期及锚定天数并插入到表tab_anchorage中
            shipManageMapper.refreshData();
            result.put("status", 0);
            result.put("msg", "刷新成功");
        }catch(Exception e) {
            e.printStackTrace();
            result.put("status", 1);
            result.put("msg", "程序异常:查询失败error:"+e.getMessage());
        }
        return result;
    }

    public void getWeighDate(){
        List buffer = new ArrayList();
        //根据业务逻辑找到抛锚和起锚日期
        //连续的日期被认为是一段锚定日期
        //如果日期中有中断就认为是多段锚定
        List<Map> dateList = shipManageMapper.queryMaxMinDate();
        String name = null;
        Date min ;
        Date max ;
        int dayOffset;
        List< java.sql.Date> mao_date;
        for(Map da:dateList){
            Calendar c1=Calendar.getInstance();
            Calendar c2=Calendar.getInstance();
            name = (String)da.get("name");
            mao_date = (List< java.sql.Date >)shipManageMapper.queryEveryDate(name);
            min = (java.sql.Date) da.get("min");
            c1.setTime(min);
            max = (java.sql.Date) da.get("max");
            dayOffset = calcDayOffset(min, max)+1;
            System.out.println(name+"  -  "+dayOffset);
            int j=0;        //最小日期肯定相等
            int k=0;        //用于判断锚定日期是否连续
            int l=0;        //用于判断中断日期是否连续
            Anchorage an = new Anchorage();
            an.name = name;
            //抛锚日期
            an.anchor_date = new java.sql.Date(min.getTime());
            for(int i=0;i<dayOffset;i++){
                c2.setTime(mao_date.get(j));
                System.out.println("c1.compareTo(c2):"+c1.compareTo(c2));
                if(c1.compareTo(c2)==0){
                    j++;
                    k++;
                    l=0;
                    if(k==2){
                        //抛锚日期
                        c2.add(Calendar.DAY_OF_MONTH, -1);
                        an.anchor_date = new java.sql.Date(c2.getTime().getTime());
                        c2.add(Calendar.DAY_OF_MONTH, 1);
                    }
                }else{
                    l++;
                    if(l==1){
                        c2.setTime(mao_date.get(j-1));
                        //设置对象属性
                        setAnObj(an,c2.getTime(),k,name);
                        c2.setTime(mao_date.get(j));
                    }
                    k=0;
                }
                System.out.println("i,j,k,l:"+i+j+k+l);
                c1.add(Calendar.DAY_OF_MONTH, 1);//最小日期
                //对最后一批数据进行处理
                if(i==dayOffset-1){
                    //设置对象属性
                    setAnObj(an,max,k,name);
                }
            }
        }
    }

    //设置对象属性
    public void setAnObj(Anchorage an,Date date,int k,String name){
        if(k==1){
            //如果只连续一次就中断了，抛锚日期就和起锚日期是同一天
            //抛锚日期
            an.anchor_date = new java.sql.Date(date.getTime());
        }
        //起锚日期
        an.weigh_date = new java.sql.Date(date.getTime());
        an.anchor_days = k;
        an.nameAndDate = name+an.weigh_date;
        insertAn(an);
    }

    //插入处理后的数据到数据库
    public void insertAn(Anchorage an){
        shipManageMapper.insertAnchorage(an);
        System.out.println(an.toString());
    }


    public static int calcDayOffset(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1 = cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if (year1 != year2) {  //同一年
            int timeDistance = 0;
            for (int i = year1; i < year2; i++) {
                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) {  //闰年
                    timeDistance += 366;
                } else {  //不是闰年

                    timeDistance += 365;
                }
            }
            return timeDistance + (day2 - day1);
        } else { //不同年
            return day2 - day1;
        }
    }

}