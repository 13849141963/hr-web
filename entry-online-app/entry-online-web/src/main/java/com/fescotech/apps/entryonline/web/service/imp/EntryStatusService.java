package com.fescotech.apps.entryonline.web.service.imp;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fescotech.apps.entryonline.entity.R;
import com.fescotech.apps.entryonline.entity.vo.standard.EmpTaskVo;
import com.fescotech.apps.entryonline.entity.vo.standard.QueryEntryVo;
import com.fescotech.apps.entryonline.util.PageUtils;
import com.fescotech.apps.entryonline.util.Result;
import com.fescotech.apps.entryonline.web.service.IEntryStatusService;
import com.fescotech.apps.entryonline.web.util.ShiroUtils;
import com.fescotech.common.util.ApiUtilOpenPlateform;

@Service
public class EntryStatusService implements IEntryStatusService {

	@Override
	public Result getEmpStatusDict() throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("dictType", "1");
		String jsonStr = JSON.toJSONString(map);
		String methodEntryTask = "olentry.queryDict";
		String resultStr = ApiUtilOpenPlateform.callOpenPlateformApi(methodEntryTask, jsonStr);
		Result result = JSON.parseObject(resultStr, Result.class);
		return result;
	}

	@Override
	public Result getProceduresListDict() throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("dictType", "3");
		String jsonStr = JSON.toJSONString(map);
		String methodEntryTask = "olentry.queryDict";
		String resultStr = ApiUtilOpenPlateform.callOpenPlateformApi(methodEntryTask, jsonStr);
		Result result = JSON.parseObject(resultStr, Result.class);
		return result;
	}

	@Override
	public Result getProceduresStatusDict() throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("dictType", "35");
		String jsonStr = JSON.toJSONString(map);
		String methodEntryTask = "olentry.queryDict";
		String resultStr = ApiUtilOpenPlateform.callOpenPlateformApi(methodEntryTask, jsonStr);
		Result result = JSON.parseObject(resultStr, Result.class);
		return result;
	}

	@Override
	public R queryEntryStatus(QueryEntryVo vo) throws Exception {
		Result result = queryEntryStatusData(vo);

		Object successResult = result.getSuccessResult();
		
		JSONObject successResultObject = JSONObject.parseObject(successResult.toString());
		Object recordListObj = successResultObject.get("pageRecords");
		int totalCount = (int) successResultObject.get("totalCount");

		List<EmpTaskVo> empRecords = null;
		if (recordListObj != null) {
			empRecords = JSONObject.parseArray(recordListObj.toString(), EmpTaskVo.class);
		}

		PageUtils pageUtil = new PageUtils(empRecords, totalCount, vo.getPageSize(), vo.getPageNo());
		return R.ok().put("page", pageUtil);

	}

	@Override
	public void exportData(HttpServletResponse response, Result result) {
		OutputStream output = null;
		try {
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("new sheet");
			sheet.setDefaultColumnWidth(20); // 默认列宽

			HSSFCellStyle titleStyle = wb.createCellStyle(); // 标题样式
			titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			titleStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);// 下边框
			titleStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
			titleStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
			titleStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
			titleStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 水平居中

			titleStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
			titleStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
			titleStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
			titleStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());

			Font ztFont = wb.createFont();
			ztFont.setItalic(false); // 设置字体为斜体字
			ztFont.setColor(Font.COLOR_NORMAL); // 将字体设置为“红色”
			ztFont.setFontHeightInPoints((short) 16); // 将字体大小设置为18px
			ztFont.setFontName("宋体"); // 将“宋体”字体应用到当前单元格上
			ztFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // 加粗
			// ztFont.setUnderline(Font.U_DOUBLE); //
			// 添加（Font.U_SINGLE单条下划线/Font.U_DOUBLE双条下划线）
			// ztFont.setStrikeout(true); // 是否添加删除线
			titleStyle.setFont(ztFont);

			HSSFFont font = wb.createFont();
			font.setFontName("黑体");
			font.setFontHeightInPoints((short) 13);// 设置字体大小
			font.setColor(HSSFColor.WHITE.index); // 字体颜色

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

			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 9));

			// 员工数据下载导出
			HSSFRow row_h = sheet.createRow((short) 0);

			HSSFCell ch = row_h.createCell(0);
			ch.setCellValue("OnSite员工入职办理情况导出");
			ch.setCellStyle(titleStyle);

			HSSFRow row1 = sheet.createRow((short) 1);
			HSSFCell cell_1_0 = row1.createCell(0);
			cell_1_0.setCellValue("任务编号");
			cell_1_0.setCellStyle(headStyle);

			HSSFCell cell_1_1 = row1.createCell(1);
			cell_1_1.setCellValue("雇员姓名");
			cell_1_1.setCellStyle(headStyle);

			HSSFCell cell_1_2 = row1.createCell(2);
			cell_1_2.setCellValue("所在公司");
			cell_1_2.setCellStyle(headStyle);

			HSSFCell cell_1_3 = row1.createCell(3);
			cell_1_3.setCellValue("身份证号");
			cell_1_3.setCellStyle(headStyle);

			HSSFCell cell_1_4 = row1.createCell(4);
			cell_1_4.setCellValue("手机号");
			cell_1_4.setCellStyle(headStyle);

			HSSFCell cell_1_5 = row1.createCell(5);
			cell_1_5.setCellValue("员工状态");
			cell_1_5.setCellStyle(headStyle);

			HSSFCell cell_1_6 = row1.createCell(6);
			cell_1_6.setCellValue("订单起始时间");
			cell_1_6.setCellStyle(headStyle);

			HSSFCell cell_1_7 = row1.createCell(7);
			cell_1_7.setCellValue("入职登记");
			cell_1_7.setCellStyle(headStyle);

			HSSFCell cell_1_8 = row1.createCell(8);
			cell_1_8.setCellValue("社保手续");
			cell_1_8.setCellStyle(headStyle);

			HSSFCell cell_1_9 = row1.createCell(9);
			cell_1_9.setCellValue("人事手续");
			cell_1_9.setCellStyle(headStyle);

			Object successResult = result.getSuccessResult();
			String s1 = JSON.toJSONString(successResult);
			Map map = JSON.parseObject(s1, Map.class);
			Object pageRecords = map.get("pageRecords");
			String s2 = JSON.toJSONString(pageRecords);
			List<EmpTaskVo> entryConditionExport = JSON.parseArray(s2, EmpTaskVo.class);

			if (entryConditionExport == null || entryConditionExport.size() == 0) {
				String data = "未查询到可供下载数据";
				response.setContentType("application/text");
				response.getOutputStream().write(data.getBytes("UTF-8"));
				return;
			}

			for (int i = 0; i < entryConditionExport.size(); i++) {
				HSSFRow row2 = sheet.createRow(i + 2);
				// 任务编号
				HSSFCell cell_2_0 = row2.createCell(0);
				cell_2_0.setCellValue(entryConditionExport.get(i).getTaskId());
				cell_2_0.setCellStyle(contentStyle);

				// 雇员姓名
				HSSFCell cell_2_1 = row2.createCell(1);
				cell_2_1.setCellValue(entryConditionExport.get(i).getEmpName());
				cell_2_1.setCellStyle(contentStyle);

				// 所在公司
				HSSFCell cell_2_2 = row2.createCell(2);
				cell_2_2.setCellValue(entryConditionExport.get(i).getCompanyName());
				cell_2_2.setCellStyle(contentStyle);

				// 身份证号
				HSSFCell cell_2_3 = row2.createCell(3);
				cell_2_3.setCellValue(entryConditionExport.get(i).getIdCode());
				cell_2_3.setCellStyle(contentStyle);

				// 手机号
				HSSFCell cell_2_4 = row2.createCell(4);
				cell_2_4.setCellValue(entryConditionExport.get(i).getMobile());
				cell_2_4.setCellStyle(contentStyle);

				// 员工状态
				HSSFCell cell_2_5 = row2.createCell(5);
				cell_2_5.setCellValue(entryConditionExport.get(i).getEmpStatus());
				cell_2_5.setCellStyle(contentStyle);

				// 订单起始时间
				HSSFCell cell_2_6 = row2.createCell(6);
				cell_2_6.setCellValue(entryConditionExport.get(i).getMyOrderStartTime());
				cell_2_6.setCellStyle(contentStyle);

				// 入职登记
				HSSFCell cell_2_7 = row2.createCell(7);
				cell_2_7.setCellValue(entryConditionExport.get(i).getEnrollStatus());
				cell_2_7.setCellStyle(contentStyle);

				// 社保手续
				HSSFCell cell_2_8 = row2.createCell(8);
				cell_2_8.setCellValue(entryConditionExport.get(i).getInsStatus());
				cell_2_8.setCellStyle(contentStyle);

				// 人事手续
				HSSFCell cell_2_9 = row2.createCell(9);
				cell_2_9.setCellValue(entryConditionExport.get(i).getPersonnelStatus());
				cell_2_9.setCellStyle(contentStyle);

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

		}/*
		 * catch (NullDataException e) { try { response.reset();
		 * response.setStatus(555); response.setContentType("application/text");
		 * String data = "未查询到可供下载数据"; output = response.getOutputStream();
		 * output.write(data.getBytes("UTF-8")); } catch (IOException e1) {
		 * e1.printStackTrace(); } }
		 */catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (output != null) {
					output.close();
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public Result queryEntryStatusData(QueryEntryVo vo) throws Exception {
		String empName = "";
		if (vo.getEmpName() != null && !"".equals(vo.getEmpName())) {
			empName = URLDecoder.decode(vo.getEmpName(), "utf-8");
			empName = URLDecoder.decode(empName, "utf-8");
			vo.setEmpName(empName);
		}
		 String username = (String)ShiroUtils.getSessionAttribute("username");
		vo.setCreator(username);
		String jsonStr = JSON.toJSONString(vo);
		String methodEntryTask = "olentry.queryStandardEntryStatus";
		String resultStr = ApiUtilOpenPlateform.callOpenPlateformApi(methodEntryTask, jsonStr);
		Result result = JSON.parseObject(resultStr, Result.class);
		return result;
	}

}
