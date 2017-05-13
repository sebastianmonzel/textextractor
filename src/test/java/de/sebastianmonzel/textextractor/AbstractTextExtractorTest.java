package de.sebastianmonzel.textextractor;

import java.io.InputStream;

/**
 * Created by dem_m on 13.05.2017.
 */
public class AbstractTextExtractorTest {
    protected InputStream getInputStreamFromResource(String name) {
        return getClass().getClassLoader().getResourceAsStream(name);
    }
}
