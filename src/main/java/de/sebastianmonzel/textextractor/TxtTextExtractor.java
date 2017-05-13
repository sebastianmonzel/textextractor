package de.sebastianmonzel.textextractor;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;


public class TxtTextExtractor extends AbstractTextExtractor {

    @Override
    public AbstractTextExtractor extractText(File file) {
        try {
            Path path = file.toPath();
            byte[] bytes = Files.readAllBytes(path);
            text = new String(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

    @Override
    public AbstractTextExtractor extractText(InputStream inputStream) {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        try {
            String line;
            while ((line = br.readLine()) != null) {
                stringBuilder.append(line);
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
