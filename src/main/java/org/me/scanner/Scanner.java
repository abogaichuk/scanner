package org.me.scanner;

import javax.enterprise.context.ApplicationScoped;
import java.io.IOException;

@ApplicationScoped
public class Scanner {

    public int execute(String command) throws InterruptedException, IOException {
        System.out.println("executing command: " + command);
        Process process = Runtime.getRuntime().exec(command);
        process.waitFor();
        print(process);
        return process.exitValue();
    }

    private void print(Process process) throws IOException {
        if (process.exitValue() == 0)
            System.out.println(new String(process.getInputStream().readAllBytes()));
        else
            System.out.println(new String(process.getErrorStream().readAllBytes()));
    }
}
