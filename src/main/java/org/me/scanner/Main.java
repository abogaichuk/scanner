package org.me.scanner;

import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import lombok.SneakyThrows;
import org.apache.commons.cli.*;
import javax.inject.Inject;

import static org.me.scanner.ScannerUtils.*;

@QuarkusMain
public class Main implements QuarkusApplication {

    @Inject
    Options options;

    @Inject
    CmdLineService cmdService;

    @Inject
    Scanner scanner;

    @SneakyThrows
    @Override
    public int run(String... args) {
        CommandLineParser parser = new DefaultParser();
        CommandLine cmdLine = parser.parse(options, args);

        int exit = 0;
        if (needHelp(cmdLine)) {
            cmdService.printHelp(options);
        } else {
            String command = cmdService.buildCommand(cmdLine);
            exit = scanner.execute(command);
        }

        return exit;
    }
}
