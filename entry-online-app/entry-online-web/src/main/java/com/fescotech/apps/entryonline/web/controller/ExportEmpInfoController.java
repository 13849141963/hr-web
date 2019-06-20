package com.fescotech.apps.entryonline.web.controller;

import com.alibaba.fastjson.JSON;
import com.fescotech.apps.entryonline.dto.EmpInfoDtoReq;
import com.fescotech.apps.entryonline.dto.EmpInfoDtoResp;
import com.fescotech.apps.entryonline.dto.EntrySituationHRDtoReq;
import com.fescotech.apps.entryonline.dto.EntrySituationHRDtoResp;
import com.fescotech.apps.entryonline.entity.R;
import com.fescotech.apps.entryonline.util.Result;
import com.fescotech.apps.entryonline.web.exception.NullDataException;
import com.fescotech.apps.entryonline.web.util.CommonDictUtils;
import com.fescotech.common.util.ApiUtilOpenPlateform;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by cy on 2018/3/26.
 */
@Controller
public class ExportEmpInfoController {

        @RequestMapping(value = "/entryOnline/ExportEmpInfos")
        @ResponseBody
        public void partExport(HttpServletResponse response, @RequestParam(value = "empTaskIds",required=false)Long[] empTaskIds) throws IOException {

            EmpInfoDtoReq empInfoReq = new EmpInfoDtoReq();
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
                ch.setCellValue("员工信息表");
                ch.setCellStyle(headStyle);
                //sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 2)); //指定合并区域

                HSSFRow row1 = sheet.createRow((short) 1);
                HSSFCell cell_1_0 = row1.createCell(0);
                cell_1_0.setCellValue("证件类型");
                cell_1_0.setCellStyle(headStyle);


                HSSFCell cell_1_1 = row1.createCell(1);
                cell_1_1.setCellValue("姓名");
                cell_1_1.setCellStyle(headStyle);

                HSSFCell cell_1_2 = row1.createCell(2);
                cell_1_2.setCellValue("证件号码");
                cell_1_2.setCellStyle(headStyle);

                HSSFCell cell_1_3 = row1.createCell(3);
                cell_1_3.setCellValue("性别");
                cell_1_3.setCellStyle(headStyle);

                HSSFCell cell_1_4 = row1.createCell(4);
                cell_1_4.setCellValue("出生日期");
                cell_1_4.setCellStyle(headStyle);

                HSSFCell cell_1_5 = row1.createCell(5);
                cell_1_5.setCellValue("民族");
                cell_1_5.setCellStyle(headStyle);

                HSSFCell cell_1_6 = row1.createCell(6);
                cell_1_6.setCellValue("入职公司");
                cell_1_6.setCellStyle(headStyle);

                HSSFCell cell_1_7 = row1.createCell(7);
                cell_1_7.setCellValue("入职门店");
                cell_1_7.setCellStyle(headStyle);

                HSSFCell cell_1_8 = row1.createCell(8);
                cell_1_8.setCellValue("职务");
                cell_1_8.setCellStyle(headStyle);

                HSSFCell cell_1_9 = row1.createCell(9);
                cell_1_9.setCellValue("工作类型");
                cell_1_9.setCellStyle(headStyle);


                HSSFCell cell_1_10 = row1.createCell(10);
                cell_1_10.setCellValue("员工类型");
                cell_1_10.setCellStyle(headStyle);

                HSSFCell cell_1_11 = row1.createCell(11);
                cell_1_11.setCellValue("户别");
                cell_1_11.setCellStyle(headStyle);

                HSSFCell cell_1_12 = row1.createCell(12);
                cell_1_12.setCellValue("户口所在地");
                cell_1_12.setCellStyle(headStyle);

                HSSFCell cell_1_13 = row1.createCell(13);
                cell_1_13.setCellValue("缴费人员类别");
                cell_1_13.setCellStyle(headStyle);

                HSSFCell cell_1_14 = row1.createCell(14);
                cell_1_14.setCellValue("户口性质");
                cell_1_14.setCellStyle(headStyle);

                HSSFCell cell_1_15 = row1.createCell(15);
                cell_1_15.setCellValue("文化程度");
                cell_1_15.setCellStyle(headStyle);

                HSSFCell cell_1_16 = row1.createCell(16);
                cell_1_16.setCellValue("婚姻状况");
                cell_1_16.setCellStyle(headStyle);

                HSSFCell cell_1_17 = row1.createCell(17);
                cell_1_17.setCellValue("政治面貌");
                cell_1_17.setCellStyle(headStyle);

                HSSFCell cell_1_18 = row1.createCell(18);
                cell_1_18.setCellValue("参加工作日期");
                cell_1_18.setCellStyle(headStyle);

                HSSFCell cell_1_19 = row1.createCell(19);
                cell_1_19.setCellValue("户口所在地地址");
                cell_1_19.setCellStyle(headStyle);

                HSSFCell cell_1_20 = row1.createCell(20);
                cell_1_20.setCellValue("户口所在地邮编");
                cell_1_20.setCellStyle(headStyle);

                HSSFCell cell_1_21 = row1.createCell(21);
                cell_1_21.setCellValue("居住地联系地址");
                cell_1_21.setCellStyle(headStyle);

                HSSFCell cell_1_22 = row1.createCell(22);
                cell_1_22.setCellValue("居住地联系邮编");
                cell_1_22.setCellStyle(headStyle);

                HSSFCell cell_1_23 = row1.createCell(23);
                cell_1_23.setCellValue("参保人电话");
                cell_1_23.setCellStyle(headStyle);

                HSSFCell cell_1_24 = row1.createCell(24);
                cell_1_24.setCellValue("联系人电话");
                cell_1_24.setCellStyle(headStyle);

                HSSFCell cell_1_25 = row1.createCell(25);
                cell_1_25.setCellValue("个人身份");
                cell_1_25.setCellStyle(headStyle);

                HSSFCell cell_1_26 = row1.createCell(26);
                cell_1_26.setCellValue("定点医疗机构1");
                cell_1_26.setCellStyle(headStyle);

                HSSFCell cell_1_27 = row1.createCell(27);
                cell_1_27.setCellValue("定点医疗机构2");
                cell_1_27.setCellStyle(headStyle);

                HSSFCell cell_1_28 = row1.createCell(28);
                cell_1_28.setCellValue("定点医疗机构3");
                cell_1_28.setCellStyle(headStyle);

                HSSFCell cell_1_29 = row1.createCell(29);
                cell_1_29.setCellValue("定点医疗机构4");
                cell_1_29.setCellStyle(headStyle);

                HSSFCell cell_1_30 = row1.createCell(30);
                cell_1_30.setCellValue("委托代发银行类型");
                cell_1_30.setCellStyle(headStyle);

                HSSFCell cell_1_31 = row1.createCell(31);
                cell_1_31.setCellValue("委托代发银行账户");
                cell_1_31.setCellStyle(headStyle);




                //内容
                String method = "olentry.batchLoadEmpInfo";
                //需要更改
                //entrySituationDto.setBusiCustNos(new String[]{"cp001","cp002","cp003"});
                String[] busiCustIds = CommonDictUtils.getRemoveDuplicateCustomerIds();
                empInfoReq.setBusiCustNos(busiCustIds);
                empInfoReq.setEmpTaskIds(empTaskIds);

                String s = JSON.toJSONString(empInfoReq);
                //中台接口
                String resultStr = ApiUtilOpenPlateform.callOpenPlateformApi(method, s);
                Result result = JSON.parseObject(resultStr, Result.class);
                Object successResult = result.getSuccessResult();
                String s1 = JSON.toJSONString(successResult);
                Map map = JSON.parseObject(s1, Map.class);
                Object pageRecords = map.get("pageRecords");
                String s2 = JSON.toJSONString(pageRecords);
                List<EmpInfoDtoResp> EmpInfoDtoResps = JSON.parseArray(s2, EmpInfoDtoResp.class);

                for (int i = 0; i < EmpInfoDtoResps.size(); i++) {
                    HSSFRow row2 = sheet.createRow(i+2);
                    //证件类型
                    HSSFCell cell_2_0 = row2.createCell(0);
                    cell_2_0.setCellValue(EmpInfoDtoResps.get(i).getIdType());
                    cell_2_0.setCellStyle(contentStyle);

                    //姓名
                    HSSFCell cell_2_1 = row2.createCell(1);
                    cell_2_1.setCellValue(EmpInfoDtoResps.get(i).getEmpName());
                    cell_2_1.setCellStyle(contentStyle);

                    //证件号码
                    HSSFCell cell_2_2 = row2.createCell(2);
                    cell_2_2.setCellValue(EmpInfoDtoResps.get(i).getIdCode());
                    cell_2_2.setCellStyle(contentStyle);

                    //性别
                    HSSFCell cell_2_3 = row2.createCell(3);
                    cell_2_3.setCellValue(EmpInfoDtoResps.get(i).getGender());
                    cell_2_3.setCellStyle(contentStyle);

                    //出生日期
                    HSSFCell cell_2_4 = row2.createCell(4);
                    cell_2_4.setCellValue(EmpInfoDtoResps.get(i).getBirthDate());
                    cell_2_4.setCellStyle(contentStyle);


                    //民族
                    HSSFCell cell_2_5 = row2.createCell(5);
                    cell_2_5.setCellValue(EmpInfoDtoResps.get(i).getNation());
                    cell_2_5.setCellStyle(contentStyle);

                    //入职公司
                    HSSFCell cell_2_6 = row2.createCell(6);
                    cell_2_6.setCellValue(EmpInfoDtoResps.get(i).getCompanyName());
                    cell_2_6.setCellStyle(contentStyle);

                    //入职门店
                    HSSFCell cell_2_7 = row2.createCell(7);
                    cell_2_7.setCellValue(EmpInfoDtoResps.get(i).getDepName());
                    cell_2_7.setCellStyle(contentStyle);

                    //职务
                    HSSFCell cell_2_8 = row2.createCell(8);
                    cell_2_8.setCellValue(EmpInfoDtoResps.get(i).getPost());
                    cell_2_8.setCellStyle(contentStyle);

                    //工作类型
                    HSSFCell cell_2_9 = row2.createCell(9);
                    cell_2_9.setCellValue(EmpInfoDtoResps.get(i).getJobType());
                    cell_2_9.setCellStyle(contentStyle);
                    //员工类型
                    HSSFCell cell_2_10 = row2.createCell(10);
                    cell_2_10.setCellValue(EmpInfoDtoResps.get(i).getEmpType());
                    cell_2_10.setCellStyle(contentStyle);

                    //户别
                    HSSFCell cell_2_11 = row2.createCell(11);
                    cell_2_11.setCellValue(EmpInfoDtoResps.get(i).getHouseholdCate());
                    cell_2_11.setCellStyle(contentStyle);
                    //户口所在地
                    HSSFCell cell_2_12 = row2.createCell(12);
                    cell_2_12.setCellValue(EmpInfoDtoResps.get(i).getHouseholdCity());
                    cell_2_12.setCellStyle(contentStyle);
                    //缴费人员类别
                    HSSFCell cell_2_13 = row2.createCell(13);
                    cell_2_13.setCellValue(EmpInfoDtoResps.get(i).getFeePersonType());
                    cell_2_13.setCellStyle(contentStyle);
                    //户口性质
                    HSSFCell cell_2_14 = row2.createCell(14);
                    cell_2_14.setCellValue(EmpInfoDtoResps.get(i).getHouseholdType());
                    cell_2_14.setCellStyle(contentStyle);
                    //文化程度
                    HSSFCell cell_2_15 = row2.createCell(15);
                    cell_2_15.setCellValue(EmpInfoDtoResps.get(i).getEducationBackground());
                    cell_2_15.setCellStyle(contentStyle);
                    //婚姻状况
                    HSSFCell cell_2_16 = row2.createCell(16);
                    cell_2_16.setCellValue(EmpInfoDtoResps.get(i).getMarriageStatus());
                    cell_2_16.setCellStyle(contentStyle);
                    //政治面貌
                    HSSFCell cell_2_17 = row2.createCell(17);
                    cell_2_17.setCellValue(EmpInfoDtoResps.get(i).getPoliticalStatus());
                    cell_2_17.setCellStyle(contentStyle);
                    //参加工作日期
                    HSSFCell cell_2_18 = row2.createCell(18);
                    cell_2_18.setCellValue(EmpInfoDtoResps.get(i).getJoinWorkTime());
                    cell_2_18.setCellStyle(contentStyle);
                    //户口所在地地址
                    HSSFCell cell_2_19 = row2.createCell(19);
                    cell_2_19.setCellValue(EmpInfoDtoResps.get(i).getHouseholdAddr());
                    cell_2_19.setCellStyle(contentStyle);
                    //户口所在地邮编
                    HSSFCell cell_2_20 = row2.createCell(20);
                    cell_2_20.setCellValue(EmpInfoDtoResps.get(i).getHouseholdZip());
                    cell_2_20.setCellStyle(contentStyle);
                    //居住地联系地址
                    HSSFCell cell_2_21 = row2.createCell(21);
                    cell_2_21.setCellValue(EmpInfoDtoResps.get(i).getResidenceAddr());
                    cell_2_21.setCellStyle(contentStyle);
                    //居住地联系邮编
                    HSSFCell cell_2_22 = row2.createCell(22);
                    cell_2_22.setCellValue(EmpInfoDtoResps.get(i).getPostCode());
                    cell_2_22.setCellStyle(contentStyle);
                    //参保人电话
                    HSSFCell cell_2_23 = row2.createCell(23);
                    cell_2_23.setCellValue(EmpInfoDtoResps.get(i).getContactTel());
                    cell_2_23.setCellStyle(contentStyle);
                    //联系人电话
                    HSSFCell cell_2_24 = row2.createCell(24);
                    cell_2_24.setCellValue(EmpInfoDtoResps.get(i).getContactPersonTel());
                    cell_2_24.setCellStyle(contentStyle);
                    //个人身份
                    HSSFCell cell_2_25 = row2.createCell(25);
                    cell_2_25.setCellValue(EmpInfoDtoResps.get(i).getPersonalIdentity());
                    cell_2_25.setCellStyle(contentStyle);
                    //定点医疗机构1
                    HSSFCell cell_2_26 = row2.createCell(26);
                    cell_2_26.setCellValue(EmpInfoDtoResps.get(i).getHospital1());
                    cell_2_26.setCellStyle(contentStyle);
                    //定点医疗机构2
                    HSSFCell cell_2_27 = row2.createCell(27);
                    cell_2_27.setCellValue(EmpInfoDtoResps.get(i).getHospital2());
                    cell_2_27.setCellStyle(contentStyle);
                    //定点医疗机构3
                    HSSFCell cell_2_28 = row2.createCell(28);
                    cell_2_28.setCellValue(EmpInfoDtoResps.get(i).getHospital3());
                    cell_2_28.setCellStyle(contentStyle);
                    //定点医疗机构4
                    HSSFCell cell_2_29 = row2.createCell(29);
                    cell_2_29.setCellValue(EmpInfoDtoResps.get(i).getHospital4());
                    cell_2_29.setCellStyle(contentStyle);
                    //委托代发银行类型
                    HSSFCell cell_2_30 = row2.createCell(30);
                    cell_2_30.setCellValue(EmpInfoDtoResps.get(i).getDelegateBank());
                    cell_2_30.setCellStyle(contentStyle);
                    //委托代发银行账户
                    HSSFCell cell_2_31 = row2.createCell(31);
                    cell_2_31.setCellValue(EmpInfoDtoResps.get(i).getDelegateBankAccount());
                    cell_2_31.setCellStyle(contentStyle);

                }
                Date d = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String date = sdf.format(d);
                String name = URLEncoder.encode("员工信息表", "utf-8");
                String filename = name+date;
                OutputStream output=response.getOutputStream();
                response.reset();
                response.setHeader("Content-disposition", "attachment; filename="+filename+".xls");
                response.setContentType("application/msexcel");
                wb.write(output);
                output.close();
            }catch (NullDataException e){
                return ;
            }catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


