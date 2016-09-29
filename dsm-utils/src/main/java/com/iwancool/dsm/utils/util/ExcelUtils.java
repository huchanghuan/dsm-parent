package com.iwancool.dsm.utils.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.iwancool.dsm.utils.bean.AccountStatementBean;
import com.iwancool.dsm.utils.bean.MerchantsRecordBean;
import com.iwancool.dsm.utils.bean.WithdrawExportBean;
import com.iwancool.dsm.utils.bean.WithdrawResultBean;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class ExcelUtils {
	
	private static final int TITLE_ROW1 = 0;
	
	private static final int TITLE_ROW2 = 2;
	
	private static final int COLUMN_WIDTH = 20;
	
	public static boolean  exportExcel(HttpServletRequest request,HttpServletResponse response,List<List<Object>> contents, List<String> columnNames,String titleName){
		WritableWorkbook writeBook = null;
		OutputStream out = null;
		try{
			out = response.getOutputStream();
			response.setContentType("application/msexcel");// 定义输出类型  
            //获取浏览器类型  
            String user_agent = request.getHeader("User-Agent").toLowerCase();  
            String fileName = titleName;
            //为不同的浏览器，对文件名进行不同的编码转换  
            if(user_agent.indexOf("firefox") > 0){  
                 fileName =  new String(titleName.getBytes("UTF-8"), "iso8859-1");  
            }else{  
                fileName = URLEncoder.encode(titleName, "UTF-8");  
            }  
            response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xls");         // 设定输出文件头  
			writeBook = Workbook.createWorkbook(out);// 建立excel文件  
			
			// 2、新建工作表(sheet)对象，并声明其属于第几页
			WritableSheet sheet = writeBook.createSheet("blank", 0);// 第一个参数为工作簿的名称，第二个参数为页数
			if(titleName != null && !titleName.isEmpty()){
				sheet.setName(titleName);
			}
			
//			// 3、创建单元格(Label)对象，
//			Label label1 = new Label(1, 2, "test1234");// 第一个参数指定单元格的列数、第二个参数指定单元格的行数，第三个指定写的字符串内容
//			sheet.addCell(label1);
			Label label = null;
			Label titleNameLabel = new Label(0, 0, titleName);	
			sheet.mergeCells(0,0, columnNames.size()-1, 0);// 参数说明，前两个参数为待合并的起始单元格位置，后两个参数用来指定结束单元格位置（列和行）
			sheet.addCell(titleNameLabel);
			for(int index = 0;index < columnNames.size();index++){
				label = new Label(index, 1, columnNames.get(index));
				sheet.addCell(label);
			}
			for(int rowIndex = 0; rowIndex < contents.size();rowIndex++){
				for(int colIndex = 0;colIndex < columnNames.size();colIndex++){
					label = new Label(colIndex, rowIndex+2, contents.get(rowIndex).get(colIndex)+"");
					sheet.addCell(label);
				}
			}
			
			// 4、打开流，开始写文件
			writeBook.write();
			return true;
		}catch(Exception e){
			return false;
		}finally{
			if(writeBook != null){
				try {
					// 5、关闭流
					writeBook.close();
				} catch (Exception e) {
					
				}
			}
		}
	}
	
	public static boolean  exportCsvFile(HttpServletRequest request,HttpServletResponse response,List<List<Object>> contents, List<String> columnNames,String titleName){
		BufferedWriter writer = null;
		try{
			response.setContentType("application/csv");// 定义输出类型  
			//获取浏览器类型  
			String user_agent = request.getHeader("User-Agent").toLowerCase();  
			String fileName = titleName;
			//为不同的浏览器，对文件名进行不同的编码转换  
			if(user_agent.indexOf("firefox") > 0){  
				fileName =  new String(titleName.getBytes("UTF-8"), "iso8859-1");  
			}else{  
				fileName = URLEncoder.encode(titleName, "UTF-8");  
			}  
			response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".csv");         // 设定输出文件头  
			
			 writer = new BufferedWriter(new OutputStreamWriter(response.getOutputStream(), "gb2312"));
			for(int index = 0;index < columnNames.size();index++){
				writer.append("\""+columnNames.get(index)+"\"");
				if(index < columnNames.size() -1){
					writer.append(",");
				}
			}
			writer.newLine();
			for(int rowIndex = 0; rowIndex < contents.size();rowIndex++){
				for(int colIndex = 0;colIndex < columnNames.size();colIndex++){
					writer.append("\""+contents.get(rowIndex).get(colIndex)+"\"");
					if(colIndex < columnNames.size() -1){
						writer.append(",");
					}
				}
				writer.newLine();
			}
			
			// 4、打开流，开始写文件
			writer.flush();
			return true;
        } catch (Exception e) {
        	return false;
        } finally {
        	try {
	        	if(writer != null){
	        		writer.close();
	        	}
            } catch (Exception e) {
            	
            }
        }
	}
	
	/**
	 * 导出支付宝xls
	 * @param request
	 * @param response
	 * @param withdrawExport
	 * @param titleName
	 * @return
	 */
	public static boolean  exportAliPayExcel(HttpServletRequest request,HttpServletResponse response,WithdrawExportBean withdrawExport, String titleName){
		WritableWorkbook writeBook = null;
		OutputStream out = null;
		try{
			out = response.getOutputStream();
			response.setContentType("application/msexcel");// 定义输出类型  
            //获取浏览器类型  
            String user_agent = request.getHeader("User-Agent").toLowerCase();  
            String fileName = titleName;
            //为不同的浏览器，对文件名进行不同的编码转换  
            if(user_agent.indexOf("firefox") > 0){  
                 fileName =  new String(titleName.getBytes("UTF-8"), "iso8859-1");  
            }else{  
                fileName = URLEncoder.encode(titleName, "UTF-8");  
            }  
            response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xls");         // 设定输出文件头  
			writeBook = Workbook.createWorkbook(out);// 建立excel文件  
			
			// 2、新建工作表(sheet)对象，并声明其属于第几页
			WritableSheet sheet = writeBook.createSheet("blank", 0);// 第一个参数为工作簿的名称，第二个参数为页数
			if(titleName != null && !titleName.isEmpty()){
				sheet.setName(titleName);
			}
			//3创建单元格
			/*for(int rowIndex = 0,rowSize = withdrawExport.size(); rowIndex < rowSize;rowIndex++){
				List<Object> rowData = withdrawExport.get(rowIndex);
				for(int colIndex = 0, colSize = rowData.size(); colIndex < colSize;colIndex++){
					if (rowIndex == TITLE_ROW1 || rowIndex == TITLE_ROW2) {
						label = new Label(colIndex, rowIndex, rowData.get(colIndex)+"", getTitle());
					} else {
						label = new Label(colIndex, rowIndex, rowData.get(colIndex)+"", getNormal());
					}
					sheet.setColumnView(colIndex, 20);
					sheet.addCell(label);
				}
			}*/
			writeExportData(sheet, withdrawExport);
			
			// 4、打开流，开始写文件
			writeBook.write();
			return true;
		}catch(Exception e){
			return false;
		}finally{
			if(writeBook != null){
				try {
					// 5、关闭流
					writeBook.close();
				} catch (Exception e) {
					
				}
			}
		}
	}
	
	/**
	 * 读取支付宝批量付款结果文件
	 * @param file
	 * @return
	 */
	public static WithdrawResultBean readAliPayExcel(File file){
		try {
			return readAliPayExcel(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 读取支付宝批量付款结果文件
	 * @param input
	 * @return
	 */
	public static WithdrawResultBean readAliPayExcel(InputStream input){
		WithdrawResultBean withdrawResult = null;
		try {
			withdrawResult = new WithdrawResultBean();
			Workbook wb = Workbook.getWorkbook(input);
			Sheet sheet = wb.getSheet(0);//第1个sheet
			int row = sheet.getRows();//总行数
			int fixIndex = 1;
			
			//1
			Cell[] cells = sheet.getRow(fixIndex);
			setPayeeData(withdrawResult, cells);
			
			List<AccountStatementBean> list = new ArrayList<AccountStatementBean>();
			//2
			for(int i=3; i<row; i++){
				Cell[] cellDatas = sheet.getRow(i);
				String index = null;
				if (null != cellDatas && 0 != cellDatas.length && null != (index = cellDatas[0].getContents()) && 0 != index.trim().length()) {
					list.add(setUserData(cellDatas));
				}
			}
			
			withdrawResult.setStatementList(list);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return withdrawResult;
	}
	
	/**
	 * 设置用户方信息
	 * @param withdrawResult
	 * @param cellDatas
	 */
	private static AccountStatementBean setUserData(Cell[] cellDatas) {
		AccountStatementBean accountStatementBean = new AccountStatementBean();
		System.out.println("#:" + cellDatas[0].getContents());
		accountStatementBean.setUserId(Long.valueOf(cellDatas[0].getContents()));
		accountStatementBean.setPayeeName(cellDatas[1].getContents());
		accountStatementBean.setPayee(cellDatas[2].getContents());
		accountStatementBean.setAmount(Double.valueOf(cellDatas[3].getContents()));
		accountStatementBean.setReason(cellDatas[4].getContents());
		accountStatementBean.setAliPayNo(cellDatas[5].getContents());
		accountStatementBean.setEstimatedCharge(Double.valueOf(cellDatas[6].getContents()));
		accountStatementBean.setSuccessFees(Double.valueOf(cellDatas[7].getContents()));
		accountStatementBean.setStatus(cellDatas[8].getContents());
		accountStatementBean.setFailReason(cellDatas[9].getContents());
		
		return accountStatementBean;
	}

	/**
	 * 设置付款方信息
	 * @param withdrawResult
	 * @param cells
	 */
	private static void setPayeeData(WithdrawResultBean withdrawResult, Cell[] cells) {
		if (null != cells && 0 != cells.length) {
			withdrawResult.setBatchNo(cells[0].getContents());
			withdrawResult.setPaymentDate(cells[1].getContents());
			withdrawResult.setDrawee(cells[2].getContents());
			withdrawResult.setAccountName(cells[3].getContents());
			withdrawResult.setTotalAmount(Double.valueOf(cells[4].getContents()));
			withdrawResult.setTotalNum(Integer.valueOf(cells[5].getContents()));
			withdrawResult.setTotalEstimatedCharge(Double.valueOf(cells[6].getContents()));
			withdrawResult.setTotalSuccessFees(Double.valueOf(cells[7].getContents()));
		}
		
	}

	/**
	* 设置标题样式
	* @return
	*/
	public static WritableCellFormat getTitle(){
		WritableFont font = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
		try {
			font.setColour(Colour.BLACK);
			} catch (WriteException e1) {
			e1.printStackTrace();
			}
			WritableCellFormat format = new WritableCellFormat(font);
		
			try {
			format.setAlignment(jxl.format.Alignment.LEFT);
			format.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			format.setBorder(Border.ALL,BorderLineStyle.THIN,Colour.BLACK);
		} catch (WriteException e) {
		e.printStackTrace();
		}
		return format;
	}
	
	/**
	* 设置标题样式
	* @return
	*/
	public static WritableCellFormat getNormal(){
		WritableFont font = new WritableFont(WritableFont.ARIAL, 10);
		try {
			font.setColour(Colour.BLACK);
			} catch (WriteException e1) {
			e1.printStackTrace();
			}
			WritableCellFormat format = new WritableCellFormat(font);
		
			try {
			format.setAlignment(jxl.format.Alignment.LEFT);
			format.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			format.setBorder(Border.ALL,BorderLineStyle.THIN,Colour.BLACK);
		} catch (WriteException e) {
		e.printStackTrace();
		}
		return format;
	}
	
	private static void writeExportData(WritableSheet sheet, WithdrawExportBean withdrawExport) {
		try {
			List<Object> titleList = getDraweeTitle();
			List<Object> exportDataTitleList = getDataTitleList();
			WritableCellFormat titleFormat = getTitle();
			WritableCellFormat normalFormat = getNormal();
			
			//付款方信息
			for (int i = 0,len = titleList.size(); i < len; i++) {
				Label label = new Label(i, TITLE_ROW1, (String)titleList.get(i), titleFormat);
				sheet.setColumnView(i, COLUMN_WIDTH);
				sheet.addCell(label);
			}
			
			Label batchNoLabel = new Label(0, TITLE_ROW1 + 1, withdrawExport.getBatchNo(), normalFormat);
			Label payDaylabel = new Label(1, TITLE_ROW1 + 1, withdrawExport.getPayDay(), normalFormat);
			Label draweeLabel = new Label(2, TITLE_ROW1 + 1, withdrawExport.getDrawee(), normalFormat);
			Label accountLabel = new Label(3, TITLE_ROW1 + 1, withdrawExport.getAccountName(), normalFormat);
			Label totalAmountLabel = new Label(4, TITLE_ROW1 + 1, String.valueOf(withdrawExport.getTotalAmount()), normalFormat);
			Label totalnumberLabel = new Label(5, TITLE_ROW1 + 1, String.valueOf(withdrawExport.getTotalNum()), normalFormat);
			sheet.addCell(batchNoLabel);
			sheet.addCell(payDaylabel);
			sheet.addCell(draweeLabel);
			sheet.addCell(accountLabel);
			sheet.addCell(totalAmountLabel);
			sheet.addCell(totalnumberLabel);
			
			//用户收款信息
			//标题
			for (int i = 0,len = exportDataTitleList.size(); i < len; i++) {
				Label label = new Label(i, TITLE_ROW2, (String)exportDataTitleList.get(i), titleFormat);
				sheet.addCell(label);
			}
			
			List<MerchantsRecordBean> merchantsRecordList = withdrawExport.getMerchantsRecordList();
			for (int r = 0,len = merchantsRecordList.size(); r < len; r++) {
				MerchantsRecordBean merchant = merchantsRecordList.get(r);
				Label userIdLabel = new Label(0, TITLE_ROW2 + 1 + r, merchant.getUserId(), normalFormat);
				Label payeeLabel = new Label(1, TITLE_ROW2 + 1 + r, merchant.getPayee(), normalFormat);
				Label payeeNameLabel = new Label(2, TITLE_ROW2 + 1 + r, merchant.getPayeeName(), normalFormat);
				Label amountLabel = new Label(3, TITLE_ROW2 + 1 + r, String.valueOf(merchant.getAmount()), normalFormat);
				Label reasonLabel = new Label(4, TITLE_ROW2 + 1 + r, merchant.getReason(), normalFormat);
				sheet.addCell(userIdLabel);
				sheet.addCell(payeeLabel);
				sheet.addCell(payeeNameLabel);
				sheet.addCell(amountLabel);
				sheet.addCell(reasonLabel);
			}
			
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
		
	}

	private static List<Object> getDataTitleList() {
		List<Object> dataTitleList = new ArrayList<Object>();
		dataTitleList.add("商户流水号");
		dataTitleList.add("收款人email");
		dataTitleList.add("收款人姓名");
		dataTitleList.add("付款金额（元）");
		dataTitleList.add("付款理由");
		return dataTitleList;
	}

	/**
	 * 付款方标题
	 * @Description (TODO
	 * @return
	 */
	private static List<Object> getDraweeTitle() {
		List<Object> titleList = new ArrayList<Object>();
		titleList.add("批次号");
		titleList.add("付款日期");
		titleList.add("付款人email");
		titleList.add("账户名称");
		titleList.add("总金额（元）");
		titleList.add("总笔数");
		return titleList;
	}
	
	
	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		/*WritableWorkbook rw = jxl.Workbook.createWorkbook(new File("e://测试.xls"));
		
		WritableSheet sheet = rw.createSheet("测试。xls", 0);
		sheet.setColumnView(0, 20);
		
		Label label = new Label(0, 0, "HELLO WORLD", getTitle());
		sheet.addCell(label);
		
		rw.write();
		rw.close();*/
		
		/*List<String> title1= new ArrayList<String>();
		title1.add("批次号");
		title1.add("付款如期");
		title1.add("付款人");
		
		List<Object> data1= new ArrayList<Object>();
		data1.add("20160919001");
		data1.add("20160919");
		data1.add("iwancool@126.com");
		
		test(title1, data1, null, null);
		System.out.println("success...");*/
		
		/*File file = new File("D:/Users/hch/Downloads/template(结果).xls");
		WithdrawResultBean result = readAliPayExcel(file);
		System.out.println(JSON.toJSONString(result, true));*/
		
		/*List<List<Object>> contents = new ArrayList<List<Object>>();
		
		List<Object> list1 = new ArrayList<Object>();
		Collections.addAll(list1, "批次号", "付款日期", "付款人email", "账户名称", "总金额（元）", "总笔数");
		
		List<Object> list2 = new ArrayList<Object>();
		Collections.addAll(list2, "20160919001", "20160919", "iwancool@126.com", "深圳恒兴方圆科技有限公司", "3", "2");
		
		List<Object> list3 = new ArrayList<Object>();
		Collections.addAll(list3, "商户流水号", "收款人email", "收款人姓名", "付款金额（元）", "付款理由");
		
		List<Object> list4 = new ArrayList<Object>();
		Collections.addAll(list4, "1002", "fanwobiao520@163.com", "樊沃标", "1", "测试一下1");
		
		contents.add(list1);
		contents.add(list2);
		contents.add(list3);
		contents.add(list4);
		
		test(contents);
		System.out.println("success...");*/
		File file = new File("e://测试.xls");
		
		WritableWorkbook workBook = Workbook.createWorkbook(file);
		WritableSheet sheet = workBook.createSheet("sheet1", 0);
		
		WithdrawExportBean export = new WithdrawExportBean();
		export.setAccountName("深圳恒兴方圆科技有限公司");
		export.setBatchNo("20160929001");
		export.setDrawee("huchanghuan5845123@sina.com");
		export.setPayDay("20160929");
		export.setTotalAmount(10);
		export.setTotalNum(2);
		
		List<MerchantsRecordBean> merchantsRecordList = new ArrayList<MerchantsRecordBean>();
		
		MerchantsRecordBean merchants1 = new MerchantsRecordBean();
		merchants1.setAmount(3.60);
		merchants1.setUserId("4545456");
		merchants1.setPayee("hch123456@163.com");
		merchants1.setPayeeName("陈鑫");
		
		MerchantsRecordBean merchants2 = new MerchantsRecordBean();
		merchants2.setAmount(6.40);
		merchants2.setUserId("4545451");
		merchants2.setPayee("hch123456@126.com");
		merchants2.setPayeeName("jakson");
		
		merchantsRecordList.add(merchants1);
		merchantsRecordList.add(merchants2);
		export.setMerchantsRecordList(merchantsRecordList);
		
		writeExportData(sheet, export);
		
		workBook.write();
		workBook.close();
		System.out.println("success...");
	}
	
	/**
	 * @throws Exception 
	 * 
	 */
	public static void test(List<List<Object>> contents) throws Exception {
		WritableWorkbook rw = jxl.Workbook.createWorkbook(new File("e://测试.xls"));
		
		WritableSheet sheet = rw.createSheet("测试。xls", 0);
		
		
		//3创建单元格
		Label label = null;
		for(int rowIndex = 0,rowSize = contents.size(); rowIndex < rowSize;rowIndex++){
			List<Object> rowData = contents.get(rowIndex);
			for(int colIndex = 0, colSize = rowData.size(); colIndex < colSize;colIndex++){
				if (rowIndex == TITLE_ROW1 || rowIndex == TITLE_ROW2) {
					label = new Label(colIndex, rowIndex, rowData.get(colIndex)+"", getTitle());
				} else {
					label = new Label(colIndex, rowIndex, rowData.get(colIndex)+"", getNormal());
				}
				sheet.setColumnView(colIndex, 20);
				sheet.addCell(label);
			}
		}
		
		
		rw.write();
		rw.close();
	}
}