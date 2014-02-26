package uk.co.probablyfine;

import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.RegexFileFilter;

import java.io.File;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static org.apache.commons.io.FileUtils.listFiles;

public class Glug {

    private Function<GlugFile, GlugFile> function;
    private String[] sources;

    private Glug(String... sources) {
        this.sources = sources;
        this.function = (file) -> file;
    }

    public static Glug src(String... sources) {
        return new Glug(sources);
    }

    public void run() {
        final File dir = new File(".");

        asList(this.sources)
            .parallelStream()
            .map(x -> new RegexFileFilter(x))
            .flatMap(x -> listFiles(dir, x, DirectoryFileFilter.DIRECTORY).parallelStream())
            .distinct()
            .map(GlugFile::new)
            .map(function)
            .collect(Collectors.toSet());
    }

    public Glug pipe(Function<GlugFile, GlugFile> g) {
        this.function = function.compose(g);
        return this;
    }

}