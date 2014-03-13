package uk.co.probablyfine.glug.runner;

import org.apache.commons.io.FileUtils;
import uk.co.probablyfine.glug.Glug;
import uk.co.probablyfine.glug.functions.Destination;
import uk.co.probablyfine.glug.functions.GlugFunctions;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static java.lang.String.format;
import static org.apache.commons.io.FileUtils.readFileToString;

public class GlugRunner {

    private static final Map<String, String> classesToLoad = new HashMap<>();
    private static final ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");

    static {{
      classesToLoad.put("glug", Glug.class.getName());
      classesToLoad.put("f",    GlugFunctions.class.getName());
      classesToLoad.put("d",    Destination.class.getName());
    }}

    private static void runGlugTasks(String glugFile, String taskName) throws IOException, ScriptException {
        loadGlugClasses();
        engine.eval(readFileToString(new File(glugFile)));
        engine.eval(format("glug.run(\"%s\")", taskName));
    }

    public static void main(String[] args) throws ScriptException, ClassNotFoundException, IOException {
        switch (args.length) {
            case 0:
                runGlugTasks("Glugfile", "default");
            case 1:
                runGlugTasks("Glugfile", args[0]);
            default:
                runGlugTasks(args[0], args[1]);
        }
    }

    private static void loadGlugClasses() {
        classesToLoad.entrySet().stream().forEach(entry -> {
            String script = format("var %s = Java.type(\"%s\");", entry.getKey(), entry.getValue());
            try {
                engine.eval(script);
            } catch (ScriptException e1) {
                e1.printStackTrace();
            }
        });
    }
}
