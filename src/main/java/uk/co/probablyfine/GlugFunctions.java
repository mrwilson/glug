package uk.co.probablyfine;

import java.io.File;
import java.io.IOException;
import java.util.function.Function;

import static org.apache.commons.io.FileUtils.write;

public class GlugFunctions {

    public static Function<GlugFile, GlugFile> print() {
        return glugFile -> {
            System.out.println("Processing " + glugFile.getName());
            return glugFile;
        };
    }

    public static Function<GlugFile, GlugFile> dest(String dest) {
        final File destDir = new File(dest);

        if (!destDir.exists()) { destDir.mkdir(); }

        return glugFile -> {
            try {
                write(new File(destDir, glugFile.getName()), glugFile.getContent());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                return glugFile;
            }
        };
    }
}