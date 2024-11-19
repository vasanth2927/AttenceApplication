package com.example.AttendenceApp.Service;

import java.io.FileOutputStream;

import org.springframework.stereotype.Service;

import com.example.AttendenceApp.Entity.Payroll;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

@Service
public class PdfGeneratorService {
    public void generatePayrollPdf(Payroll payroll, String filePath) {
        try {
            // Initialize PdfWriter and PdfDocument
            PdfWriter writer = new PdfWriter(new FileOutputStream(filePath));
            PdfDocument pdf = new PdfDocument(writer);

            // Initialize the Document layout object from iText
            Document document = new Document(pdf);

            // Add content to the PDF
            document.add(new Paragraph("Employee Name: " + payroll.getEmployee().getUsername()));
            document.add(new Paragraph("Payroll Date: " + payroll.getPayrollDate()));
            document.add(new Paragraph("Total Salary: " + payroll.getTotalSalary()));
            document.add(new Paragraph("Deductions: " + payroll.getDeductions()));
            document.add(new Paragraph("Net Salary: " + payroll.getNetSalary()));

            // Close the document
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
