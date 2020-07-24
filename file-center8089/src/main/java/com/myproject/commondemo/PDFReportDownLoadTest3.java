package com.myproject.commondemo;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.myproject.pojo.BigQuestion;
import com.myproject.pojo.Exampaper;
import com.myproject.pojo.Exampaperoption;
import com.myproject.pojo.Exampaperquestion;


import java.io.FileOutputStream;


/**
 * pdf生成并下载
 * @author shuyi
 * @date 2020/7/24
 */
public class PDFReportDownLoadTest3 {

    private static Font headfont;
    private static Font keyfont;
    private static Font textfont;

    static {
        BaseFont bfChinese;
        try {
            bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            headfont = new Font(bfChinese, 10, Font.BOLD);
            keyfont = new Font(bfChinese, 9, Font.BOLD);
            textfont = new Font(bfChinese, 8, Font.NORMAL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 将一份试卷写到指定的位置为pdf
     * @param exampaper  数据源
     * @param url 写到位置的pdf的全路径
     * @throws Exception 抛出异常
     */
    public void writeExampaperPdf(Exampaper exampaper, String url) throws Exception {
        // 1.新建document对象
        // 第一个参数是页面大小。接下来的参数分别是左、右、上和下页边距。
        Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        // 2.建立一个书写器(Writer)与document对象关联，通过书写器(Writer)可以将文档写入到磁盘中。
        // 创建 PdfWriter 对象 第一个参数是对文档对象的引用，第二个参数是文件的实际名称，在该名称中还会给出其输出路径。
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(url));
        // 3.添加页号打开文档
        //3.1添加页号
//        HeaderFooter footer = new HeaderFooter(new Phrase("第", textfont), new Phrase("页", textfont))
//        footer.setBorder(Rectangle.NO_BORDER);
//        document.setFooter(footer);
        //3.2打开文档
        document.open();
        // 4.向文档中添加内容
        // 通过 com.lowagie.text.Paragraph 来添加文本。可以用文本及其默认的字体、颜色、大小等等设置来创建一个默认段落
        // 将标题写进去
        Paragraph pt = new Paragraph(exampaper.getTitle(), headfont);
        // 设置文字居中 0靠左 1，居中 2，靠右
        pt.setAlignment(1);
        document.add(pt);

        //获取大题 题干和内容
        java.util.List<BigQuestion> bigQuestions = exampaper.getBigQuestions();
        for (int i = 0; bigQuestions != null && i < bigQuestions.size(); i++) {
            // 添加段落分隔符 换行
            document.add(new Paragraph("\n"));
            // 大题题干写进去
            document.add(new Paragraph(bigQuestions.get(i).getBigquestionname(),keyfont));

            java.util.List<Exampaperquestion> questions = bigQuestions.get(i).getQuestions();
            for (int j = 0; questions != null && j < questions.size(); j++) {
//                document.add(new Paragraph("\n"));// 添加段落分隔符 换行
                document.add(new Paragraph(questions.get(j).getQuestionsequence().toString() + ".\t"
                        // 将小题的题干与序号写进去
                        + questions.get(j).getQuestioncontent(),textfont));
                // 获取选项
                java.util.List<Exampaperoption> options = questions.get(j).getOptions();
                for (int k = 0; options != null && k < options.size(); k++) {
//                    document.add(new Paragraph("\n"));// 添加段落分隔符 换行
                    document.add(new Paragraph("\t"+
                            // 将选项序号与题干写进去
                            options.get(k).getOptionsequence() + "\t" + options.get(k).getOptioncontent(),textfont));
                }
            }

        }
        // 5.关闭文档
        document.close();
    }

    public static void main(String[] args) throws Exception {
        System.out.println("begin");
        Exampaper exampaper = new Exampaper();
        String url = null;
        PDFReportDownLoadTest3 ppt=new PDFReportDownLoadTest3();
        ppt.writeExampaperPdf(exampaper,url);
        System.out.println("end");
    }
}
