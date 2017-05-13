package de.sebastianmonzel.textextractor;

import java.io.*;

public class OcrTextExtractor extends AbstractTextExtractor {

    public static OcrTextExtractor of(File file) {
        return new OcrTextExtractor(file);
    }

    public static OcrTextExtractor of(InputStream file) {
        return new OcrTextExtractor(file);
    }

    public OcrTextExtractor(File file) {
        try {
            this.inputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public OcrTextExtractor(InputStream file) {
        this.inputStream = file;
    }


    @Override
    public AbstractTextExtractor extractText() throws IOException {
        return null;
    }
}
