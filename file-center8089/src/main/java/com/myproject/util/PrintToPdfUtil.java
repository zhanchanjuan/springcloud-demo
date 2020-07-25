package com.myproject.util;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;


import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * 生成pdf
 * @author shuyi
 * @date 2020/7/23
 */
public class PrintToPdfUtil {
    /**
     * @param imageFolderPath   图片文件夹地址
     * @param pdfPath PDF文件保存地址
     */
    public static void toPdf(String imageFolderPath, String pdfPath) {
        try {
            // 图片文件夹地址
            // String imageFolderPath = "D:/Demo/ceshi/";
            // 图片地址
            String imagePath = null;
            // PDF文件保存地址
            // String pdfPath = "D:/Demo/ceshi/hebing.pdf";
            // 输入流
            FileOutputStream fos = new FileOutputStream(pdfPath);

            // 创建pdf文档
            Document doc =new Document();
            // 写入PDF文档
            PdfWriter.getInstance(doc,fos);
//            doc.open();
            // 读取图片流
            BufferedImage img = null;
            // 实例化图片
            Image image = null;
            // 获取图片文件夹对象
            File file = new File(imageFolderPath);
            File[] files = file.listFiles();
            // 循环获取图片文件夹内的图片
            for (File file1 : files) {
                if (file1.getName().endsWith(".png") || file1.getName().endsWith(".jpg")
                        || file1.getName().endsWith(".gif") || file1.getName().endsWith(".jpeg")
                        || file1.getName().endsWith(".tif")) {
                    // System.out.println(file1.getName());
                    imagePath = imageFolderPath + file1.getName();
                    // 读取图片流
                    img = ImageIO.read(new File(imagePath));
                    // 根据图片大小设置文档大小
                    Rectangle rect =new Rectangle(img.getWidth(), img.getHeight());
                    doc.setPageSize(rect);
                    // 实例化图片
                    image = Image.getInstance(imagePath);
                    // 添加图片到文档
                    doc.open();
                    doc.add(image);
                }
            }
            // 关闭文档
            doc.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BadElementException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public static void firstPdf(){
        try{
            Document document = new Document();
            document.addTitle("第一个pdf文件");
            document.addSubject("java实现生成pdf");
            document.addAuthor("shuyi");
            document.addKeywords("基础功能实现实战");
            Rectangle rec =new Rectangle(300,500);
            document.setPageSize(rec);
            //没有指定文件存的目录，就默认在项目根目录下
            PdfWriter.getInstance(document, new FileOutputStream("Helloworld2.PDF"));
            //3.1添加页号
//            HeaderFooter footer = new HeaderFooter(new Phrase("页码：",keyfont), true);
//            footer.setBorder(Rectangle.NO_BORDER);
//            document.setPageCount();

            //3.2打开文档
            document.open();
            //Chunk 文本块
//            Chunk chunk1 = new Chunk("This text is underlined", FontFactory.getFont(FontFactory.HELVETICA, 3, Font.UNDERLINE));
            //Phrase 短语
//            Phrase phrase=new Phrase();
//            phrase.add(chunk1);
//            document.add(phrase);
            //Paragraph 段落
//            document.add(new Paragraph("Hello Word222"));
            //pdf中 中文处理
            BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            Font FontChinese = new Font(bfChinese, 12, Font.NORMAL);
            document.add(new Paragraph("我是一只小小鸟，pdf打印测试中文字体显示"));
            document.close();
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        firstPdf();
        System.out.println("Pdf生成成功!");
    }

//    public static void main(String[] args) {
//        long time1 = System.currentTimeMillis();
//        toPdf("E:\\application\\图片(1)\\图片\\关于",
//                "F:\\file\\docker.pdf");
//        long time2 = System.currentTimeMillis();
//        int time = (int) ((time2 - time1) / 1000);
//        System.out.println("执行了：" + time + "秒！");
//    }
}



