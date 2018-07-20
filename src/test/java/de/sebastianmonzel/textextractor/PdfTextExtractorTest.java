package de.sebastianmonzel.textextractor;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class PdfTextExtractorTest extends AbstractTextExtractorTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void extractText() throws Exception {


        String extractedText = PdfTextExtractor
                .of(getInputStreamFromResource("sometextwithstopwords.pdf"))
                .extractText()
                .getResultedText();

        assertThat(extractedText,containsString("dies    ist   ein   test,"));

    }

}