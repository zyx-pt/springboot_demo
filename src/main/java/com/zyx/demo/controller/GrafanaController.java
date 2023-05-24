package com.zyx.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zyx.demo.vo.ColUnit;
import com.zyx.demo.vo.DataFormat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 *
 * 官方实现
 * https://github.com/bergquist/fake-simple-json-datasource/blob/master/index.js
 */
@RestController
@Slf4j
public class GrafanaController {

    /**
     * should return 200 ok. Used for "Test connection" on the datasource config page.
     * @return
     */
    @GetMapping(value = "/")
    public String testConnect() {
        log.info("检查服务成功");
        return "success";
    }

    /**
     * should return annotations.
     * @param request
     * @return
     */
    @PostMapping(value = "/annotations")
    public String annotations(@RequestBody JSONObject request) {
        log.info("调用annotations接口:{}", JSON.toJSONString(request));
        return "[{annotation:annotation,time:time,title:title,tags:tags,text:text}]";
    }

    /**
     * used by the find metric options on the query tab in panels.
     * @param request
     * @return
     */
    @PostMapping(value = "/search")
    public String search(@RequestBody JSONObject request) {
        log.info("调用search接口:{}", JSON.toJSONString(request));
        return "[\"upper_25\",\"upper_50\",\"upper_75\",\"upper_90\",\"upper_95\"]";
    }


    /**
     * should return metrics based on input.
     * @param request
     * @return
     */
    @PostMapping(value = "/query")
    public String query(@RequestBody JSONObject request) {
        log.info("调用query接口:{}", JSON.toJSONString(request));
        JSONArray targets = request.getJSONArray("targets");
        // 第一个对象
        JSONObject firstTarget = targets.getJSONObject(0);
        // 第一个对象的第一层字段
        String target = firstTarget.getString("target"); // upper_50
        JSONObject additionalData = firstTarget.getJSONObject("data");
        // 第一个对象的第一层字段additionalData详情
        String my_type = additionalData.getString("my_type");
        String pt_date = additionalData.getString("pt_date");
        String orgnization_id = additionalData.getString("orgnization_id");
        log.info("target:{},orgnization_id:{},pt_date:{},my_type:{}",target,orgnization_id,pt_date,my_type);
        ArrayList<DataFormat> data = new ArrayList<DataFormat>();
        ArrayList<ColUnit> columns = new ArrayList<>();
        columns.add(new ColUnit("登记门店数(个)"));
        columns.add(new ColUnit("活跃门店数(个)"));
        columns.add(new ColUnit("新增门店数(个)"));
        ArrayList<ArrayList<String>> rows = new ArrayList<>();
        // 第一行
        ArrayList<String> row = new ArrayList<String>();
        rows.add(row);
        if("20210827".equals(pt_date)){
            row.add("100");
            row.add("200");
            row.add("300");
        }else{
            row.add("200");
            row.add("400");
            row.add("600");
        }
        DataFormat dataFormat =  new DataFormat();
        dataFormat.setType("table");
        dataFormat.setRows(rows);
        dataFormat.setColumns(columns);
        data.add(dataFormat);
        log.info(JSON.toJSONString(data));
        return JSON.toJSONString(data);
    }
}
