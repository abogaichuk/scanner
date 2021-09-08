package org.me.scanner;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;

import javax.enterprise.context.ApplicationScoped;
import java.io.PrintWriter;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.me.scanner.ScannerUtils.getFirstArgument;
import static org.me.scanner.ScannerUtils.isNotEmpty;

@ApplicationScoped
public class CmdLineService {

    public String buildCommand(CommandLine cmd) {
        final String host = getFirstArgument(cmd);
        return Stream.of(cmd.getOptions())
                .map(option -> {
                    StringBuilder sb = new StringBuilder();
                    if (isNotEmpty(option.getOpt())) {
                        sb.append("-");
                        sb.append(option.getOpt());
                        if (option.hasArg() && isNotEmpty(option.getValue())) {
                            sb.append(" ");
                            sb.append(option.getValue());
                        }
                    } else {
                        sb.append("--");
                        sb.append(option.getLongOpt());
                    }
                    return sb.toString();
                }).collect(Collectors.joining(" ", "nmap ", host));
    }

    public void printHelp(Options options) {
        String commandLineSyntax = "execute ./target/*-runner";
        PrintWriter writer = new PrintWriter(System.out);
        HelpFormatter helpFormatter = new HelpFormatter();
        helpFormatter.printHelp(
                writer,
                80,
                commandLineSyntax,
                "Options: ",
                options,
                3,
                5,
                "--- HELP ---",
                false);
        writer.flush();
    }
}
