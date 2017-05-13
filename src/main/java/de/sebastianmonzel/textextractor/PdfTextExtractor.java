package de.sebastianmonzel.textextractor;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

import java.io.File;
import java.io.InputStream;

public class PdfTextExtractor extends AbstractTextExtractor {

    @Override
    public AbstractTextExtractor extractText(File file) {
        try {
            PDDocument document = PDDocument.load(file);
            PDFTextStripper stripper = new PDFTextStripper();

            String content = stripper.getText(document);
            document.close();

            text = content;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    @Override
    public AbstractTextExtractor extractText(InputStream inputStream) {
        return null;
    }
}