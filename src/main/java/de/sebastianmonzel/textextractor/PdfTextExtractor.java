package de.sebastianmonzel.textextractor;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

import java.io.*;

public class PdfTextExtractor extends AbstractTextExtractor {


    public static PdfTextExtractor of(File file) {
        return new PdfTextExtractor(file);
    }

    public static PdfTextExtractor of(InputStream file) {
        return new PdfTextExtractor(file);
    }

    public PdfTextExtractor(File file) {
        super(file);
    }

    public PdfTextExtractor(InputStream inputStream) {
       super(inputStream);
    }

    @Override
    public AbstractTextExtractor extractText() throws IOException {

        PDDocument document = PDDocument.load(inputStream);
        PDFTextStripper stripper = new PDFTextStripper();

        String content = stripper.getText(document);
        document.close();

        text = content;

        return this;
    }

}