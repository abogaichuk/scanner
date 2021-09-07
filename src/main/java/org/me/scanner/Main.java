package org.me.scanner;

import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import lombok.SneakyThrows;
import org.apache.commons.cli.*;
import javax.inject.Inject;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.me.scanner.ScannerUtils.needHelp;

@QuarkusMain
public class Main implements QuarkusApplication {

    @Inject
    Options options;

    @SneakyThrows
    @Override
    public int run(String... args) {
        CommandLineParser parser = new DefaultParser();
        CommandLine cmdLine = parser.parse(options, args);

        int exit = 0;
        if (needHelp(cmdLine)) {
            printHelp(options, System.out);
        } else {
            String command = buildCommand(cmdLine);
            exit = execute(command);
        }

        return exit;
    }

    public String buildCommand(CommandLine cmd) {
        final String host = cmd.getArgs().length > 0 ? " " + cmd.getArgs()[0] : "";
        return Stream.of(cmd.getOptions())
                .map(option -> {
                    StringBuilder sb = new StringBuilder("-");
                    sb.append(option.getOpt());
                    if (option.hasArg() && option.getValue() != null) {
                        sb.append(" ");
                        sb.append(option.getValue());
                    }
                    return sb.toString();
                })
                .collect(Collectors.joining(" ", "nmap ", host));
    }

    public void printHelp(Options options, OutputStream out) {
        String commandLineSyntax = "execute ./target/*-runner";
        PrintWriter writer = new PrintWriter(out);
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

    @SneakyThrows
    private int execute(String command) {
        System.out.println("executing command: " + command);
        Process process = Runtime.getRuntime().exec(command);
        process.waitFor();
        print(process);
        return process.exitValue();
    }

    @SneakyThrows
    private void print(Process process) {
        if (process.exitValue() == 0)
            System.out.println(new String(process.getInputStream().readAllBytes()));
        else
            System.out.println(new String(process.getErrorStream().readAllBytes()));
    }
}
