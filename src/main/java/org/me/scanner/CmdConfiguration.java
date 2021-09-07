package org.me.scanner;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class CmdConfiguration {
    @Produces
    public Options options() {
        Options options = new Options();
        // host scanning options
        options.addOption("sn", "No port scan");
        options.addOption("sL", "List Scan");
        options.addOption("PR", "ARP Ping");
        options.addOption(Option.builder().longOpt("traceroute").desc("Trace path to host").build());
        options.addOption(Option.builder("PS").desc("TCP SYN Ping").hasArg().numberOfArgs(1).argName("port list").build());
        options.addOption(Option.builder("PA").desc("TCP ACK Ping").hasArg().numberOfArgs(1).argName("port list").build());
        options.addOption(Option.builder("PU").desc("UDP Ping").hasArg().numberOfArgs(1).argName("port list").build());
        options.addOption(Option.builder("PO").desc("IP Protocol Ping").hasArg().numberOfArgs(1).argName("protocol list").build());

        // port scanning options
        options.addOption(Option.builder("p").desc("Only scan specified port").longOpt("port")
                .hasArg().numberOfArgs(1).argName("p").build());
        options.addOption("sS", "TCP SYN scan");
        options.addOption("sT", "TCP connect scan");
        options.addOption("sU", "UDP scans");
        options.addOption("sA", "TCP ACK scan");
        options.addOption("sW", "TCP Window scan");
        options.addOption("sM", "TCP Maimon scan");
        options.addOption("sV", "Version detection");

        //os
        options.addOption("O", "Enable OS detection");
        options.addOption(Option.builder().longOpt("osscan-guess").desc("Guess OS more aggressively").build());

        //scripting
        options.addOption(Option.builder("sC").longOpt("script=default").desc("It is equivalent to --script=default").build());
        options.addOption(Option.builder().longOpt("script-help").desc("Shows help about scripts.")
                .hasArg().numberOfArgs(1).argName("filename").build());
        options.addOption(Option.builder().longOpt("script").desc("Runs a script scan using the comma-separated list of filenames")
                .hasArg().numberOfArgs(1).argName("filenames").build());
        options.addOption(Option.builder().longOpt("script-trace")
                .desc("All incoming and outgoing communication performed by a script is printed").build());

        //miscellaneous
        options.addOption("A", "Aggressive scan option.  Presently this " +
                "enables OS detection (-O), version scanning (-sV), script scanning (-sC) and traceroute (--traceroute)." +
                " You should not use -A against target networks without permission (e.g. root access).");
        options.addOption(Option.builder("oN").desc("Requests that normal output be directed to the given filename")
                .hasArg().numberOfArgs(1).argName("filename").build());
        options.addOption(Option.builder("h").longOpt("help").desc("HELP").build());
        options.addOption(Option.builder("6").longOpt("ipv6").desc("Enable IPv6 scanning").build());
        options.addOption(Option.builder("v").desc("Increase verbosity level")
                .hasArg().numberOfArgs(1).argName("verbosity level").build());
        options.addOption(Option.builder("d").desc("Increase debugging level")
                .hasArg().numberOfArgs(1).argName("debugging level").build());
        options.addOption(Option.builder().longOpt("send-ip").desc("Send at raw IP level").build());
        options.addOption(Option.builder().longOpt("iflist").desc("List interfaces and routes").build());
        options.addOption(Option.builder().longOpt("privileged").desc("Assume that the user is fully privileged").build());
        options.addOption(Option.builder().longOpt("unprivileged").desc("Assume that the user lacks raw socket privileges").build());

        return  options;
    }
}
