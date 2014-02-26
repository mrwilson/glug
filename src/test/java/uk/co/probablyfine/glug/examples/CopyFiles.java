package uk.co.probablyfine.glug.examples;

import static uk.co.probablyfine.Glug.src;
import static uk.co.probablyfine.GlugFunctions.dest;
import static uk.co.probablyfine.GlugFunctions.print;

public class CopyFiles {

    public static void main(String[] args) {
        src(".*.java")
            .pipe(print())
            .pipe(dest("./copy_files_example"))
            .run()
        ;
    }
}
