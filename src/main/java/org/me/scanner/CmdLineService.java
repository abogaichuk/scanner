package org.me.scanner;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;

import javax.enterprise.context.ApplicationScoped;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.me.scanner.ScannerUtils.getFirstArgument;

@ApplicationScoped
public class CmdLineService {

    public String buildCommand(CommandLine cmd) {
        final String host = getFirstArgument(cmd);
        return Stream.of(cmd.getOptions())
                .map(option -> {
                    StringBuilder sb = new StringBuilder("-");
                    sb.append(option.getOpt());
                    if (option.hasArg() && option.getValue() != null) {
                        sb.append(" ");
                        sb.append(option.getValue());
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
