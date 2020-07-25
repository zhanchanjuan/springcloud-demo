package com.myproject.util;


import com.myproject.pojo.ExportExcelView;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @author shuyi
 * @date 2020/7/22
 */
public class ExportExcelUtil {
    /**
     * 创建表格标题
     * @param wb   Excel文档对象
     * @param sheet  工作表对象
     * @param headString  标题名称
     * @param col  标题占用列数
     */
    @SuppressWarnings("deprecation")
    public static void createHeadTittle(HSSFWorkbook wb, HSSFSheet sheet, String headString, int col) {
        // 创建Excel工作表的行
        HSSFRow row = sheet.createRow(0);
        // 创建Excel工作表指定行的单元格
        HSSFCell cell = row.createCell(0);
        // 设置高度
        row.setHeight((short) 1000);
        // 定义单元格为字符串类型
        cell.setCellType(HSSFCell.ENCODING_UTF_16);
        cell.setCellValue(new HSSFRichTextString(headString));

        // 指定标题合并区域
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, col));

        // 定义单元格格式，添加单元格表样式，并添加到工作簿
        HSSFCellStyle cellStyle = wb.createCellStyle();
        // 指定单元格居中对齐
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        //垂直
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        // 自动换行
        cellStyle.setWrapText(true);
        // 设置单元格字体
        HSSFFont font = wb.createFont();
        font.setBold(false);
        font.setFontName("宋体");
        // 字体大小
        font.setFontHeightInPoints((short) 32);
        cellStyle.setFont(font);
        //将设置的状态添加到指定行的表格
        cell.setCellStyle(cellStyle);
    }

    /**
     * 创建表头
     *
     * @param wb  Excel文档对象
     *
     * @param sheet 工作表对象
     *
     * @param thead 表头内容
     *
     * @param sheetWidth   每一列宽度
     *
     */
    @SuppressWarnings("deprecation")
    public static void createThead(HSSFWorkbook wb, HSSFSheet sheet, String[] thead, int[] sheetWidth) {
        HSSFRow row1 = sheet.createRow(1);
        row1.setHeight((short) 600);
        // 定义单元格格式，添加单元格表样式，并添加到工作簿
        HSSFCellStyle cellStyle = wb.createCellStyle();
        // 指定单元格居中对齐
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        //垂直
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        // 自动换行
        cellStyle.setWrapText(true);
        // 设置背景色
        cellStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
//        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        // 设置右边框类型  ---右边框
        cellStyle.setBorderRight(BorderStyle.THIN);

        // 设置右边框颜色
        cellStyle.setRightBorderColor(HSSFColor.BLACK.index);
        // 设置单元格字体
        HSSFFont font = wb.createFont();
        font.setBold(false);
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 10);
        cellStyle.setFont(font);

        // 设置表头内容
        for (int i = 0; i < thead.length; i++) {
            HSSFCell cell1 = row1.createCell(i);
            cell1.setCellType(HSSFCell.ENCODING_UTF_16);
            cell1.setCellValue(new HSSFRichTextString(thead[i]));
            cell1.setCellStyle(cellStyle);
        }

        // 设置每一列宽度
        for (int i = 0; i < sheetWidth.length; i++) {
            sheet.setColumnWidth(i, sheetWidth[i]);
        }
    }

    /**
     * 填入数据
     *
     * @param wb
     *            // Excel文档对象
     * @param sheet
     *            // 工作表对象
     * @param result
     *            // 表数据
     */
    @SuppressWarnings("deprecation")
    public static void createTable(HSSFWorkbook wb, HSSFSheet sheet, List<LinkedHashMap<String, String>> result) {
        // 定义单元格格式，添加单元格表样式，并添加到工作薄
        HSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setWrapText(true);

        // 单元格字体
        HSSFFont font = wb.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 10);
        cellStyle.setFont(font);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);

        // 循环插入数据
        for (int i = 0; i < result.size(); i++) {
            HSSFRow row = sheet.createRow(i + 2);
            // 设置高度
            row.setHeight((short) 400);
            HSSFCell cell = null;
            int j = 0;
            for (String key : (result.get(i).keySet())) {
                cell = row.createCell(j);
                cell.setCellStyle(cellStyle);
                cell.setCellValue(new HSSFRichTextString(result.get(i).get(key)));
                j++;
            }
        }
    }

    public static void main(String[] args) {
        //测试hashmap  treemap  linkedhashmap之间的顺序
 		/*Map<String, String>  map=new HashMap<>();
 		System.out.println("hashmap排序");
 		add_keyvalue(map);
 		TreeMap<String, String>  map2=new TreeMap<>();
 		System.out.println("treemap排序");
 		add_keyvalue(map2);
 		LinkedHashMap<String, String>  map3=new LinkedHashMap<>();
 		System.out.println("linkedhash排序");
 		add_keyvalue(map3);*/

        // 1.封装数据
        List<ExportExcelView> list = new LinkedList<>();
        ExportExcelView b1 = new ExportExcelView();
        b1.setDeclsno("201810251706470169854601");
        b1.setDecdt("20120-09-22");
        b1.setEleacno("1209394999");
        b1.setCustName("张三");
        b1.setEntName("正信广电");
        b1.setSaleName("郭启铭");
        b1.setSaleTel("17342064227");
        b1.setRealsumretbal("1000");
        b1.setDecutionFee("100");

        ExportExcelView b2 = new ExportExcelView();
        b2.setDeclsno("201810251706470176052618");
        b2.setDecdt("2020-09-22");
        b2.setEleacno("1209394999");
        b2.setCustName("赵四");
        b2.setEntName("正信广电");
        b2.setSaleName("郭启铭");
        b2.setSaleTel("17342064227");
        b2.setRealsumretbal("2000");
        b2.setDecutionFee("200");
        list.add(b1);
        list.add(b2);

        // 实体类转换为map
        List<LinkedHashMap<String, String>> result = new ArrayList<>();
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        for (ExportExcelView e : list) {
            map.put("declsno", e.getDeclsno());
            map.put("decdt", e.getDecdt());
            map.put("eleacno", e.getEleacno());
            map.put("custName",e.getCustName());
            map.put("entName",e.getEntName());
            map.put("saleName",e.getSaleName());
            map.put("saleTel",e.getSaleTel());
            map.put("realsumretbal",e.getRealsumretbal());
            map.put("decutionFee",e.getDecutionFee());
            result.add(map);
        }

        // 2.定义变量值 创建Excel文件
        // 定义文件名
        String fileName = "正信广电_202009代扣费用表.xls";
        // 定义表格标题
        String headString = "正信广电_202009代扣费用表";
        // 定义工作表表名
        String sheetName = "正信广电_202009代扣费用表";
        // 文件本地保存路径
        String filePath = "D:\\";
        String[] thead = { "扣款流水", "扣款日期", "发电户号", "用户姓名", "开发商",
                "业务员姓名","业务员手机号","扣款金额(元)", "代扣费用(元)" };
        // 定义每一列宽度
        int[] sheetWidth = { 7500, 4000, 3000, 3000, 4000, 3000, 5000, 5000,5000};

        // 创建Excel文档对象
        HSSFWorkbook wb = new HSSFWorkbook();
        // 创建工作表
        HSSFSheet sheet = wb.createSheet(sheetName);

        // 3.生成表格
        // ①创建表格标题
        createHeadTittle(wb, sheet, headString, 8);
        // result.get(0).size() - 1为表格占用列数，从0开始
        // ②创建表头
        createThead(wb, sheet, thead, sheetWidth);
        // ③填入数据
        createTable(wb, sheet, result);

        FileOutputStream fos;
        try {
            fos = new FileOutputStream(new File(filePath + fileName));
            wb.write(fos);
            fos.close();
            wb.close();
            System.out.println("导出excel成功");
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }



}
