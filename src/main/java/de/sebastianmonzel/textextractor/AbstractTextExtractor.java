package de.sebastianmonzel.textextractor;

import java.io.*;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractTextExtractor {

    public static final String LINE_SEPARATOR = "line.separator";
    protected String text;
    protected InputStream inputStream;

    public AbstractTextExtractor(File file) {
        try {
            this.inputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public AbstractTextExtractor(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public AbstractTextExtractor(String text) {
        this.text = text;
    }

    public abstract AbstractTextExtractor extractText();

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



    private File resolveStopwordFileByLocale(Locale locale) {
        ClassLoader classLoader = AbstractTextExtractor.class.getClassLoader();
        File file = new File(classLoader.getResource("stopwords_" + locale.getLanguage() + ".txt").getFile());
        return file;
    }

    public AbstractTextExtractor removePunctuationMarks() {

        String[] punctuationMarks = {"\\,","\\;","\\!","\\.","\\?"};

        for (String punctuationMark : punctuationMarks) {
            text = text.replaceAll(punctuationMark.trim(),"");
        }
        return this;
    }

    public AbstractTextExtractor removeXmlTags() {

        text = text.replaceAll("<[^>]+>", "");
        return this;
    }

    public AbstractTextExtractor filterByRegularExpression(String regularExpression) {

        final Pattern pattern = Pattern.compile(regularExpression);
        final Matcher matcher = pattern.matcher(text);

        if ( matcher.find() ) {
            text = matcher.group(1);
        } else {
            System.err.println("Pattern '" + regularExpression + "' not found. Will ignore it.");
            text = null;
        }

        return this;
    }

    public AbstractTextExtractor normalizeWhitespace() {


        return this;
    }


    public String getText() {
        return text;
    }
}
