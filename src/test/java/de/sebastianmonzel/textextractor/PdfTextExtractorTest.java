package de.sebastianmonzel.textextractor;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class PdfTextExtractorTest extends AbstractTextExtractorTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void extractText() throws Exception {


        String text = PdfTextExtractor
                .of(getInputStreamFromResource("sometextwithstopwords.pdf"))
                .extractText()
                .getText();

        assertThat(text,is("dies ist ein test, " + System.getProperty("line.separator")));

    }

}