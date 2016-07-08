package name.kevinross.debugtestapp;

import joptsimple.OptionParser;
import joptsimple.OptionSet;
import name.kevinross.tool.AbstractTool;

/**
 * Created by kevinross (contact@kevinross.name) on 2016-07-08.
 */
public class DemoTool extends AbstractTool {
    @Override
    protected void run(String[] args) {
        for (String arg : args) {
            System.out.println(arg);
        }
        System.out.println(getContext().getString(R.string.demo_string));
    }

    /*
        OR using an option parser
     */
    /*
    protected void run(OptionSet opts) {
        System.out.println(opts.valueOf("a").toString());
    }

    @Override
    protected OptionParser getArgParser() {
        return new OptionParser("a:");
    }*/
}
