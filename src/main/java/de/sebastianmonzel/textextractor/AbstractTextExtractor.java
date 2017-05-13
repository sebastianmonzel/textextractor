package de.sebastianmonzel.textextractor;

import java.io.*;
import java.util.Locale;

public abstract class AbstractTextExtractor {

    public static final String LINE_SEPARATOR = "line.separator";
    protected String text;
    protected InputStream inputStream;

    public abstract AbstractTextExtractor extractText() throws IOException;

    public AbstractTextExtractor removeStopwords(Locale locale) {

        String[] stopwords = readStopwords(locale);

        for (String stopword : stopwords) {
            text = text.replaceAll(" " + stopword.trim() + " "," ");
            text = text.replaceAll( stopword.trim() + " ","");
            text = text.replaceAll(" " + stopword.trim(),"");
        }
        return this;
    }

    private String[] readStopwords(Locale locale) {
        File stopwordFile = resolveStopwordFileByLocale(locale);

        String stopwordText = TxtTextExtractor
                .of(stopwordFile)
                .extractText()
                .getText();

        return stopwordText.split(System.getProperty(LINE_SEPARATOR));
    }

    public AbstractTextExtractor removePunctuationMarks() {

        String[] punctuationMarks = {"\\,","\\;","\\!","\\.","\\?"};

        for (String punctuationMark : punctuationMarks) {
            text = text.replaceAll(punctuationMark.trim(),"");
        }
        return this;
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
