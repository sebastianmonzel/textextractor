package de.sebastianmonzel.textextractor;

import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.Locale;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class TxtTextExtractorTest extends AbstractTextExtractorTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void extractText() throws Exception {

        InputStream resourceAsStream = getInputStreamFromResource("test1234.txt");

        String s = TxtTextExtractor.of(resourceAsStream)
                .extractText()
                .getText();

        assertThat(s,is("test1234"));
    }

    @Test
    public void extractTextWithoutStopwords() throws Exception {

        InputStream resourceAsStream = getInputStreamFromResource("sometextwithstopwords.txt");

        String s = TxtTextExtractor.of(resourceAsStream)
                .extractText()
                .removeStopwords(Locale.GERMAN)
                .removePunctuationMarks()
                .getText();

        assertThat(s,is("test"));
    }


}