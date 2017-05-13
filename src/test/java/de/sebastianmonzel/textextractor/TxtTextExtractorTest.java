package de.sebastianmonzel.textextractor;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.InputStream;
import java.util.Locale;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class TxtTextExtractorTest {

    public static final String PATHNAME = "";
    public static final File FILE = new File(PATHNAME);

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void extractText() throws Exception {

        InputStream resourceAsStream = getInputStreamFromResource("test1234.txt");

        TxtTextExtractor txtTextExtractor = new TxtTextExtractor();
        String s = txtTextExtractor.extractText(resourceAsStream);

        assertThat(s,is("test1234"));
    }

    @Test
    public void extractTextWithoutStopwords() throws Exception {

        InputStream resourceAsStream = getInputStreamFromResource("sometextwithstopwords.txt");

        TxtTextExtractor txtTextExtractor = new TxtTextExtractor();
        String s = txtTextExtractor.extractText(resourceAsStream);
        String textWithoutStopwords = txtTextExtractor.removeStopwords(Locale.GERMAN, s);

        assertThat(textWithoutStopwords,is("test"));
    }

    private InputStream getInputStreamFromResource(String name) {
        return getClass().getClassLoader().getResourceAsStream(name);
    }


}