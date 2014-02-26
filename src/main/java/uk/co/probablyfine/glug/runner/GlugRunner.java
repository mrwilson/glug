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

public class GlugRunner {

    private static final Map<String, String> classesToLoad = new HashMap<>();
    private static final ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");

    static {{
      classesToLoad.put("glug", Glug.class.getName());
      classesToLoad.put("f",    GlugFunctions.class.getName());
      classesToLoad.put("d",    Destination.class.getName());
    }}

    public static void main(String[] args) throws ScriptException, ClassNotFoundException, IOException {
        loadGlugClasses();
        engine.eval(FileUtils.readFileToString(new File(args[0])));
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
