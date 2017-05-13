package de.sebastianmonzel.textextractor;

import java.io.File;
import java.io.InputStream;
import java.util.Locale;

public abstract class AbstractTextExtractor {

    private String text;

    public abstract String extractText(File file);

    public String removeStopwords(Locale locale, String text) {

        String[] stopwords = readStopwords(locale);

        String replacedText = text;
        for (String stopword : stopwords) {
            replacedText = replacedText.replaceAll(" " + stopword + " "," ");
            replacedText = replacedText.replaceAll( stopword + " ","");
            replacedText = replacedText.replaceAll(" " + stopword,"");
        }

        return replacedText;
    }

    private String[] readStopwords(Locale locale) {
        File stopwordFile = resolveStopwordFileByLocale(locale);

        TxtTextExtractor txtTextExtractor = new TxtTextExtractor();
        String stopwordText = txtTextExtractor.extractText(stopwordFile);

        return stopwordText.split("\r\n");
    }

    private File resolveStopwordFileByLocale(Locale locale) {
        ClassLoader classLoader = AbstractTextExtractor.class.getClassLoader();
        File file = new File(classLoader.getResource("stopwords_" + locale.getLanguage() + ".txt").getFile());
        return file;
    }

    public abstract String extractText(InputStream inputStream);
}
