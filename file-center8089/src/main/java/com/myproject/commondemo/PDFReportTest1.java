package com.myproject.commondemo;


import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;

import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;

import java.io.FileOutputStream;




/**
 * @author shuyi
 * @date 2020/7/24
 */
public class PDFReportTest1 {
    @Autowired
    private CreateTable createTable;

    @Autowired
    private CreateCell createCell;



    Document document=new Document();
    //设置字体大小
    public static Font headfont;
    public static Font textfont;
    public static Font keyfont;

    static{
        BaseFont bfChinese;
        try {
            bfChinese = BaseFont.createFont("STSong-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
            headfont =new Font(bfChinese,10, Font.BOLD);
            textfont =new Font(bfChinese,8, Font.BOLD);
            keyfont =new Font(bfChinese,8, Font.BOLD);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public PDFReportTest1(File file){
        //设置页面大小
        document.setPageSize(PageSize.A4);
        try {
            PdfWriter.getInstance(document,new FileOutputStream(file));
            document.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    /**
     * 添加数据，生产pdf
     * @throws Exception 异常
     */
    public void generatePDF() throws Exception{
        PdfPTable table = createTable.createTable(4);
        table.addCell(createCell.createCell("学生信息列表：", keyfont, Element.ALIGN_LEFT,4,false));
        table.addCell(createCell.createCell("姓名", keyfont, Element.ALIGN_CENTER));
        table.addCell(createCell.createCell("年龄", keyfont, Element.ALIGN_CENTER));
        table.addCell(createCell.createCell("性别", keyfont, Element.ALIGN_CENTER));
        table.addCell(createCell.createCell("住址", keyfont, Element.ALIGN_CENTER));

        for(int i=0;i<5;i++){
            table.addCell(createCell.createCell("姓名"+i, textfont));
            table.addCell(createCell.createCell(i+15+"", textfont));
            table.addCell(createCell.createCell((i%2==0)?"男":"女", textfont));
            table.addCell(createCell.createCell("地址"+i, textfont));
        }
        document.add(table);
        document.close();
    }

    //失败
    public static void main(String[] args) throws Exception {
        File file = new File("F:\\study\\SpringCould\\project\\text.pdf");
        file.createNewFile();
        new PDFReportTest1(file).generatePDF();
    }



}
