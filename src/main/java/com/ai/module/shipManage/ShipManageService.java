package com.ai.module.shipManage;

import com.ai.frame.export.Ship;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

}