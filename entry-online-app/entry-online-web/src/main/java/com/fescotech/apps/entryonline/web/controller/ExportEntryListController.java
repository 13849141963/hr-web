package com.fescotech.apps.entryonline.web.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.fescotech.apps.entryonline.entity.R;
import com.fescotech.apps.entryonline.web.exception.NullDataException;
import com.fescotech.apps.entryonline.web.util.CommonDictUtils;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.fescotech.apps.entryonline.dto.EntryConditionExportDto;
import com.fescotech.apps.entryonline.dto.EntrySituationHRDtoReq;
import com.fescotech.apps.entryonline.util.Result;
import com.fescotech.common.util.ApiUtilOpenPlateform;


@Controller
@RequestMapping
public class ExportEntryListController {
    @RequestMapping(value = "/entryOnline/ExportEntryList")
    public String partExport(HttpServletResponse response, @RequestParam(value = "empTaskIds", required = false) Long[] empTaskIds) {

        OutputStream output = null;
        EntrySituationHRDtoReq entrySituationDto = new EntrySituationHRDtoReq();
        try {
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet("new sheet");
            sheet.setDefaultColumnWidth(20); // 默认列宽

            HSSFFont font = wb.createFont();
            font.setFontName("黑体");
            font.setFontHeightInPoints((short) 13);// 设置字体大小
            font.setColor(HSSFColor.WHITE.index); //字体颜色

            HSSFCellStyle headStyle = wb.createCellStyle(); // 头部样式
            headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平居中
            headStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
            headStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
            headStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
            headStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
            headStyle.setFillForegroundColor(HSSFColor.TEAL.index);// 设置背景色
            headStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            headStyle.setFont(font);// 选择需要用到的字体格式

            HSSFCellStyle contentStyle = wb.createCellStyle(); // 内容样式
            contentStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
            contentStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
            contentStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
            contentStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框

            //员工数据下载导出
            HSSFRow row_h = sheet.createRow((short) 0);
            HSSFCell ch = row_h.createCell(0);
            ch.setCellValue("员工数据");
            ch.setCellStyle(headStyle);

            HSSFRow row1 = sheet.createRow((short) 1);
            HSSFCell cell_1_0 = row1.createCell(0);
            cell_1_0.setCellValue("姓名");
            cell_1_0.setCellStyle(headStyle);


            HSSFCell cell_1_1 = row1.createCell(1);
            cell_1_1.setCellValue("身份证号");
            cell_1_1.setCellStyle(headStyle);

            HSSFCell cell_1_2 = row1.createCell(2);
            cell_1_2.setCellValue("手机号");
            cell_1_2.setCellStyle(headStyle);

            HSSFCell cell_1_3 = row1.createCell(3);
            cell_1_3.setCellValue("品牌");
            cell_1_3.setCellStyle(headStyle);

            HSSFCell cell_1_4 = row1.createCell(4);
            cell_1_4.setCellValue("所属店名");
            cell_1_4.setCellStyle(headStyle);

            HSSFCell cell_1_5 = row1.createCell(5);
            cell_1_5.setCellValue("店长手机号");
            cell_1_5.setCellStyle(headStyle);

            HSSFCell cell_1_6 = row1.createCell(6);
            cell_1_6.setCellValue("职务");
            cell_1_6.setCellStyle(headStyle);

            HSSFCell cell_1_7 = row1.createCell(7);
            cell_1_7.setCellValue("用工类型");
            cell_1_7.setCellStyle(headStyle);

            HSSFCell cell_1_8 = row1.createCell(8);
            cell_1_8.setCellValue("人员类型");
            cell_1_8.setCellStyle(headStyle);

            HSSFCell cell_1_9 = row1.createCell(9);
            cell_1_9.setCellValue("户口本本人职业");
            cell_1_9.setCellStyle(headStyle);

            HSSFCell cell_1_10 = row1.createCell(10);
            cell_1_10.setCellValue("社保类型");
            cell_1_10.setCellStyle(headStyle);

            HSSFCell cell_1_11 = row1.createCell(11);
            cell_1_11.setCellValue("缴费人员类别");
            cell_1_11.setCellStyle(headStyle);

            HSSFCell cell_1_12 = row1.createCell(12);
            cell_1_12.setCellValue("是否需要转入变更");
            cell_1_12.setCellStyle(headStyle);

            HSSFCell cell_1_13 = row1.createCell(13);
            cell_1_13.setCellValue("是否缴纳公积金");
            cell_1_13.setCellStyle(headStyle);

            HSSFCell cell_1_14 = row1.createCell(14);
            cell_1_14.setCellValue("入职日期");
            cell_1_14.setCellStyle(headStyle);

            HSSFCell cell_1_15 = row1.createCell(15);
            cell_1_15.setCellValue("已预派业务客户");
            cell_1_15.setCellStyle(headStyle);

            HSSFCell cell_1_16 = row1.createCell(16);
            cell_1_16.setCellValue("入职办理总进度");
            cell_1_16.setCellStyle(headStyle);

            HSSFCell cell_1_17 = row1.createCell(17);
            cell_1_17.setCellValue("入职登记进度");
            cell_1_17.setCellStyle(headStyle);

            HSSFCell cell_1_18 = row1.createCell(18);
            cell_1_18.setCellValue("社保手续进度");
            cell_1_18.setCellStyle(headStyle);

            HSSFCell cell_1_19 = row1.createCell(19);
            cell_1_19.setCellValue("档案手续进度");
            cell_1_19.setCellStyle(headStyle);

            HSSFCell cell_1_20 = row1.createCell(20);
            cell_1_20.setCellValue("入职申请确认");
            cell_1_20.setCellStyle(headStyle);

            //内容
            String method = "olentry.queryExpEntryList";

            Map<String, Object> queryParam = new HashMap<>();
            //queryParam.put("busiCustNos", new String[] { "cp001", "cp002","cp003" });
            String[] busiCustIds = CommonDictUtils.getRemoveDuplicateCustomerIds();
            queryParam.put("busiCustNos", busiCustIds);
            queryParam.put("empTaskIds", empTaskIds);

			
			/*entrySituationDto.setBusiCustNos(new String[]{"cp001","cp002","cp003"});
			entrySituationDto.setEmpTaskIds(empTaskIds);*/
            //中台接口
            String resultStr = ApiUtilOpenPlateform.callOpenPlateformApi(method, JSON.toJSONString(queryParam));

            Result result = JSON.parseObject(resultStr, Result.class);
            Object successResult = result.getSuccessResult();
            String s1 = JSON.toJSONString(successResult);
            Map map = JSON.parseObject(s1, Map.class);
            Object pageRecords = map.get("pageRecords");
            String s2 = JSON.toJSONString(pageRecords);
            List<EntryConditionExportDto> entryConditionExport = JSON.parseArray(s2, EntryConditionExportDto.class);

            for (int i = 0; i < entryConditionExport.size(); i++) {
                HSSFRow row2 = sheet.createRow(i + 2);
                //员工姓名
                HSSFCell cell_2_0 = row2.createCell(0);
                cell_2_0.setCellValue(entryConditionExport.get(i).getEmpName());
                cell_2_0.setCellStyle(contentStyle);

                //身份证号
                HSSFCell cell_2_1 = row2.createCell(1);
                cell_2_1.setCellValue(entryConditionExport.get(i).getIdCode());
                cell_2_1.setCellStyle(contentStyle);

                //手机号
                HSSFCell cell_2_2 = row2.createCell(2);
                cell_2_2.setCellValue(entryConditionExport.get(i).getMobile());
                cell_2_2.setCellStyle(contentStyle);

                //业务客户编号(品牌号)
                HSSFCell cell_2_3 = row2.createCell(3);
                cell_2_3.setCellValue(entryConditionExport.get(i).getCompanyName());
                cell_2_3.setCellStyle(contentStyle);

                //所属店名
                HSSFCell cell_2_4 = row2.createCell(4);
                cell_2_4.setCellValue(entryConditionExport.get(i).getDepName());
                cell_2_4.setCellStyle(contentStyle);

                //店长手机号
                HSSFCell cell_2_5 = row2.createCell(5);
                cell_2_5.setCellValue(entryConditionExport.get(i).getMgrMobile());
                cell_2_5.setCellStyle(contentStyle);

                //职务
                HSSFCell cell_2_6 = row2.createCell(6);
                cell_2_6.setCellValue(entryConditionExport.get(i).getPostName());
                cell_2_6.setCellStyle(contentStyle);

                //用工类型
                HSSFCell cell_2_7 = row2.createCell(7);
                cell_2_7.setCellValue(entryConditionExport.get(i).getJobTypeName());
                cell_2_7.setCellStyle(contentStyle);

                //人员类型
                HSSFCell cell_2_8 = row2.createCell(8);
                cell_2_8.setCellValue(entryConditionExport.get(i).getEmpTypeName());
                cell_2_8.setCellStyle(contentStyle);

                //户口本本人职业(粮农)
                HSSFCell cell_2_9 = row2.createCell(9);
                cell_2_9.setCellValue(entryConditionExport.get(i).getIsFarmerName());
                cell_2_9.setCellStyle(contentStyle);

                //社保类型
                HSSFCell cell_2_10 = row2.createCell(10);
                cell_2_10.setCellValue(entryConditionExport.get(i).getInsAddTypeName());
                cell_2_10.setCellStyle(contentStyle);

                //缴费人员类别
                HSSFCell cell_2_11 = row2.createCell(11);
                cell_2_11.setCellValue(entryConditionExport.get(i).getFeePersonTypeName());
                cell_2_11.setCellStyle(contentStyle);

                //是否需要转入变更
                HSSFCell cell_2_12 = row2.createCell(12);
                cell_2_12.setCellValue(entryConditionExport.get(i).getIntoChangeFlagName());
                cell_2_12.setCellStyle(contentStyle);

                //是否缴纳公积金
                HSSFCell cell_2_13 = row2.createCell(13);
                cell_2_13.setCellValue(entryConditionExport.get(i).getIsNeedInsuranceName());
                cell_2_13.setCellStyle(contentStyle);

                //入职日期
                HSSFCell cell_2_14 = row2.createCell(14);
                cell_2_14.setCellValue(entryConditionExport.get(i).getEntryTime());
                cell_2_14.setCellStyle(contentStyle);


                //已预排业务客户
                HSSFCell cell_2_15 = row2.createCell(15);
                cell_2_15.setCellValue(entryConditionExport.get(i).getOrderCompany());
                cell_2_15.setCellStyle(contentStyle);


                //入职办理总进度
                HSSFCell cell_2_16 = row2.createCell(16);
                cell_2_16.setCellValue(entryConditionExport.get(i).getTaskStatusName());
                cell_2_16.setCellStyle(contentStyle);

                //入职登记进度
                HSSFCell cell_2_17 = row2.createCell(17);
                cell_2_17.setCellValue(entryConditionExport.get(i).getEnrollStatusName());
                cell_2_17.setCellStyle(contentStyle);

                //社保手续进度
                HSSFCell cell_2_18 = row2.createCell(18);
                cell_2_18.setCellValue(entryConditionExport.get(i).getInsStatusName());
                cell_2_18.setCellStyle(contentStyle);

                //档案手续进度
                HSSFCell cell_2_19 = row2.createCell(19);
                cell_2_19.setCellValue(entryConditionExport.get(i).getPersonnelStatusName());
                cell_2_19.setCellStyle(contentStyle);

                //入职确认状态
                HSSFCell cell_2_20 = row2.createCell(20);
                cell_2_20.setCellValue(entryConditionExport.get(i).getConfirmStatusName());
                cell_2_20.setCellStyle(contentStyle);

            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String date = sdf.format(new Date());
            String name = URLEncoder.encode("员工入职情况查询", "utf-8");
            String filename = name + date;
            output = response.getOutputStream();
            response.reset();
            response.setHeader("Content-disposition", "attachment; filename=" + filename + ".xls");
            response.setContentType("application/msexcel");
            wb.write(output);

        } catch (NullDataException e) {
            try {
                response.reset();
                response.setStatus(555);
                response.setContentType("application/text");
                String data = "未查询到可供下载数据";
                output = response.getOutputStream();
                output.write(data.getBytes("UTF-8"));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
	
