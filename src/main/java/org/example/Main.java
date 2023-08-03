package org.example;

import software.amazon.cloudwatchlogs.emf.config.Configuration;
import software.amazon.cloudwatchlogs.emf.config.EnvironmentConfigurationProvider;

import static software.amazon.cloudwatchlogs.emf.environment.Environments.EC2;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Configuration config = EnvironmentConfigurationProvider.getConfig();
        config.setLogGroupName("SethLogGroup");
        config.setEnvironmentOverride(EC2);
        config.setAgentEndpoint("udp://127.0.0.1:25888");
        for (int i = 0; i < 25; i++) {
            MetricThread metricThread = new MetricThread();
            metricThread.run();
        }
        while (true) {
            Thread.sleep(1000000000);
        }
    }
}