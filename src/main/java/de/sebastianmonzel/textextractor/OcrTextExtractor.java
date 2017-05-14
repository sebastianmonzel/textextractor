package de.sebastianmonzel.textextractor;

import java.io.*;

public class OcrTextExtractor extends AbstractTextExtractor {

    public static OcrTextExtractor of(File file) {
        return new OcrTextExtractor(file);
    }

    public static OcrTextExtractor of(InputStream file) {
        return new OcrTextExtractor(file);
    }

    public static OcrTextExtractor of(String text) {
        return new OcrTextExtractor(text);
    }

    public OcrTextExtractor(File file) {
        super(file);
    }

    public OcrTextExtractor(InputStream inputStream) {
        super(inputStream);
    }

    public OcrTextExtractor(String inputStream) {
        super(inputStream);
    }


    @Override
    public AbstractTextExtractor extractText() {
        return null;
    }
}
