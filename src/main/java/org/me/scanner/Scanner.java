package org.me.scanner;

import lombok.SneakyThrows;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Scanner {

    @SneakyThrows
    public int execute(String command) {
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
