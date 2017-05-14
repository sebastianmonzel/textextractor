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

        String extractedText = TxtTextExtractor.of(resourceAsStream)
                .extractText()
                .getText();

        assertThat(extractedText,is("test1234"));
    }

    @Test
    public void extractTextWithoutStopwords() throws Exception {

        InputStream resourceAsStream = getInputStreamFromResource("sometextwithstopwords.txt");

        String extractedText = TxtTextExtractor.of(resourceAsStream)
                .extractText()
                .removeStopwords(Locale.GERMAN)
                .removePunctuationMarks()
                .getText();

        assertThat(extractedText,is("test"));
    }

    @Test
    public void extractTextByRegex() throws Exception {

        InputStream resourceAsStream = getInputStreamFromResource("sometextwithstopwords.txt");

        String extractedText = TxtTextExtractor.of(resourceAsStream)
                .extractText()
                .removePunctuationMarks()
                .filterByRegularExpression(".*(ist).*")
                .getText();

        assertThat(extractedText,is("ist"));
    }

}