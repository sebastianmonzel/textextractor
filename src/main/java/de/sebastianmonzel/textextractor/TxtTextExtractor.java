package de.sebastianmonzel.textextractor;

import java.io.*;

public class TxtTextExtractor extends AbstractTextExtractor {

    public static TxtTextExtractor of(File file) {
        return new TxtTextExtractor(file);
    }

    public static TxtTextExtractor of(InputStream inputStream) {
        return new TxtTextExtractor(inputStream);
    }

    public TxtTextExtractor(File file) {
        try {
            this.inputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public TxtTextExtractor(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public AbstractTextExtractor extractText() {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        try {
            String line;
            while ((line = br.readLine()) != null) {
                stringBuilder.append(line);
                if ( br.ready() ) {
                    stringBuilder.append(System.getProperty(LINE_SEPARATOR));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        text = stringBuilder.toString();

        return this;
    }

}
