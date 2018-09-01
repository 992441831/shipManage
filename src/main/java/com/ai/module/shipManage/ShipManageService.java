package com.ai.module.shipManage;

import com.ai.frame.export.Ship;
import com.ai.module.others.Anchorage;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

@SuppressWarnings("unchecked")
@Service
public class ShipManageService {
    @Resource
    private ShipManageMapper shipManageMapper;
    int status = 0;       //是否处于锚定状态，0为非锚定状态，1为锚定状态

    /**
     * 获取船舶基本信息列表
     * @author yc
     * @since 2018/8/4
     * 入参：pno      当前页码
     *       pageSize  每页显示记录条数
     * */
    public Map queryShip(Map paramMap) throws Exception {
        Map result = new HashMap();
        try{
            int count = shipManageMapper.queryCount(paramMap);
            //如果不传入页码和每页记录数，意味着不需要分页，也就没有总页数
            if(paramMap.get("pno")!=null&&paramMap.get("pageSize")!=null&&!paramMap.get("pno").equals("")&&!paramMap.get("pageSize").equals("")){
                int pno = Integer.valueOf(paramMap.get("pno").toString());    //当前页码
                int pageSize = Integer.valueOf(paramMap.get("pageSize").toString());//每页显示记录条数
                int offset = (pno-1)*pageSize;
                paramMap.put("offset",offset);
                paramMap.put("pageSize",pageSize);
                System.out.println("paramMap"+paramMap);
                int pageCount = new Double(Math.ceil((count/(pageSize*1.0)))).intValue();
                result.put("pageCount", pageCount);     //总页数
            }

            List<Ship> list = shipManageMapper.queryShip(paramMap);
            result.put("list", list);                //查询的船的信息列表

        }catch(Exception e) {
            e.printStackTrace();
            result.put("status", -1);
            result.put("msg", "程序异常:查询失败error:"+e.getMessage());
        }

        result.put("status", 0);
        result.put("msg", "查询成功");
        return result;
    }

    /**
     * 数据库后台刷新数据
     * @author yc
     * @since 2018/8/4
     * */
    public Map refreshData(Map paramMap) throws Exception{
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

    public void getWeighDate() throws Exception{
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
            Calendar c3 = Calendar.getInstance(); //获取当前日期
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
            an.setName(name);
            //抛锚日期
            an.setAnchor_date(new java.sql.Date(min.getTime()));
            for(int i=0;i<dayOffset;i++){
                c2.setTime(mao_date.get(j));
                //System.out.println("c1.compareTo(c2):"+c1.compareTo(c2));
                if(c1.compareTo(c2)==0){
                    j++;
                    k++;
                    l=0;
                    if(k==2){
                        //抛锚日期
                        c2.add(Calendar.DAY_OF_MONTH, -1);
                        an.setAnchor_date(new java.sql.Date(c2.getTime().getTime()));
                        c2.add(Calendar.DAY_OF_MONTH, 1);
                    }
                }else{
                    l++;
                    if(l==1){
                        c2.setTime(mao_date.get(j-1));
                        //设置对象属性，并插入数据
                        setAnObj(an,c2.getTime(),k,name);
                        c2.setTime(mao_date.get(j));
                    }
                    k=0;
                }
                //System.out.println("i,j,k,l:"+i+j+k+l);
                c1.add(Calendar.DAY_OF_MONTH, 1);//最小日期
                //对数据的最后一天进行处理,如果最后一天不是今天，那么最后一天这天肯定起锚了
                //&&max.compareTo()
                if(i==dayOffset-1&&calcDayOffset(max,c3.getTime())!=0){
                    //设置对象属性，并插入数据
                    setAnObj(an,max,k,name);
                }
                //如果最后一天等于今天，那么这艘船处于锚定状态，将起锚日期设置为无限大，方便查询
                if(i==dayOffset-1&&calcDayOffset(max,c3.getTime())==0){
                    status = 1;   //设置为锚定状态
                    //设置对象属性，并插入数据
                    setAnObj(an,max,k,name);
                    status = 0;
                }
            }
        }
    }

    //设置对象属性，并插入数据
    public void setAnObj(Anchorage an,Date date,int k,String name) throws Exception{
        if(k==1){
            //如果只连续一次就中断了，抛锚日期就和起锚日期是同一天
            //或者是今天刚抛锚还没起锚，抛锚日期等于记录日期的最后一天
            //抛锚日期
            an.setAnchor_date(new java.sql.Date(date.getTime()));
        }
        if(status == 1){
            //锚定状态，起锚日期不确定，为了方便查询被设置为无限大
            String dateStr = "2118-1-1";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                date = sdf.parse(dateStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            //起锚日期
            an.setWeigh_date(new java.sql.Date(date.getTime()));
        }else{
            //起锚日期
            an.setWeigh_date(new java.sql.Date(date.getTime()));
        }
        an.setAnchor_days(k);
        an.setNameAndDate(name+an.getAnchor_date());
        insertAn(an);
    }

    //插入处理后的数据到数据库
    public void insertAn(Anchorage an) throws Exception{
        shipManageMapper.insertAnchorage(an);
    }

    //插入原始到数据库
    public void insertAnchorageSource(Map param) throws Exception{
        shipManageMapper.insertAnchorageSource(param);
    }

    //插入原始到数据库
    public void insertWharfsSource(Map param) throws Exception{
        shipManageMapper.insertWharfsSource(param);
    }
    //插入原始到数据库
    public void insertVesselsSource(Map param) throws Exception{
        shipManageMapper.insertVesselsSource(param);
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