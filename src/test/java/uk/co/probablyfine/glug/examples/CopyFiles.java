package uk.co.probablyfine.glug.examples;

import uk.co.probablyfine.glug.Glug;

import static uk.co.probablyfine.glug.Glug.src;
import static uk.co.probablyfine.glug.functions.Destination.dest;
import static uk.co.probablyfine.glug.functions.GlugFunctions.print;

public class CopyFiles {

    public static void main(String[] args) {
        src(".*.java")
            .pipe(print())
            .pipe(dest("./copy_files_example"))
            .run()
        ;
    }
}
