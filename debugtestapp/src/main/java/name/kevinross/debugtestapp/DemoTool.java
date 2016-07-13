package name.kevinross.debugtestapp;

import joptsimple.OptionParser;
import joptsimple.OptionSet;
import name.kevinross.tool.AbstractTool;

/**
 * Created by kevinross (contact@kevinross.name) on 2016-07-08.
 */
public class DemoTool extends AbstractTool {
    @Override
    protected OptionParser getArgParser() {
        return new OptionParser("demos:");
    }
    protected void run(OptionSet options) {
        for (char s : "demo".toCharArray()) {
            System.out.println(String.format("has %s ? %s", String.valueOf(s), Boolean.toString(options.has(String.valueOf(s)))));
        }
        Boolean hasS = options.hasArgument("s");
        System.out.println(String.format("has s ? %s=%s", Boolean.toString(options.has("s")), hasS ? options.valueOf("s").toString() : "null"));
        for (String arg : getArgs()) {
            System.out.println(arg);
        }
    }
}
