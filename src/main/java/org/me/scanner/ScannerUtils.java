package org.me.scanner;

import org.apache.commons.cli.CommandLine;

public class ScannerUtils {

    public static boolean needHelp(CommandLine cmd) {
        return cmd.getOptions().length == 0 || cmd.hasOption("h");
    }

    public static boolean isArgument(CommandLine cmd) {
        return cmd.getArgs().length > 0;
    }

    public static String getFirstArgument(CommandLine cmd) {
        return isArgument(cmd) ? " " + cmd.getArgs()[0] : "";
    }

    public static boolean isEmpty(String s) {
        return s == null || s.isEmpty();
    }

    public static boolean isNotEmpty(String s) {
        return !isEmpty(s);
    }
}
