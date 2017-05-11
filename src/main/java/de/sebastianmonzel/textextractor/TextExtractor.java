package de.sebastianmonzel.textextractor;

import java.io.File;

public abstract class TextExtractor {

    public abstract String extractText(File file);

    public String removeStopWords(String text) {
        return "";

    }
}
