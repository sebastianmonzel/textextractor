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
        super(file);
    }

    public OcrTextExtractor(InputStream inputStream) {
        super(inputStream);
    }


    @Override
    public AbstractTextExtractor extractText() throws IOException {
        return null;
    }
}
