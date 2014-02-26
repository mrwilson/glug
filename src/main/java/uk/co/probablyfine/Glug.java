package uk.co.probablyfine;

import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.RegexFileFilter;

import java.io.File;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toSet;
import static org.apache.commons.io.FileUtils.listFiles;

public class Glug {

    final AtomicReference<Set<GlugFile>> glugFileCollection;

    private Glug(String... sources) {
        this.glugFileCollection = new AtomicReference<>(new HashSet<GlugFile>());
        setup(sources);
    }

    private void setup(String... sources) {
        final File dir = new File(".");

        final Set<GlugFile> glugFiles = asList(sources)
            .parallelStream()
            .map(x -> new RegexFileFilter(x))
            .flatMap(x -> listFiles(dir, x, DirectoryFileFilter.DIRECTORY).stream())
            .map(GlugFile::new)
            .collect(toSet());

        glugFileCollection.getAndSet(glugFiles);

    }

    public static Glug src(String... sources) {
        return new Glug(sources);
    }

    public Glug pipe(Function<GlugFile, GlugFile> g) {
        final Set<GlugFile> glugFiles = glugFileCollection
            .get()
            .parallelStream()
            .map(g)
            .collect(toSet());

        this.glugFileCollection.getAndSet(glugFiles);
        return this;
    }

}