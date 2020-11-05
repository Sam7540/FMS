package com.system.fms;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/*
 * @author Soumyajit
 */
class PdfHandler {
    public static void makePdf(String name, String branch, String id) throws Exception{
         int invoiceno = 61450, sem = 3; 
        String pay = String.format("%-50s", "32300.00/-");
        String line = "-";
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("D:/new.pdf"));
        document.open();
        LocalDateTime datetime = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String date = datetime.format(format);
        float[] columnwidth = {150f, 150f};
        PdfPTable table1 = new PdfPTable(columnwidth);
        table1.setWidthPercentage(100);
        table1.setSpacingBefore(10f);
        
        Font f = new Font();
        f.setStyle(Font.BOLD);
        f.setSize(18);
        document.add(new Paragraph("SAL ENGINEERING AND TECHNICAL INSTITUTE",f));
     
        PdfPCell c1 = new PdfPCell(new Paragraph("Invoice no.: "+invoiceno));
        c1.setBorderColor(BaseColor.WHITE);
        PdfPCell c2 = new PdfPCell(new Paragraph("Date: "+date));
        c2.setBorderColor(BaseColor.WHITE);
        table1.addCell(c1);
        table1.addCell(c2);
        document.add(table1);
        for(int i=0; i<110; i++) line += "-";
        document.add(new Paragraph(line));
        PdfPCell c3 = new PdfPCell(new Paragraph("Name: "+name));
        c3.setBorderColor(BaseColor.WHITE);
        PdfPCell c4 = new PdfPCell(new Paragraph("Enrollment No.: "+id));
        c4.setBorderColor(BaseColor.WHITE);
        PdfPCell c5 = new PdfPCell(new Paragraph("Branch: "+branch));
        c5.setBorderColor(BaseColor.WHITE);
        PdfPCell c6 = new PdfPCell(new Paragraph("Sem: "+sem));
        c6.setBorderColor(BaseColor.WHITE);
        PdfPTable table2 = new PdfPTable(columnwidth);
        table2.setWidthPercentage(100);
        table2.setSpacingBefore(5f);
        table2.addCell(c3);
        table2.addCell(c4);
        
        document.add(table2);
        document.add(new Paragraph("Amount paid: "+pay+"Payment Mode: Check"));
        f.setSize(7);
        document.add(new Paragraph("\nNote: This Invoice is Digitally Signed",f));
        document.close();
        
    }
}
