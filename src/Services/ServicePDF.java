/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entities.Commande;
import entities.Medicament;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ServicePDF {
    
    
    public static void printPDF(Commande c) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String currentDate = dateFormat.format(new Date());

        String file_name = "D:\\PDFPI\\" + currentDate + "_" + c.getID_commande() + "_" + c.getNom_pharmacie() + ".pdf";
        Document doc = new Document();
        PdfWriter.getInstance(doc, new FileOutputStream(file_name));
            
            doc.open();
            
            // Create a title paragraph
            Paragraph title = new Paragraph("Commande Details", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16));
            title.setAlignment(Element.ALIGN_CENTER);
            doc.add(title);
            
            // Add empty line
            doc.add(new Paragraph("\n"));
            
            // Create a table for the details
            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(60);
            table.setWidths(new int[]{1, 3});
            
            // Add details as table cells
            //table.addCell(createTableCell("ID Commande"));
           // table.addCell(createTableCell(String.valueOf(c.getID_commande())));
            
           // table.addCell(createTableCell("ID Pharmacie"));
            //table.addCell(createTableCell(String.valueOf(c.getID_pharmacie())));
            
            table.addCell(createTableCell("Adresse"));
            table.addCell(createTableCell(c.getAdresse()));
            
            table.addCell(createTableCell("Nom Pharmacie"));
            table.addCell(createTableCell(c.getNom_pharmacie()));
            
            table.addCell(createTableCell("Date Commande"));
            table.addCell(createTableCell(c.getDate_commande().toString()));
            
            table.addCell(createTableCell("Etat"));
            table.addCell(createTableCell(c.getEtat()));
            
            doc.add(table);
            
            // Add empty line
            doc.add(new Paragraph("\n"));
            doc.add(new Paragraph("\n"));
            
            // Create a title ordonnance
            Paragraph ordonnance = new Paragraph("Ordonnance", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14));
            title.setAlignment(Element.ALIGN_CENTER);
            doc.add(ordonnance);
            
            // Add empty line
            doc.add(new Paragraph("\n"));
            
            // Get Medicament List
            ServiceCommande sc = new ServiceCommande();
            List<Medicament> meds = new ArrayList<>();
            meds= sc.getMedFromComm(c);
            
            // Create a table for the medications
        PdfPTable medTable = new PdfPTable(3);
        medTable.setWidthPercentage(80);
        medTable.setWidths(new int[]{1, 2, 1});

        // Add table headers
        //medTable.addCell(createTableCell("ID Médicament"));
        medTable.addCell(createTableCell("Nom Médicament"));
        medTable.addCell(createTableCell("Prix"));
        medTable.addCell(createTableCell("Catégorie"));

        // Add medications as table cells
        for (Medicament med : meds) {
           // medTable.addCell(createTableCell(String.valueOf(med.getID_medicament())));
            medTable.addCell(createTableCell(med.getNom_medicament()));
            medTable.addCell(createTableCell(String.valueOf(med.getPrix())));
            medTable.addCell(createTableCell(med.getCategorie()));
        }
            doc.add(medTable);
            doc.add(new Paragraph("\n"));
            doc.add(new Paragraph("\n"));
            
            // Create a title ordonnance
            Paragraph total = new Paragraph("TOTALE", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14));
            title.setAlignment(Element.ALIGN_CENTER);
            doc.add(total);
            doc.add(new Paragraph("\n"));
        PdfPTable totale = new PdfPTable(1);
        totale.setWidthPercentage(80);
        totale.setWidths(new int[]{1});
        totale.addCell(createTableCell("Prix Totale"));
        float tot=0;
        for (Medicament med : meds) {
           // medTable.addCell(createTableCell(String.valueOf(med.getID_medicament())));
           tot+=med.getPrix();
        }
        totale.addCell(createTableCell(String.valueOf(tot)));
        
        doc.add(totale);
            
            doc.close();
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    // Helper method to create a table cell with content
    private static PdfPCell createTableCell(String content) {
        PdfPCell cell = new PdfPCell(new Phrase(content, FontFactory.getFont(FontFactory.HELVETICA, 12)));
        cell.setPadding(5);
        return cell;
    }
}

