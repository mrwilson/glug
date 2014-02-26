package uk.co.probablyfine;

import java.io.File;
import java.io.IOException;

import static org.apache.commons.io.FileUtils.readFileToString;

public class GlugFile {
    private final File file;

    public GlugFile(File x) {
        this.file = x;
    }

    public String getName() {
        return file.getName();
    }

    public String getContent() throws IOException {
        return readFileToString(file);
    }
}
