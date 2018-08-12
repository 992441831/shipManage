package com.ai.module.shipManage;

import com.ai.frame.export.ExportExcel;
import com.ai.frame.export.Ship;
import com.ai.frame.util.Config;
import com.ai.frame.util.GetParamUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
@Controller
@RequestMapping("/module/shipManage")
public class ShipManageController {
    @Resource
    private ShipManageService shipManageService;

    /**
     * 获取船舶信息列表
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/queryShip.do",method = RequestMethod.GET)
    @ResponseBody
    public  Object queryShip(HttpServletRequest request,
                                         HttpServletResponse response) throws Exception {
        response.setHeader("Access-Control-Allow-Origin", "*");//允许其它链接跨域访问
        // 取查询条件
        Map paramMap =  GetParamUtil.getRequestParamMap(request);
        Map reusltMap = new HashMap();
        //paramMap.put("pno",2);
        //paramMap.put("pageSize",5);
        try{
            reusltMap = shipManageService.queryShip(paramMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reusltMap;
    }

    /**
     * 根据船名获取船舶信息
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/queryShipByName.do",method = RequestMethod.GET)
    @ResponseBody
    public  Object queryShipByName(HttpServletRequest request,
                            HttpServletResponse response) throws Exception {
        response.setHeader("Access-Control-Allow-Origin", "*");//允许其它链接跨域访问
        Map reusltMap = new HashMap();
        // 取查询条件
        Map paramMap =  GetParamUtil.getRequestParamMap(request);
        try{
            reusltMap = shipManageService.queryShipByName(paramMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reusltMap;
    }

    /**
     * 导出船舶信息
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/dataExport.do",method = RequestMethod.GET)
    //@ResponseBody
    public void dataExport(HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {
        response.setHeader("Access-Control-Allow-Origin", "*");//允许其它链接跨域访问
        Config conf = Config.getInstance();

        Map reusltMap = new HashMap();
        Map listMap = new HashMap();
        ExportExcel<Ship> exp = new ExportExcel<Ship>();
        String[] headers =
                { "id", "创建日期", "船舶名称", "船舶长度", "吨位","抛锚日期","目标港口","起锚日期","停泊天数","电话","违章情况" };
        List<Ship> dataset = new ArrayList<Ship>();
        String fileName = "shipMessage.xls";
        String fileDir = conf.getProperty("fileDir");
        //String path = request.getSession().getServletContext().getRealPath(str);
        String path = fileDir+"//"+fileName;
        //String outputFileDir = "D:\\IO\\output\\c.xls";
        OutputStream out = new FileOutputStream(path);
        // 取查询条件
        Map paramMap =  GetParamUtil.getRequestParamMap(request);
        try{
            listMap = shipManageService.queryShip(paramMap);
            if(listMap.get("status").toString().equals("0")){
                List<Ship> list=  (List)listMap.get("list");
                for(Ship ship : list){
                    dataset.add(ship);
                }
                exp.exportExcel(headers, dataset, out);
                out.close();
                reusltMap.put("status", 0);
                reusltMap.put("msg", "下载成功");
            }else{

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        download(path,response);
        //return reusltMap;
    }

    //弹窗下载
    public void download(String path, HttpServletResponse response) {
        try {
            // path是指欲下载的文件的路径。
            File file = new File(path);
            // 取得文件名。
            String filename = file.getName();
            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename="
                    + new String(filename.getBytes()));
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(
                    response.getOutputStream());
            response.setContentType("application/vnd.ms-excel;charset=gb2312");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 数据库后台刷新数据
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/refreshData.do",method = RequestMethod.GET)
    @ResponseBody
    public  Object refreshData(HttpServletRequest request,
                             HttpServletResponse response) throws Exception {
        response.setHeader("Access-Control-Allow-Origin", "*");//允许其它链接跨域访问
        // 取查询条件
        Map paramMap =  GetParamUtil.getRequestParamMap(request);
        Map reusltMap = new HashMap();
        try{
            reusltMap = shipManageService.refreshData(paramMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reusltMap;
    }

}
