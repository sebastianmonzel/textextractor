package de.sebastianmonzel.textextractor;

import java.io.File;
import java.io.InputStream;
import java.util.Locale;

public abstract class AbstractTextExtractor {

    protected String text;

    public abstract AbstractTextExtractor extractText(File file);

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

        TxtTextExtractor txtTextExtractor = new TxtTextExtractor();
        String stopwordText = txtTextExtractor.extractText(stopwordFile).getText();

        return stopwordText.split( System.getProperty("line.separator"));
    }

    private File resolveStopwordFileByLocale(Locale locale) {
        ClassLoader classLoader = AbstractTextExtractor.class.getClassLoader();
        File file = new File(classLoader.getResource("stopwords_" + locale.getLanguage() + ".txt").getFile());
        return file;
    }

    public abstract AbstractTextExtractor extractText(InputStream inputStream);

    public String getText() {
        return text;
    }
}
