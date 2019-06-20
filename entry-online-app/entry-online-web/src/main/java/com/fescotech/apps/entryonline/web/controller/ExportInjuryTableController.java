package com.fescotech.apps.entryonline.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.fescotech.apps.entryonline.dto.EntrySituationDtoResp;
import com.fescotech.apps.entryonline.dto.EntrySituationHRDtoReq;
import com.fescotech.apps.entryonline.dto.EntrySituationHRDtoResp;
import com.fescotech.apps.entryonline.dto.PrintJobNewInsDtoResp;
import com.fescotech.apps.entryonline.entity.R;
import com.fescotech.apps.entryonline.util.Result;
import com.fescotech.apps.entryonline.web.exception.NullDataException;
import com.fescotech.apps.entryonline.web.util.CommonDictUtils;
import com.fescotech.apps.entryonline.web.util.DownloadFileUtils;
import com.fescotech.common.util.ApiUtilOpenPlateform;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by cy on 2018/6/14.
 */

@Controller
@RequestMapping
public class ExportInjuryTableController {


    @RequestMapping(value = "/entryOnline/confirmPaperMaterial")
    @ResponseBody
    public R confirmPaperMaterial(@RequestParam(value = "empTaskIds", required = false) Long[] empTaskIds) throws Exception {
        Map<String, Object> map = new HashMap<>();
        try {
            String[] busiCustIds = CommonDictUtils.getRemoveDuplicateCustomerIds();
            map.put("empTaskIds", empTaskIds);
            map.put("busiCustNos", busiCustIds);
            String s = JSON.toJSONString(map);

            String method = "olentry.confirmPaperMaterial";
            String resultStr = ApiUtilOpenPlateform.callOpenPlateformApi(method, s);
        }catch (Exception e){
            e.printStackTrace();
            return R.error().put("errorMsg",e.getMessage());
        }
        return R.ok();
    }


    @RequestMapping(value = "/entryOnline/batchPrintJobNewIns")
    @ResponseBody
    public void partExport(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "empTaskIds", required = false) Long[] empTaskIds) throws Exception {
        Map<String, Object> map = new HashMap<>();
        String[] busiCustIds = CommonDictUtils.getRemoveDuplicateCustomerIds();
        map.put("empTaskIds",empTaskIds);
        map.put("busiCustNos",busiCustIds);
        String s = JSON.toJSONString(map);

        String method = "olentry.batchPrintJobNewIns";
        String resultStr = ApiUtilOpenPlateform.callOpenPlateformApi(method, s);

        Result result = JSON.parseObject(resultStr, Result.class);
        Object successResult = result.getSuccessResult();
        String s1 = JSON.toJSONString(successResult);
        Map map1 = JSON.parseObject(s1, Map.class);
        Object pageRecords = map1.get("pageRecords");
        String s2 = JSON.toJSONString(pageRecords);
        List<PrintJobNewInsDtoResp> list = JSON.parseArray(s2, PrintJobNewInsDtoResp.class);

        List<HSSFWorkbook> wbs = getPersonInjuryTable(list);
        HSSFWorkbook wb = getBatchInjuryTable(list);
        wbs.add(wb);

        DownloadFileUtils.downloadHSSFWorkbook(request,response,wbs);

    }

    private HSSFWorkbook getBatchInjuryTable(List<PrintJobNewInsDtoResp> list) {

        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("北京市社会保险参保人员增加表");
        sheet.setDefaultColumnWidth(20); // 默认列宽

        HSSFPrintSetup ps = sheet.getPrintSetup();
        ps.setVResolution((short)600);
        ps.setLandscape(true); // 打印方向，true：横向，false：纵向(默认)
        ps.setPaperSize(HSSFPrintSetup.A4_PAPERSIZE);

        //调整列宽
        sheet.setColumnWidth(0,5* 256);
        sheet.setColumnWidth(1,9* 256);
        sheet.setColumnWidth(2,7* 256);
        sheet.setColumnWidth(3,20* 256);
        sheet.setColumnWidth(4,13* 256);
        sheet.setColumnWidth(5,7* 256);
        sheet.setColumnWidth(6,7* 256);
        sheet.setColumnWidth(7,7* 256);
        sheet.setColumnWidth(8,7* 256);
        sheet.setColumnWidth(9,7* 256);
        sheet.setColumnWidth(10,11* 256);
        sheet.setColumnWidth(11,9* 256);
        sheet.setColumnWidth(12,9* 256);
        sheet.setColumnWidth(13,11* 256);

        //sheet.setColumnWidth((short) 0, (short) 250);
        //10号字体
        HSSFFont font10 = wb.createFont();
        font10.setFontName("黑体");
        font10.setFontHeightInPoints((short) 10);// 设置字体大小
        font10.setColor(HSSFColor.BLACK.index); //字体颜色

        //20号字体红色
        HSSFFont font20 = wb.createFont();
        font20.setFontHeightInPoints((short) 20);// 设置字体大小
        font20.setFontHeightInPoints(HSSFColor.RED.index);

        HSSFCellStyle headStyle = wb.createCellStyle(); // 头部样式
        headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平居中
        //headStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
        //headStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
        //headStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
        //headStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
        //headStyle.setFillForegroundColor(HSSFColor.TEAL.index);// 设置背景色
        //headStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        headStyle.setFont(font10);// 选择需要用到的字体格式

        HSSFCellStyle contentStyle = wb.createCellStyle(); // 内容样式
        //contentStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
        //contentStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
        //contentStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
        //contentStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
        //sheet.addMergedRegion(new CellRangeAddress(int firstRow, int lastRow, int firstCol, int lastCol)

        HSSFCellStyle contentStyleAll = wb.createCellStyle(); // 内容样式
        contentStyleAll.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平居中
        contentStyleAll.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
        contentStyleAll.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
        contentStyleAll.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
        contentStyleAll.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
        contentStyleAll.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
        contentStyleAll.setWrapText(true);//自动换行

        List<List<PrintJobNewInsDtoResp>> listPrintJob = groupListByQuantity(list, 12);

        for (int i = 0; i < listPrintJob.size(); i++) {
            int firstData = i * 21;

            //合并单元格
            sheet.addMergedRegion(new CellRangeAddress(firstData, firstData, 0, 13));
            sheet.addMergedRegion(new CellRangeAddress(firstData + 1, firstData + 1, 0, 13));
            sheet.addMergedRegion(new CellRangeAddress(firstData + 2, firstData + 2, 0, 13));
            sheet.addMergedRegion(new CellRangeAddress(firstData + 3, firstData + 3, 0, 13));
            sheet.addMergedRegion(new CellRangeAddress(firstData + 4, firstData + 4, 5, 9));
            sheet.addMergedRegion(new CellRangeAddress(firstData + 4, firstData + 4, 10, 11));

            sheet.addMergedRegion(new CellRangeAddress(firstData + 4, firstData + 5, 0, 0));
            sheet.addMergedRegion(new CellRangeAddress(firstData + 4, firstData + 5, 1, 1));
            sheet.addMergedRegion(new CellRangeAddress(firstData + 4, firstData + 5, 2, 2));
            sheet.addMergedRegion(new CellRangeAddress(firstData + 4, firstData + 5, 3, 3));
            sheet.addMergedRegion(new CellRangeAddress(firstData + 4, firstData + 5, 4, 4));
            sheet.addMergedRegion(new CellRangeAddress(firstData + 4, firstData + 5, 12, 12));
            sheet.addMergedRegion(new CellRangeAddress(firstData + 4, firstData + 5, 13, 13));

            sheet.addMergedRegion(new CellRangeAddress(firstData + 18, firstData + 18, 0, 13));
            sheet.addMergedRegion(new CellRangeAddress(firstData + 19, firstData + 19, 0, 13));
            sheet.addMergedRegion(new CellRangeAddress(firstData + 20, firstData + 20, 0, 13));

            //第一行
            HSSFRow row = sheet.createRow(firstData);
            HSSFCell cell_1_1 = row.createCell(0);
            cell_1_1.setCellValue("北京市社会保险参保人员增加表");
            cell_1_1.setCellStyle(headStyle);

            //第二行
            HSSFRow row2 = sheet.createRow(firstData+1);
            HSSFCell cell_2_1 = row2.createCell(0);
            cell_2_1.setCellValue("填报单位(盖章) : "+list.get(0).getCompanyName());
            cell_2_1.setCellStyle(contentStyle);

            //第三行
            HSSFRow row3 = sheet.createRow(firstData+2);
            HSSFCell cell_3_1 = row3.createCell(0);
            cell_3_1.setCellValue("统一社会信用代码(组织机构代码) : "+getSocialCreditCodeByCompanyName(list.get(0).getCompanyName()));
            cell_3_1.setCellStyle(contentStyle);

            //第四行
            HSSFRow row4 = sheet.createRow(firstData+3);
            HSSFCell cell_4_1 = row4.createCell(0);
            cell_4_1.setCellValue("社会保险登记证编码 : "+getSocialSecurityCodeByCompanyName(list.get(0).getCompanyName()));
            cell_4_1.setCellStyle(contentStyle);

            //第五行
            HSSFRow row5 = sheet.createRow(firstData+4);
            HSSFCell cell_5_1 = row5.createCell(0);
            cell_5_1.setCellValue("序号");
            cell_5_1.setCellStyle(contentStyleAll);

            HSSFCell cell_5_2 = row5.createCell(1);
            cell_5_2.setCellValue("姓名");
            cell_5_2.setCellStyle(contentStyleAll);

            HSSFCell cell_5_3 = row5.createCell(2);
            cell_5_3.setCellValue("性别");
            cell_5_3.setCellStyle(contentStyleAll);

            HSSFCell cell_5_4 = row5.createCell(3);
            cell_5_4.setCellValue("*公民身份号码");
            cell_5_4.setCellStyle(contentStyleAll);

            HSSFCell cell_5_5 = row5.createCell(4);
            cell_5_5.setCellValue("*缴费人员类别");
            cell_5_5.setCellStyle(contentStyleAll);

            HSSFCell cell_5_6 = row5.createCell(5);
            cell_5_6.setCellValue("*参加险种");
            cell_5_6.setCellStyle(contentStyleAll);

            HSSFCell cell_5_7 = row5.createCell(6);
            cell_5_7.setCellStyle(contentStyleAll);

            HSSFCell cell_5_8 = row5.createCell(7);
            cell_5_8.setCellStyle(contentStyleAll);

            HSSFCell cell_5_9 = row5.createCell(8);
            cell_5_9.setCellStyle(contentStyleAll);

            HSSFCell cell_5_10 = row5.createCell(9);
            cell_5_10.setCellStyle(contentStyleAll);

            HSSFCell cell_5_11 = row5.createCell(10);
            cell_5_11.setCellValue("*个人缴费（恢复）原因");
            cell_5_11.setCellStyle(contentStyleAll);

            HSSFCell cell_5_12 = row5.createCell(11);
            cell_5_12.setCellStyle(contentStyleAll);

            HSSFCell cell_5_13 = row5.createCell(12);
            cell_5_13.setCellValue("*申报月工资收入\\档次(元");
            cell_5_13.setCellStyle(contentStyleAll);

            HSSFCell cell_5_14 = row5.createCell(13);
            cell_5_14.setCellValue("*增加日期");
            cell_5_14.setCellStyle(contentStyleAll);

            //第六行
            HSSFRow row6 = sheet.createRow(firstData+5);

            HSSFCell cell_6_1 = row6.createCell(0);
            cell_6_1.setCellStyle(contentStyleAll);

            HSSFCell cell_6_2 = row6.createCell(1);
            cell_6_2.setCellStyle(contentStyleAll);

            HSSFCell cell_6_3 = row6.createCell(2);
            cell_6_3.setCellStyle(contentStyleAll);

            HSSFCell cell_6_4 = row6.createCell(3);
            cell_6_4.setCellStyle(contentStyleAll);

            HSSFCell cell_6_5 = row6.createCell(4);
            cell_6_5.setCellStyle(contentStyleAll);

            HSSFCell cell_6_6 = row6.createCell(5);
            cell_6_6.setCellValue("养老");
            cell_6_6.setCellStyle(contentStyleAll);

            HSSFCell cell_6_7 = row6.createCell(6);
            cell_6_7.setCellValue("失业");
            cell_6_7.setCellStyle(contentStyleAll);

            HSSFCell cell_6_8 = row6.createCell(7);
            cell_6_8.setCellValue("工伤");
            cell_6_8.setCellStyle(contentStyleAll);

            HSSFCell cell_6_9 = row6.createCell(8);
            cell_6_9.setCellValue("生育");
            cell_6_9.setCellStyle(contentStyleAll);

            HSSFCell cell_6_10 = row6.createCell(9);
            cell_6_10.setCellValue("医疗");
            cell_6_10.setCellStyle(contentStyleAll);

            HSSFCell cell_6_11 = row6.createCell(10);
            cell_6_11.setCellValue("四险");
            cell_6_11.setCellStyle(contentStyleAll);

            HSSFCell cell_6_12 = row6.createCell(11);
            cell_6_12.setCellValue("医疗");
            cell_6_12.setCellStyle(contentStyleAll);

            HSSFCell cell_6_13 = row6.createCell(12);
            cell_6_13.setCellStyle(contentStyleAll);

            HSSFCell cell_6_14 = row6.createCell(13);
            cell_6_14.setCellStyle(contentStyleAll);

            //处理listPrintJob数据
            List<PrintJobNewInsDtoResp> printJobNewInsDtoResps = listPrintJob.get(i);
            for(int j = 0;j<12;j++){
                HSSFRow row7 = sheet.createRow(firstData+6+j);
                if(printJobNewInsDtoResps.size()>j){
                    PrintJobNewInsDtoResp printJobNewInsDtoResp = printJobNewInsDtoResps.get(j);

                    HSSFCell cell_7_1 = row7.createCell(0);
                    cell_7_1.setCellValue(j+1);
                    cell_7_1.setCellStyle(contentStyleAll);

                    HSSFCell cell_7_2 = row7.createCell(1);
                    cell_7_2.setCellValue(printJobNewInsDtoResp.getEmpName());
                    cell_7_2.setCellStyle(contentStyleAll);

                    HSSFCell cell_7_3 = row7.createCell(2);
                    cell_7_3.setCellValue(printJobNewInsDtoResp.getGender());
                    cell_7_3.setCellStyle(contentStyleAll);

                    HSSFCell cell_7_4 = row7.createCell(3);
                    cell_7_4.setCellValue(printJobNewInsDtoResp.getIdCode());
                    cell_7_4.setCellStyle(contentStyleAll);

                    HSSFCell cell_7_5 = row7.createCell(4);
                    cell_7_5.setCellValue(printJobNewInsDtoResp.getFeePersonType());
                    cell_7_5.setCellStyle(contentStyleAll);

                    HSSFCell cell_7_6 = row7.createCell(5);
                    cell_7_6.setCellValue("");
                    cell_7_6.setCellStyle(contentStyleAll);

                    HSSFCell cell_7_7 = row7.createCell(6);
                    cell_7_7.setCellValue("");
                    cell_7_7.setCellStyle(contentStyleAll);

                    HSSFCell cell_7_8 = row7.createCell(7);
                    cell_7_8.setCellValue("√");
                    cell_7_8.setCellStyle(contentStyleAll);

                    HSSFCell cell_7_9 = row7.createCell(8);
                    cell_7_9.setCellValue("");
                    cell_7_9.setCellStyle(contentStyleAll);

                    HSSFCell cell_7_10 = row7.createCell(9);
                    cell_7_10.setCellValue("");
                    cell_7_10.setCellStyle(contentStyleAll);

                    HSSFCell cell_7_11 = row7.createCell(10);
                    cell_7_11.setCellValue("其他新参统");
                    cell_7_11.setCellStyle(contentStyleAll);

                    HSSFCell cell_7_12 = row7.createCell(11);
                    cell_7_12.setCellStyle(contentStyleAll);

                    HSSFCell cell_7_13 = row7.createCell(12);
                    cell_7_13.setCellValue("4624");
                    cell_7_13.setCellStyle(contentStyleAll);

                    HSSFCell cell_7_14 = row7.createCell(13);
                    cell_7_14.setCellValue("2018/5/1");
                    cell_7_14.setCellStyle(contentStyleAll);

                }else {
                    HSSFCell cell_7_1 = row7.createCell(0);
                    cell_7_1.setCellValue(j+1);
                    cell_7_1.setCellStyle(contentStyleAll);

                    HSSFCell cell_7_2 = row7.createCell(1);
                    cell_7_2.setCellValue("");
                    cell_7_2.setCellStyle(contentStyleAll);

                    HSSFCell cell_7_3 = row7.createCell(2);
                    cell_7_3.setCellValue("");
                    cell_7_3.setCellStyle(contentStyleAll);

                    HSSFCell cell_7_4 = row7.createCell(3);
                    cell_7_4.setCellValue("");
                    cell_7_4.setCellStyle(contentStyleAll);

                    HSSFCell cell_7_5 = row7.createCell(4);
                    cell_7_5.setCellValue("");
                    cell_7_5.setCellStyle(contentStyleAll);

                    HSSFCell cell_7_6 = row7.createCell(5);
                    cell_7_6.setCellValue("");
                    cell_7_6.setCellStyle(contentStyleAll);

                    HSSFCell cell_7_7 = row7.createCell(6);
                    cell_7_7.setCellValue("");
                    cell_7_7.setCellStyle(contentStyleAll);

                    HSSFCell cell_7_8 = row7.createCell(7);
                    cell_7_8.setCellValue("");
                    cell_7_8.setCellStyle(contentStyleAll);

                    HSSFCell cell_7_9 = row7.createCell(8);
                    cell_7_9.setCellValue("");
                    cell_7_9.setCellStyle(contentStyleAll);

                    HSSFCell cell_7_10 = row7.createCell(9);
                    cell_7_10.setCellValue("");
                    cell_7_10.setCellStyle(contentStyleAll);

                    HSSFCell cell_7_11 = row7.createCell(10);
                    cell_7_11.setCellValue("");
                    cell_7_11.setCellStyle(contentStyleAll);

                    HSSFCell cell_7_12 = row7.createCell(11);
                    cell_7_12.setCellStyle(contentStyleAll);

                    HSSFCell cell_7_13 = row7.createCell(12);
                    cell_7_13.setCellValue("");
                    cell_7_13.setCellStyle(contentStyleAll);

                    HSSFCell cell_7_14 = row7.createCell(13);
                    cell_7_14.setCellValue("");
                    cell_7_14.setCellStyle(contentStyleAll);

                }
            }
            //第19行
            HSSFRow row19 = sheet.createRow(firstData+18);
            HSSFCell cell_19_0 = row19.createCell(0);
            cell_19_0.setCellValue("单位负责人:                                                           社保经（代）办机构经办人员（签章）:");
            cell_19_0.setCellStyle(contentStyle);

            //第20行
            HSSFRow row20 = sheet.createRow(firstData+19);
            HSSFCell cell_20_0 = row20.createCell(0);
            cell_20_0.setCellValue("单位经办人:                                                           社保经（代）办机构（盖章）:");
            cell_20_0.setCellStyle(contentStyle);

            //第21行
            HSSFRow row21 = sheet.createRow(firstData+20);
            HSSFCell cell_21_0 = row21.createCell(0);
            cell_21_0.setCellValue("填报日期:       年    月    日                                         办理日期:       年    月    日");
            cell_21_0.setCellStyle(contentStyle);

            sheet.setRowBreak(firstData+21);
        }
        return wb;
    }


    private List<HSSFWorkbook> getPersonInjuryTable(List<PrintJobNewInsDtoResp> list) {

        List<HSSFWorkbook> wbs = new ArrayList<>();

        for(int i=0;i<list.size();i++) {

            PrintJobNewInsDtoResp printJobNewInsDtoResp = list.get(i);

            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet(printJobNewInsDtoResp.getEmpName()+printJobNewInsDtoResp.getIdCode());
            sheet.setDefaultColumnWidth(20); // 默认列宽

            HSSFPrintSetup ps = sheet.getPrintSetup();
            ps.setLandscape(true); // 打印方向，true：横向，false：纵向(默认)
            ps.setVResolution((short)600);
            ps.setPaperSize(HSSFPrintSetup.A4_PAPERSIZE);

            //调整列宽
            sheet.setColumnWidth(0,26* 256);
            sheet.setColumnWidth(1,11* 256);
            sheet.setColumnWidth(2,20 * 256);
            sheet.setColumnWidth(3,20* 256);
            sheet.setColumnWidth(4,22* 256);
            sheet.setColumnWidth(5,22* 256);

            HSSFFont font = wb.createFont();
            font.setFontName("黑体");
            font.setFontHeightInPoints((short) 13);// 设置字体大小
            font.setColor(HSSFColor.BLACK.index); //字体颜色

            HSSFCellStyle headStyle = wb.createCellStyle(); // 头部样式
            headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平居中
            headStyle.setFont(font);// 选择需要用到的字体格式

            HSSFCellStyle contentStyle = wb.createCellStyle(); // 内容样式

            HSSFCellStyle contentStyleAll = wb.createCellStyle(); // 内容样式
            contentStyleAll.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平居中
            contentStyleAll.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
            contentStyleAll.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
            contentStyleAll.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
            contentStyleAll.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
            contentStyleAll.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
            contentStyleAll.setWrapText(true);//自动换行

            HSSFCellStyle contentStyleAllNoAlign = wb.createCellStyle(); // 内容样式
            contentStyleAllNoAlign.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
            contentStyleAllNoAlign.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
            contentStyleAllNoAlign.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
            contentStyleAllNoAlign.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
            contentStyleAllNoAlign.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
            contentStyleAllNoAlign.setWrapText(true);//自动换行

            //合并单元格
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 5));
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 5));
            sheet.addMergedRegion(new CellRangeAddress(2, 2, 0, 2));
            sheet.addMergedRegion(new CellRangeAddress(2, 2, 3, 5));
            sheet.addMergedRegion(new CellRangeAddress(3, 3, 1, 4));
            sheet.addMergedRegion(new CellRangeAddress(3, 7, 5, 5));
            sheet.addMergedRegion(new CellRangeAddress(4, 4, 3, 4));
            sheet.addMergedRegion(new CellRangeAddress(5, 5, 3, 4));
            sheet.addMergedRegion(new CellRangeAddress(6, 6, 3, 4));
            sheet.addMergedRegion(new CellRangeAddress(7, 7, 3, 4));
            sheet.addMergedRegion(new CellRangeAddress(8, 8, 1, 3));
            sheet.addMergedRegion(new CellRangeAddress(9, 9, 1, 3));
            sheet.addMergedRegion(new CellRangeAddress(10, 10, 1, 3));
            sheet.addMergedRegion(new CellRangeAddress(11, 11, 1, 3));
            sheet.addMergedRegion(new CellRangeAddress(14, 14, 1, 2));
            sheet.addMergedRegion(new CellRangeAddress(14, 14, 4, 5));
            sheet.addMergedRegion(new CellRangeAddress(15, 15, 1, 2));
            sheet.addMergedRegion(new CellRangeAddress(15, 15, 4, 5));
            sheet.addMergedRegion(new CellRangeAddress(16, 16, 1, 2));
            sheet.addMergedRegion(new CellRangeAddress(16, 16, 4, 5));
            sheet.addMergedRegion(new CellRangeAddress(17, 17, 1, 2));
            sheet.addMergedRegion(new CellRangeAddress(17, 17, 4, 5));
            sheet.addMergedRegion(new CellRangeAddress(18, 18, 1, 2));
            sheet.addMergedRegion(new CellRangeAddress(18, 18, 4, 5));
            sheet.addMergedRegion(new CellRangeAddress(19, 19, 1, 2));
            sheet.addMergedRegion(new CellRangeAddress(19, 19, 4, 5));
            sheet.addMergedRegion(new CellRangeAddress(20, 20, 1, 2));
            sheet.addMergedRegion(new CellRangeAddress(20, 20, 4, 5));
            sheet.addMergedRegion(new CellRangeAddress(21, 21, 0, 5));
            sheet.addMergedRegion(new CellRangeAddress(22, 22, 1, 2));
            sheet.addMergedRegion(new CellRangeAddress(22, 22, 4, 5));
            sheet.addMergedRegion(new CellRangeAddress(23, 23, 1, 2));
            sheet.addMergedRegion(new CellRangeAddress(23, 23, 4, 5));
            sheet.addMergedRegion(new CellRangeAddress(24, 24, 0, 5));
            sheet.addMergedRegion(new CellRangeAddress(25, 25, 0, 5));
            sheet.addMergedRegion(new CellRangeAddress(26, 26, 4, 5));
            sheet.addMergedRegion(new CellRangeAddress(27, 27, 4, 5));
            sheet.addMergedRegion(new CellRangeAddress(28, 28, 0, 5));

            //第一行
            HSSFRow row = sheet.createRow(0);
            HSSFCell cell_1_1 = row.createCell(0);
            cell_1_1.setCellValue("北京市社会保险个人信息登记表");
            cell_1_1.setCellStyle(headStyle);

            //第二行
            HSSFRow row2 = sheet.createRow(1);
            HSSFCell cell_2_1 = row2.createCell(0);
            cell_2_1.setCellValue("填报单位(公章) : "+printJobNewInsDtoResp.getCompanyName());
            cell_2_1.setCellStyle(contentStyle);

            //第三行
            HSSFRow row3 = sheet.createRow(2);
            HSSFCell cell_3_1 = row3.createCell(0);
            cell_3_1.setCellValue("组织机构代码 : "+getSocialCreditCodeByCompanyName(printJobNewInsDtoResp.getCompanyName()));
            cell_3_1.setCellStyle(contentStyle);

            //第四行(改后第三行)
            HSSFCell cell_4_1 = row3.createCell(3);
            cell_4_1.setCellValue("社会保险登记号 : "+getSocialSecurityCodeByCompanyName(printJobNewInsDtoResp.getCompanyName()));
            cell_4_1.setCellStyle(contentStyle);

            //第五行
            HSSFRow row5 = sheet.createRow(3);
            HSSFCell cell_5_1 = row5.createCell(0);
            cell_5_1.setCellValue("﹡参加险种:");
            cell_5_1.setCellStyle(contentStyleAll);
            //"养老（    ）   失业（     ）  工伤（ √  ）   生育（  ）   医疗（   ）"
            HSSFCell cell_5_2 = row5.createCell(1);
            cell_5_2.setCellValue("养老（    ）   失业（     ）  工伤（ √  ）   生育（  ）   医疗（   ）");
            cell_5_2.setCellStyle(contentStyleAll);

            HSSFCell cell_5_3 = row5.createCell(2);
            cell_5_3.setCellStyle(contentStyleAll);

            HSSFCell cell_5_4 = row5.createCell(3);
            cell_5_4.setCellStyle(contentStyleAll);

            HSSFCell cell_5_5 = row5.createCell(4);
            cell_5_5.setCellStyle(contentStyleAll);
            //照片
            HSSFCell cell_5_6 = row5.createCell(5);
            cell_5_6.setCellValue("上传照片");
            cell_5_6.setCellStyle(contentStyleAll);

            //第六行
            HSSFRow row6 = sheet.createRow(4);
            HSSFCell cell_6_1 = row6.createCell(0);
            cell_6_1.setCellValue("﹡姓  名");
            cell_6_1.setCellStyle(contentStyleAll);

            HSSFCell cell_6_2 = row6.createCell(1);
            cell_6_2.setCellValue(printJobNewInsDtoResp.getEmpName());
            cell_6_2.setCellStyle(contentStyleAll);

            HSSFCell cell_6_3 = row6.createCell(2);
            cell_6_3.setCellValue("﹡公民身份号码(社会保障号码)");
            cell_6_3.setCellStyle(contentStyleAll);

            HSSFCell cell_6_4 = row6.createCell(3);
            cell_6_4.setCellValue(printJobNewInsDtoResp.getIdCode());
            cell_6_4.setCellStyle(contentStyleAll);

            HSSFCell cell_6_5 = row6.createCell(4);
            cell_6_5.setCellStyle(contentStyleAll);

            HSSFCell cell_6_6 = row6.createCell(5);
            cell_6_6.setCellStyle(contentStyleAll);

            //第七行
            HSSFRow row7 = sheet.createRow(5);
            HSSFCell cell_7_1 = row7.createCell(0);
            cell_7_1.setCellValue("﹡性  别");
            cell_7_1.setCellStyle(contentStyleAll);

            HSSFCell cell_7_2 = row7.createCell(1);
            cell_7_2.setCellValue(printJobNewInsDtoResp.getGender());
            cell_7_2.setCellStyle(contentStyleAll);

            HSSFCell cell_7_3 = row7.createCell(2);
            cell_7_3.setCellValue("﹡出生日期");
            cell_7_3.setCellStyle(contentStyleAll);

            HSSFCell cell_7_4 = row7.createCell(3);
            cell_7_4.setCellValue(printJobNewInsDtoResp.getBirthDate());
            cell_7_4.setCellStyle(contentStyleAll);

            HSSFCell cell_7_5 = row7.createCell(4);
            cell_7_5.setCellStyle(contentStyleAll);

            HSSFCell cell_7_6 = row7.createCell(5);
            cell_7_6.setCellStyle(contentStyleAll);

            //第八行
            HSSFRow row8 = sheet.createRow(6);
            HSSFCell cell_8_1 = row8.createCell(0);
            cell_8_1.setCellValue("﹡民族");
            cell_8_1.setCellStyle(contentStyleAll);

            HSSFCell cell_8_2 = row8.createCell(1);
            cell_8_2.setCellValue(printJobNewInsDtoResp.getNation());
            cell_8_2.setCellStyle(contentStyleAll);

            HSSFCell cell_8_3 = row8.createCell(2);
            cell_8_3.setCellValue("﹡国家/地区");
            cell_8_3.setCellStyle(contentStyleAll);

            HSSFCell cell_8_4 = row8.createCell(3);
            cell_8_4.setCellValue("中国");
            cell_8_4.setCellStyle(contentStyleAll);

            HSSFCell cell_8_5 = row8.createCell(4);
            cell_8_5.setCellStyle(contentStyleAll);

            HSSFCell cell_8_6 = row8.createCell(5);
            cell_8_6.setCellStyle(contentStyleAll);

            //第九行
            HSSFRow row9 = sheet.createRow(7);
            HSSFCell cell_9_1 = row9.createCell(0);
            cell_9_1.setCellValue("﹡个人身份");
            cell_9_1.setCellStyle(contentStyleAll);

            HSSFCell cell_9_2 = row9.createCell(1);
            cell_9_2.setCellValue(printJobNewInsDtoResp.getPersonalIdentity());
            cell_9_2.setCellStyle(contentStyleAll);

            HSSFCell cell_9_3 = row9.createCell(2);
            cell_9_3.setCellValue("﹡参加工作日期");
            cell_9_3.setCellStyle(contentStyleAll);

            HSSFCell cell_9_4 = row9.createCell(3);
            cell_9_4.setCellValue(printJobNewInsDtoResp.getJoinWorkTime());
            cell_9_4.setCellStyle(contentStyleAll);

            HSSFCell cell_9_5 = row9.createCell(4);
            cell_9_5.setCellStyle(contentStyleAll);

            HSSFCell cell_9_6 = row9.createCell(5);
            cell_9_6.setCellStyle(contentStyleAll);

            //第十行
            HSSFRow row10 = sheet.createRow(8);
            HSSFCell cell_10_1 = row10.createCell(0);
            cell_10_1.setCellValue("户口所在区县街乡");
            cell_10_1.setCellStyle(contentStyleAll);

            HSSFCell cell_10_2 = row10.createCell(1);
            cell_10_2.setCellValue(printJobNewInsDtoResp.getHouseholdCity());
            cell_10_2.setCellStyle(contentStyleAll);

            HSSFCell cell_10_3 = row10.createCell(2);
            cell_10_3.setCellStyle(contentStyleAll);

            HSSFCell cell_10_4 = row10.createCell(3);
            cell_10_4.setCellStyle(contentStyleAll);

            HSSFCell cell_10_5 = row10.createCell(4);
            cell_10_5.setCellValue("﹡户口性质");
            cell_10_5.setCellStyle(contentStyleAll);

            HSSFCell cell_10_6 = row10.createCell(5);
            cell_10_6.setCellValue(printJobNewInsDtoResp.getHouseholdType());
            cell_10_6.setCellStyle(contentStyleAll);

            //第十一行
            HSSFRow row11 = sheet.createRow(9);
            HSSFCell cell_11_1 = row11.createCell(0);
            cell_11_1.setCellValue("﹡户口所在地地址");
            cell_11_1.setCellStyle(contentStyleAll);

            HSSFCell cell_11_2 = row11.createCell(1);
            cell_11_2.setCellValue(printJobNewInsDtoResp.getHouseholdAddr());
            cell_11_2.setCellStyle(contentStyleAll);

            HSSFCell cell_11_3 = row11.createCell(2);
            cell_11_3.setCellStyle(contentStyleAll);

            HSSFCell cell_11_4 = row11.createCell(3);
            cell_11_4.setCellStyle(contentStyleAll);

            HSSFCell cell_11_5 = row11.createCell(4);
            cell_11_5.setCellValue("﹡户口所在地邮政编码");
            cell_11_5.setCellStyle(contentStyleAll);

            HSSFCell cell_11_6 = row11.createCell(5);
            cell_11_6.setCellValue(printJobNewInsDtoResp.getHouseholdZip());
            cell_11_6.setCellStyle(contentStyleAll);

            //第十二行
            HSSFRow row12 = sheet.createRow(10);
            HSSFCell cell_12_1 = row12.createCell(0);
            cell_12_1.setCellValue("﹡居住地（联系）地址");
            cell_12_1.setCellStyle(contentStyleAll);

            HSSFCell cell_12_2 = row12.createCell(1);
            cell_12_2.setCellValue("北京市东城区富华大厦A座");
            cell_12_2.setCellStyle(contentStyleAll);

            HSSFCell cell_12_3 = row12.createCell(2);
            cell_12_3.setCellStyle(contentStyleAll);

            HSSFCell cell_12_4 = row12.createCell(3);
            cell_12_4.setCellStyle(contentStyleAll);

            HSSFCell cell_12_5 = row12.createCell(4);
            cell_12_5.setCellValue("﹡居住地（联系）邮政编码");
            cell_12_5.setCellStyle(contentStyleAll);

            HSSFCell cell_12_6 = row12.createCell(5);
            cell_12_6.setCellValue("100027");
            cell_12_6.setCellStyle(contentStyleAll);

            //第十三行
            HSSFRow row13 = sheet.createRow(11);
            HSSFCell cell_13_1 = row13.createCell(0);
            cell_13_1.setCellValue("﹡选择邮寄社会保险对账单地址");
            cell_13_1.setCellStyle(contentStyleAll);

            HSSFCell cell_13_2 = row13.createCell(1);
            cell_13_2.setCellValue("北京市东城区富华大厦A座");
            cell_13_2.setCellStyle(contentStyleAll);

            HSSFCell cell_13_3 = row13.createCell(2);
            cell_13_3.setCellStyle(contentStyleAll);

            HSSFCell cell_13_4 = row13.createCell(3);
            cell_13_4.setCellStyle(contentStyleAll);

            HSSFCell cell_13_5 = row13.createCell(4);
            cell_13_5.setCellValue("﹡邮政编码");
            cell_13_5.setCellStyle(contentStyleAll);

            HSSFCell cell_13_6 = row13.createCell(5);
            cell_13_6.setCellValue("100027");
            cell_13_6.setCellStyle(contentStyleAll);

            //第十四行
            HSSFRow row14 = sheet.createRow(12);
            HSSFCell cell_14_1 = row14.createCell(0);
            cell_14_1.setCellValue("﹡获取对账单方式");
            cell_14_1.setCellStyle(contentStyleAll);

            HSSFCell cell_14_2 = row14.createCell(1);
            cell_14_2.setCellValue("网上查询");
            cell_14_2.setCellStyle(contentStyleAll);

            HSSFCell cell_14_3 = row14.createCell(2);
            cell_14_3.setCellValue("电子邮件地址");
            cell_14_3.setCellStyle(contentStyleAll);

            HSSFCell cell_14_4 = row14.createCell(3);
            cell_14_4.setCellValue("");
            cell_14_4.setCellStyle(contentStyleAll);

            HSSFCell cell_14_5 = row14.createCell(4);
            cell_14_5.setCellValue("﹡文化程度");
            cell_14_5.setCellStyle(contentStyleAll);

            HSSFCell cell_14_6 = row14.createCell(5);
            cell_14_6.setCellValue(printJobNewInsDtoResp.getEducationBackground());
            cell_14_6.setCellStyle(contentStyleAll);

            //第十五行
            HSSFRow row15 = sheet.createRow(13);
            HSSFCell cell_15_1 = row15.createCell(0);
            cell_15_1.setCellValue("﹡参保人电话");
            cell_15_1.setCellStyle(contentStyleAll);

            HSSFCell cell_15_2 = row15.createCell(1);
            cell_15_2.setCellValue(printJobNewInsDtoResp.getContactTel());
            cell_15_2.setCellStyle(contentStyleAll);

            HSSFCell cell_15_3 = row15.createCell(2);
            cell_15_3.setCellValue("参保人手机");
            cell_15_3.setCellStyle(contentStyleAll);

            HSSFCell cell_15_4 = row15.createCell(3);
            cell_15_4.setCellValue(printJobNewInsDtoResp.getMobile());
            cell_15_4.setCellStyle(contentStyleAll);

            HSSFCell cell_15_5 = row15.createCell(4);
            cell_15_5.setCellValue("﹡申报月均工资收入（元）");
            cell_15_5.setCellStyle(contentStyleAll);

            HSSFCell cell_15_6 = row15.createCell(5);
            cell_15_6.setCellValue("5080");
            cell_15_6.setCellStyle(contentStyleAll);

            //第十六行
            HSSFRow row16 = sheet.createRow(14);
            HSSFCell cell_16_1 = row16.createCell(0);
            cell_16_1.setCellValue("﹡证件类型");
            cell_16_1.setCellStyle(contentStyleAll);

            HSSFCell cell_16_2 = row16.createCell(1);
            cell_16_2.setCellValue("身份证");
            cell_16_2.setCellStyle(contentStyleAll);

            HSSFCell cell_16_3 = row16.createCell(2);
            cell_16_3.setCellStyle(contentStyleAll);

            HSSFCell cell_16_4 = row16.createCell(3);
            cell_16_4.setCellValue("﹡证件号码");
            cell_16_4.setCellStyle(contentStyleAll);

            HSSFCell cell_16_5 = row16.createCell(4);
            cell_16_5.setCellValue(printJobNewInsDtoResp.getIdCode());
            cell_16_5.setCellStyle(contentStyleAll);

            HSSFCell cell_16_6 = row16.createCell(5);
            cell_16_6.setCellStyle(contentStyleAll);

            //第十七行
            HSSFRow row17 = sheet.createRow(15);
            HSSFCell cell_17_1 = row17.createCell(0);
            cell_17_1.setCellValue("委托代发基金银行名称");
            cell_17_1.setCellStyle(contentStyleAll);

            HSSFCell cell_17_2 = row17.createCell(1);
            cell_17_2.setCellValue(printJobNewInsDtoResp.getFeePersonType());
            cell_17_2.setCellStyle(contentStyleAll);

            HSSFCell cell_17_3 = row17.createCell(2);
            cell_17_3.setCellStyle(contentStyleAll);

            HSSFCell cell_17_4 = row17.createCell(3);
            cell_17_4.setCellValue("﹡委托代发银行账号");
            cell_17_4.setCellStyle(contentStyleAll);

            HSSFCell cell_17_5 = row17.createCell(4);
            cell_17_5.setCellValue(printJobNewInsDtoResp.getDelegateBankAccount());
            cell_17_5.setCellStyle(contentStyleAll);

            HSSFCell cell_17_6 = row17.createCell(5);
            cell_17_6.setCellStyle(contentStyleAll);

            //第十八行
            HSSFRow row18 = sheet.createRow(16);
            HSSFCell cell_18_1 = row18.createCell(0);
            cell_18_1.setCellValue("﹡缴费人员类别");
            cell_18_1.setCellStyle(contentStyleAll);

            HSSFCell cell_18_2 = row18.createCell(1);
            cell_17_5.setCellValue(printJobNewInsDtoResp.getFeePersonType());
            cell_18_2.setCellStyle(contentStyleAll);

            HSSFCell cell_18_3 = row18.createCell(2);
            cell_18_3.setCellStyle(contentStyleAll);

            HSSFCell cell_18_4 = row18.createCell(3);
            cell_18_4.setCellValue("﹡医疗参保人员类别");
            cell_18_4.setCellStyle(contentStyleAll);

            HSSFCell cell_18_5 = row18.createCell(4);
            cell_18_4.setCellValue("在职职工");
            cell_18_5.setCellStyle(contentStyleAll);

            HSSFCell cell_18_6 = row18.createCell(5);
            cell_18_6.setCellStyle(contentStyleAll);

            //第十九行
            HSSFRow row19 = sheet.createRow(17);
            HSSFCell cell_19_1 = row19.createCell(0);
            cell_19_1.setCellValue("离退休类别");
            cell_19_1.setCellStyle(contentStyleAll);

            HSSFCell cell_19_2 = row19.createCell(1);
            cell_19_2.setCellStyle(contentStyleAll);

            HSSFCell cell_19_3 = row19.createCell(2);
            cell_19_3.setCellStyle(contentStyleAll);

            HSSFCell cell_19_4 = row19.createCell(3);
            cell_19_4.setCellValue("离退休日期");
            cell_19_4.setCellStyle(contentStyleAll);

            HSSFCell cell_19_5 = row19.createCell(4);
            cell_19_5.setCellStyle(contentStyleAll);

            HSSFCell cell_19_6 = row19.createCell(5);
            cell_19_6.setCellStyle(contentStyleAll);

            //第二十行
            HSSFRow row20 = sheet.createRow(18);
            HSSFCell cell_20_1 = row20.createCell(0);
            cell_20_1.setCellValue("定点医疗机构1");
            cell_20_1.setCellStyle(contentStyleAll);

            HSSFCell cell_20_2 = row20.createCell(1);
            cell_20_2.setCellValue(printJobNewInsDtoResp.getHospital1());
            cell_20_2.setCellStyle(contentStyleAll);

            HSSFCell cell_20_3 = row20.createCell(2);
            cell_20_3.setCellStyle(contentStyleAll);

            HSSFCell cell_20_4 = row20.createCell(3);
            cell_20_4.setCellValue("定点医疗机构2");
            cell_20_4.setCellStyle(contentStyleAll);

            HSSFCell cell_20_5 = row20.createCell(4);
            cell_20_5.setCellValue(printJobNewInsDtoResp.getHospital2());
            cell_20_5.setCellStyle(contentStyleAll);

            HSSFCell cell_20_6 = row20.createCell(5);
            cell_20_6.setCellStyle(contentStyleAll);

            //第二十一行
            HSSFRow row21 = sheet.createRow(19);
            HSSFCell cell_21_1 = row21.createCell(0);
            cell_21_1.setCellValue("定点医疗机构3");
            cell_21_1.setCellStyle(contentStyleAll);

            HSSFCell cell_21_2 = row21.createCell(1);
            cell_21_2.setCellValue(printJobNewInsDtoResp.getHospital3());
            cell_21_2.setCellStyle(contentStyleAll);

            HSSFCell cell_21_3 = row21.createCell(2);
            cell_21_3.setCellStyle(contentStyleAll);

            HSSFCell cell_21_4 = row21.createCell(3);
            cell_21_4.setCellValue("定点医疗机构4");
            cell_21_4.setCellStyle(contentStyleAll);

            HSSFCell cell_21_5 = row21.createCell(4);
            cell_21_5.setCellValue(printJobNewInsDtoResp.getHospital4());
            cell_21_5.setCellStyle(contentStyleAll);

            HSSFCell cell_21_6 = row21.createCell(5);
            cell_21_6.setCellStyle(contentStyleAll);

            //第二十二行
            HSSFRow row22 = sheet.createRow(20);
            HSSFCell cell_22_1 = row22.createCell(0);
            cell_22_1.setCellValue("定点医疗机构5");
            cell_22_1.setCellStyle(contentStyleAll);

            HSSFCell cell_22_2 = row22.createCell(1);
            cell_22_2.setCellStyle(contentStyleAll);

            HSSFCell cell_22_3 = row22.createCell(2);
            cell_22_3.setCellStyle(contentStyleAll);

            HSSFCell cell_22_4 = row22.createCell(3);
            cell_22_4.setCellValue("*是否患有特殊病");
            cell_22_4.setCellStyle(contentStyleAll);

            HSSFCell cell_22_5 = row22.createCell(4);
            cell_22_5.setCellValue("无特殊病");
            cell_22_5.setCellStyle(contentStyleAll);

            HSSFCell cell_22_6 = row22.createCell(5);
            cell_22_6.setCellStyle(contentStyleAll);

            //第二十三行
            HSSFRow row23 = sheet.createRow(21);
            HSSFCell cell_23_1 = row23.createCell(0);
            cell_23_1.setCellValue("外籍人员信息");
            cell_23_1.setCellStyle(contentStyleAll);

            HSSFCell cell_23_2 = row23.createCell(1);
            cell_23_2.setCellStyle(contentStyleAll);

            HSSFCell cell_23_3 = row23.createCell(2);
            cell_23_3.setCellStyle(contentStyleAll);

            HSSFCell cell_23_4 = row23.createCell(3);
            cell_23_4.setCellStyle(contentStyleAll);

            HSSFCell cell_23_5 = row23.createCell(4);
            cell_23_5.setCellStyle(contentStyleAll);

            HSSFCell cell_23_6 = row23.createCell(5);
            cell_23_6.setCellStyle(contentStyleAll);

            //第二十四行
            HSSFRow row24 = sheet.createRow(22);
            HSSFCell cell_24_1 = row24.createCell(0);
            cell_24_1.setCellValue("护照号码");
            cell_24_1.setCellStyle(contentStyleAll);

            HSSFCell cell_24_2 = row24.createCell(1);
            cell_24_2.setCellValue("");
            cell_24_2.setCellStyle(contentStyleAll);

            HSSFCell cell_24_3 = row24.createCell(2);
            cell_24_3.setCellStyle(contentStyleAll);

            HSSFCell cell_24_4 = row24.createCell(3);
            cell_24_4.setCellValue("外国人居留证号码");
            cell_24_4.setCellStyle(contentStyleAll);

            HSSFCell cell_24_5 = row24.createCell(4);
            cell_24_5.setCellValue("");
            cell_24_5.setCellStyle(contentStyleAll);

            HSSFCell cell_24_6 = row24.createCell(5);
            cell_24_6.setCellStyle(contentStyleAll);

            //第二十五行
            HSSFRow row25 = sheet.createRow(23);
            HSSFCell cell_25_1 = row25.createCell(0);
            cell_25_1.setCellValue("外国人证件类型");
            cell_25_1.setCellStyle(contentStyleAll);

            HSSFCell cell_25_2 = row25.createCell(1);
            cell_25_2.setCellValue("");
            cell_25_2.setCellStyle(contentStyleAll);

            HSSFCell cell_25_3 = row25.createCell(2);
            cell_25_3.setCellStyle(contentStyleAll);

            HSSFCell cell_25_4 = row25.createCell(3);
            cell_25_4.setCellValue("外国人证件号码");
            cell_25_4.setCellStyle(contentStyleAll);

            HSSFCell cell_25_5 = row25.createCell(4);
            cell_25_5.setCellStyle(contentStyleAll);

            HSSFCell cell_25_6 = row25.createCell(5);
            cell_25_6.setCellStyle(contentStyleAll);

            //第二十六行
            HSSFRow row26 = sheet.createRow(24);
            HSSFCell cell_26_1 = row26.createCell(0);
            cell_26_1.setCellValue("本人目前确属社会保险参保对象，现申请参加社会保险，按照社会保险登记的要求本人已如实填写了上述相关信息，并对所填写内容的真实有效性负责。");
            cell_26_1.setCellStyle(contentStyleAll);

            HSSFCell cell_26_2 = row26.createCell(1);
            cell_26_2.setCellStyle(contentStyleAll);

            HSSFCell cell_26_3 = row26.createCell(2);
            cell_26_3.setCellStyle(contentStyleAll);

            HSSFCell cell_26_4 = row26.createCell(3);
            cell_26_4.setCellStyle(contentStyleAll);

            HSSFCell cell_26_5 = row26.createCell(4);
            cell_26_5.setCellStyle(contentStyleAll);

            HSSFCell cell_26_6 = row26.createCell(5);
            cell_26_6.setCellStyle(contentStyleAll);

            //第二十七行
            HSSFRow row27 = sheet.createRow(25);
            HSSFCell cell_27_1 = row27.createCell(0);
            cell_27_1.setCellValue("参保人签字:                                                                          签字日期：  年   月   日");
            cell_27_1.setCellStyle(contentStyleAllNoAlign);

            HSSFCell cell_27_2 = row27.createCell(1);
            cell_27_2.setCellStyle(contentStyleAll);

            HSSFCell cell_27_3 = row27.createCell(2);
            cell_27_3.setCellStyle(contentStyleAll);

            HSSFCell cell_27_4 = row27.createCell(3);
            cell_27_4.setCellStyle(contentStyleAll);

            HSSFCell cell_27_5 = row27.createCell(4);
            cell_27_5.setCellStyle(contentStyleAll);

            HSSFCell cell_27_6 = row27.createCell(5);
            cell_27_6.setCellStyle(contentStyleAll);

            //第二十八行
            HSSFRow row28 = sheet.createRow(26);
            HSSFCell cell_28_1 = row28.createCell(0);
            cell_28_1.setCellValue("单位负责人：");
            cell_28_1.setCellStyle(contentStyle);

            HSSFCell cell_28_2 = row28.createCell(1);
            cell_28_2.setCellStyle(contentStyle);

            HSSFCell cell_28_3 = row28.createCell(2);
            cell_28_3.setCellValue("单位经办人：");
            cell_28_3.setCellStyle(contentStyle);

            HSSFCell cell_28_4 = row28.createCell(3);
            cell_28_4.setCellStyle(contentStyle);

            HSSFCell cell_28_5 = row28.createCell(4);
            cell_28_5.setCellValue("社保经（代）办机构经办人员（签章）：");
            cell_28_5.setCellStyle(contentStyle);

            HSSFCell cell_28_6 = row28.createCell(5);
            cell_28_6.setCellStyle(contentStyle);

            //第二十九行
            HSSFRow row29 = sheet.createRow(27);
            HSSFCell cell_29_1 = row29.createCell(0);
            cell_29_1.setCellValue("填报日期：   年  月  日");
            cell_29_1.setCellStyle(contentStyle);

            HSSFCell cell_29_2 = row29.createCell(1);
            cell_29_2.setCellStyle(contentStyle);

            HSSFCell cell_29_3 = row29.createCell(2);
            cell_29_3.setCellStyle(contentStyle);

            HSSFCell cell_29_4 = row29.createCell(3);
            cell_29_4.setCellStyle(contentStyle);

            HSSFCell cell_29_5 = row29.createCell(4);
            cell_29_5.setCellValue("办理日期：    年    月    日");
            cell_29_5.setCellStyle(contentStyle);

            HSSFCell cell_29_6 = row29.createCell(5);
            cell_29_6.setCellStyle(contentStyle);

            //第三十行
            HSSFRow row30 = sheet.createRow(28);
            HSSFCell cell_30_1 = row30.createCell(0);
            cell_30_1.setCellValue("注：表格中带*号的项目为必录项,其他有前提条件的必录项请参考指标解释。");
            cell_30_1.setCellStyle(contentStyle);

            wbs.add(wb);

        }

        return wbs;
    }


    private List<List<PrintJobNewInsDtoResp>> groupListByQuantity(List list, int quantity) {
        if (list == null || list.size() == 0) {
            return list;
        }

        if (quantity <= 0) {
            new IllegalArgumentException("Wrong quantity.");
        }

        List<List<PrintJobNewInsDtoResp>> wrapList = new ArrayList<List<PrintJobNewInsDtoResp>>();
        int count = 0;
        while (count < list.size()) {
            wrapList.add(list.subList(count, (count + quantity) > list.size() ? list.size() : count + quantity));
            count += quantity;
        }

        return wrapList;
    }

    private String getSocialCreditCodeByCompanyName(String companyName){

        if(companyName.contains("肯德基")){
            return "91110000600007281U";
        }
        if(companyName.contains("必胜客")){
            return "911100001016512322";
        }
        if(companyName.contains("东方既白")){
            return "91110106MA004YE86F";
        }
        return null;
    }

    private String getSocialSecurityCodeByCompanyName(String companyName){
        if(companyName.contains("肯德基")){
            return "91110000600007281U";
        }
        if(companyName.contains("必胜客")){
            return "911100001016512322";
        }
        if(companyName.contains("东方既白")){
            return "91110106MA004YE86F";
        }
        return null;
    }

}
