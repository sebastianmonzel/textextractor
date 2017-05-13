package de.sebastianmonzel.textextractor;

import java.io.*;
import java.util.Locale;

public abstract class AbstractTextExtractor {

    protected String text;
    protected InputStream inputStream;

    public abstract AbstractTextExtractor extractText() throws IOException;

    public AbstractTextExtractor removeStopwords(Locale locale) {

        String[] stopwords = readStopwords(locale);

        String replacedText = text;
        for (String stopword : stopwords) {
            replacedText = replacedText.replaceAll(" " + stopword.trim() + " "," ");
            replacedText = replacedText.replaceAll( stopword.trim() + " ","");
            replacedText = replacedText.replaceAll(" " + stopword.trim(),"");
        }

        text = replacedText;

        return this;
    }

    private String[] readStopwords(Locale locale) {
        File stopwordFile = resolveStopwordFileByLocale(locale);

        String stopwordText = TxtTextExtractor
                .of(stopwordFile)
                .extractText()
                .getText();

        return stopwordText.split( System.getProperty("line.separator"));
    }

    private File resolveStopwordFileByLocale(Locale locale) {
        ClassLoader classLoader = AbstractTextExtractor.class.getClassLoader();
        File file = new File(classLoader.getResource("stopwords_" + locale.getLanguage() + ".txt").getFile());
        return file;
    }

    public String getText() {
        return text;
    }
}
