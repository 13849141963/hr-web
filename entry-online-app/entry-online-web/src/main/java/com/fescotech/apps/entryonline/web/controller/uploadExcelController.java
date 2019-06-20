package com.fescotech.apps.entryonline.web.controller;

import com.alibaba.fastjson.JSON;
import com.fescotech.apps.entryonline.util.Result;
import com.fescotech.apps.entryonline.web.util.CommonDictUtils;
import com.fescotech.common.util.ApiUtilOpenPlateform;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.ErrorConstants;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cy on 2018/7/2.
 */
@Controller
@EnableAsync
public class uploadExcelController {
    @Async
    public void uploadExcel(List<String> listid,List<HSSFWorkbook> listwb) throws Exception {
        List<Map<String,String>> maps =  new ArrayList<>();
        for (int i =0;i<listid.size();i++){
            byte[] bytes = listwb.get(i).getBytes();
            File file = new File("s");
            try {
                FileOutputStream output = new FileOutputStream(file);

                BufferedOutputStream bufferedOutput = new BufferedOutputStream(output);

                bufferedOutput.write(bytes);
            }catch (Exception e){
                e.printStackTrace();
            }
            //将file上传获取fileId
            String fileId = null;
            Map<String,String> map = new HashMap();
            map.put("empTaskId",listid.get(i));
            map.put("materialType",fileId);
            map.put("fileId  ","307");
            maps.add(map);
        }

        String s = JSON.toJSONString(maps);

        String method = "olentry.batchSaveMaterialId";
        String resultStr = ApiUtilOpenPlateform.callOpenPlateformApi(method, s);
    }
}
