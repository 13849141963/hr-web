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
import com.fescotech.apps.entryonline.dto.EntrySituationHRDtoReq;
import com.fescotech.apps.entryonline.dto.EntrySituationHRDtoResp;
import com.fescotech.apps.entryonline.entity.vo.EntrySituationHRVoReq;
import com.fescotech.apps.entryonline.util.Result;
import com.fescotech.apps.entryonline.web.util.CommonDictUtils;
import com.fescotech.common.util.ApiUtilOpenPlateform;


@Controller
@RequestMapping
public class ExportEntryListHrController {
	@RequestMapping(value = "/entryOnline/ExportEntryListHr")
    @ResponseBody
    public void partExport(HttpServletResponse response,@RequestParam(value = "empTaskIds",required=false)Long[] empTaskIds) throws IOException{

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
				//sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 2)); //指定合并区域

				HSSFRow row1 = sheet.createRow((short) 1);
				HSSFCell cell_1_0 = row1.createCell(0);
				cell_1_0.setCellValue("姓名");
				cell_1_0.setCellStyle(headStyle);


				HSSFCell cell_1_1 = row1.createCell(1);
				cell_1_1.setCellValue("品牌");
				cell_1_1.setCellStyle(headStyle);
				
				HSSFCell cell_1_2 = row1.createCell(2);
				cell_1_2.setCellValue("店号");
				cell_1_2.setCellStyle(headStyle);
				
				HSSFCell cell_1_3 = row1.createCell(3);
				cell_1_3.setCellValue("职务");
				cell_1_3.setCellStyle(headStyle);
				
				HSSFCell cell_1_4 = row1.createCell(4);
				cell_1_4.setCellValue("用工类型");
				cell_1_4.setCellStyle(headStyle);
				
				HSSFCell cell_1_5 = row1.createCell(5);
				cell_1_5.setCellValue("身份证号");
				cell_1_5.setCellStyle(headStyle);
				
				HSSFCell cell_1_6 = row1.createCell(6);
				cell_1_6.setCellValue("手机号");
				cell_1_6.setCellStyle(headStyle);
				
				HSSFCell cell_1_7 = row1.createCell(7);
				cell_1_7.setCellValue("社保手续");
				cell_1_7.setCellStyle(headStyle);
				
				HSSFCell cell_1_8 = row1.createCell(8);
				cell_1_8.setCellValue("入职登记");
				cell_1_8.setCellStyle(headStyle);
				
				HSSFCell cell_1_9 = row1.createCell(9);
				cell_1_9.setCellValue("人事档案");
				cell_1_9.setCellStyle(headStyle);


				HSSFCell cell_1_10 = row1.createCell(10);
				cell_1_10.setCellValue("入职申请确认");
				cell_1_10.setCellStyle(headStyle);
				
				//内容
				String method = "olentry.queryEntryProgressList";
				//需要更改
				//entrySituationDto.setBusiCustNos(new String[]{"cp001","cp002","cp003"});
				String[] busiCustIds = CommonDictUtils.getRemoveDuplicateCustomerIds();
				entrySituationDto.setBusiCustNos(busiCustIds);
				entrySituationDto.setEmpTaskIds(empTaskIds);
		      
		        String s = JSON.toJSONString(entrySituationDto);
			    //中台接口
		        String resultStr = ApiUtilOpenPlateform.callOpenPlateformApi(method, s);
		        Result result = JSON.parseObject(resultStr, Result.class);
		        Object successResult = result.getSuccessResult();
		        String s1 = JSON.toJSONString(successResult);
		        Map map = JSON.parseObject(s1, Map.class);
		        Object pageRecords = map.get("pageRecords");
		        String s2 = JSON.toJSONString(pageRecords);
		        List<EntrySituationHRDtoResp> entrySituationHRDtoResps = JSON.parseArray(s2, EntrySituationHRDtoResp.class);

				for (int i = 0; i < entrySituationHRDtoResps.size(); i++) {
					HSSFRow row2 = sheet.createRow(i+2);
					//姓名
					HSSFCell cell_2_0 = row2.createCell(0);
					cell_2_0.setCellValue(entrySituationHRDtoResps.get(i).getEmpName());
					cell_2_0.setCellStyle(contentStyle);
					
					//品牌号
					HSSFCell cell_2_1 = row2.createCell(1);
					cell_2_1.setCellValue(entrySituationHRDtoResps.get(i).getCompanyName());
					cell_2_1.setCellStyle(contentStyle);

					//店号
					HSSFCell cell_2_2 = row2.createCell(2);
					cell_2_2.setCellValue(entrySituationHRDtoResps.get(i).getEmpDepName());
					cell_2_2.setCellStyle(contentStyle);
					
					//职务
					HSSFCell cell_2_3 = row2.createCell(3);
					cell_2_3.setCellValue(entrySituationHRDtoResps.get(i).getPostName());
					cell_2_3.setCellStyle(contentStyle);
					
					//用工类型
					HSSFCell cell_2_4 = row2.createCell(4);
					cell_2_4.setCellValue(entrySituationHRDtoResps.get(i).getJobTypeName());
					cell_2_4.setCellStyle(contentStyle);
					
					
					//身份证号
					HSSFCell cell_2_5 = row2.createCell(5);
					cell_2_5.setCellValue(entrySituationHRDtoResps.get(i).getIdCode());
					cell_2_5.setCellStyle(contentStyle);
					
					//手机号
					HSSFCell cell_2_6 = row2.createCell(6);
					cell_2_6.setCellValue(entrySituationHRDtoResps.get(i).getMobile());
					cell_2_6.setCellStyle(contentStyle);
					
					//社保手续
					HSSFCell cell_2_7 = row2.createCell(7);
					cell_2_7.setCellValue(entrySituationHRDtoResps.get(i).getInsStatusName());
					cell_2_7.setCellStyle(contentStyle);
					
					//入职登记
					HSSFCell cell_2_8 = row2.createCell(8);
					cell_2_8.setCellValue(entrySituationHRDtoResps.get(i).getEnrollStatusName());
					cell_2_8.setCellStyle(contentStyle);
					
					//人事档案
					HSSFCell cell_2_9 = row2.createCell(9);
					cell_2_9.setCellValue(entrySituationHRDtoResps.get(i).getPersonnelStatusName());
					cell_2_9.setCellStyle(contentStyle);
					//入职申请确认
					HSSFCell cell_2_10 = row2.createCell(10);
					cell_2_10.setCellValue(entrySituationHRDtoResps.get(i).getConfirmStatusName());
					cell_2_10.setCellStyle(contentStyle);
				}
				Date d = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String date = sdf.format(d);
				String name = URLEncoder.encode("员工入职情况查询（HR端）", "utf-8");
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

