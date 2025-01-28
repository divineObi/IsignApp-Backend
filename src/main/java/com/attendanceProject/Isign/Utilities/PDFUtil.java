package com.attendanceProject.Isign.Utilities;

import com.attendanceProject.Isign.Payload.AttendanceRecordResponse;
import com.itextpdf.io.exceptions.IOException;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.TextAlignment;

import java.io.ByteArrayOutputStream;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PDFUtil {

    public static byte[] generateAttendancePDF(List<AttendanceRecordResponse> records, String courseTitle) throws IOException {
        // Create a PDF document in memory
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(byteArrayOutputStream);
        PdfDocument pdfDocument = new PdfDocument(writer);
        Document document = new Document(pdfDocument);

        // Add a title
        document.add(new Paragraph("Attendance Report for " + courseTitle)
                .setBold()
                .setFontSize(18)
                .setTextAlignment(TextAlignment.CENTER)
        );

        document.add(new Paragraph("\n")); // Add a line break

        // Define the table with 4 columns: S/N, Name, Registration Number, Attendance Time
        Table table = new Table(4);
        table.addHeaderCell(new Cell().add(new Paragraph("S/N").setBold()));
        table.addHeaderCell(new Cell().add(new Paragraph("Student Name").setBold()));
        table.addHeaderCell(new Cell().add(new Paragraph("Registration Number").setBold()));
        table.addHeaderCell(new Cell().add(new Paragraph("Attendance Time").setBold()));

        // Format the attendance time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Add rows to the table with an incrementing serial number
        int serialNumber = 1;
        for (AttendanceRecordResponse record : records) {
            table.addCell(new Cell().add(new Paragraph(String.valueOf(serialNumber++))));
            table.addCell(new Cell().add(new Paragraph(record.getStudentName())));
            table.addCell(new Cell().add(new Paragraph(record.getRegNumber())));
            table.addCell(new Cell().add(new Paragraph(record.getAttendanceTime().format(formatter))));
        }

        document.add(table);
        document.close();

        return byteArrayOutputStream.toByteArray();
    }
}

