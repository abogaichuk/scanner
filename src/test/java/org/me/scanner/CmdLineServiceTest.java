package org.me.scanner;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

@QuarkusTest
public class CmdLineServiceTest {
    @Inject
    CmdLineService service;

    @Test
    public void buildCommandTest() {
//        Assertions.assertEquals("nmap", service.buildCommand());
    }
}
