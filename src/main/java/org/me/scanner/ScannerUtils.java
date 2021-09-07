package org.me.scanner;

import org.apache.commons.cli.CommandLine;

public class ScannerUtils {
    public static boolean needHelp(CommandLine cmd) {
        return cmd.getOptions().length == 0 || cmd.hasOption("h");
    }
}
