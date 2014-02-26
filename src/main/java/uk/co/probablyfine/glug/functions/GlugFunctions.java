package uk.co.probablyfine.glug.functions;

import uk.co.probablyfine.glug.GlugFile;

import java.util.function.Function;

public class GlugFunctions {

    public static Function<GlugFile, GlugFile> print() {
        return glugFile -> {
            System.out.println("Processing " + glugFile.getName());
            return glugFile;
        };
    }

}