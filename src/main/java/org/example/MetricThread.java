package org.example;

import software.amazon.cloudwatchlogs.emf.exception.DimensionSetExceededException;
import software.amazon.cloudwatchlogs.emf.exception.InvalidDimensionException;
import software.amazon.cloudwatchlogs.emf.exception.InvalidMetricException;
import software.amazon.cloudwatchlogs.emf.logger.MetricsLogger;
import software.amazon.cloudwatchlogs.emf.model.DimensionSet;
import software.amazon.cloudwatchlogs.emf.model.StorageResolution;
import software.amazon.cloudwatchlogs.emf.model.Unit;

public class MetricThread implements Runnable {

    @Override
    public void run() {
        MetricsLogger metrics = new MetricsLogger();
        while (true) {
            try {
                metrics.putDimensions(DimensionSet.of("Service", "Aggregator"));
                metrics.putMetric("ProcessingLatency", Math.random() * 100, Unit.MILLISECONDS, StorageResolution.STANDARD);
                metrics.putMetric("Memory.HeapUsed", Math.random() * 1600424.555, Unit.BYTES, StorageResolution.HIGH);
                metrics.putMetric("M.HeapUsed", Math.random() * 1600424.555, Unit.BYTES, StorageResolution.HIGH);
                metrics.putProperty("RequestId", "422b1569-16f6-4a03-b8f0-fe3fd9b100f8");
                metrics.putProperty("uri", "https://collections.mcny.org/asset-management/" + Math.round(Math.random() * 100));
                metrics.flush();
                Thread.sleep(Math.round(Math.random() * 100));
            } catch (InvalidDimensionException | InvalidMetricException | DimensionSetExceededException | InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
