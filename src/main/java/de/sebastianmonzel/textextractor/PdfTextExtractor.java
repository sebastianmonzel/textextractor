package de.sebastianmonzel.textextractor;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

import java.io.File;
import java.io.InputStream;

public class PdfTextExtractor extends TextExtractor {

    public PdfTextExtractor() {
    }

    @Override
    public String extractText(File file) {
        try {
            PDDocument document = PDDocument.load(file);
            PDFTextStripper stripper = new PDFTextStripper();

            String content = stripper.getText(document);
            document.close();

            return content;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String extractText(InputStream inputStream) {
        return null;
    }
}