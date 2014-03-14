package uk.co.probablyfine.glug;

import org.apache.commons.io.filefilter.RegexFileFilter;

import java.io.File;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static org.apache.commons.io.FileUtils.listFiles;
import static org.apache.commons.io.filefilter.DirectoryFileFilter.DIRECTORY;

class GlugTask {
    private final String[] sources;
    private static final File CWD = new File(".");
    public Function<GlugFile, GlugFile> function = (file) -> file;

    public GlugTask(String... sources) {
        this.sources = sources;
    }

    public void run() {
        asList(this.sources).parallelStream()
            .map(x -> new RegexFileFilter(x))
            .flatMap(x -> listFiles(CWD, x, DIRECTORY).stream())
            .distinct()
            .map(GlugFile::new)
            .map(function)
            .collect(Collectors.toSet());
    }

    public GlugTask pipe(Function<GlugFile, GlugFile> g) {
        this.function = function.compose(g);
        return this;
    }

}
