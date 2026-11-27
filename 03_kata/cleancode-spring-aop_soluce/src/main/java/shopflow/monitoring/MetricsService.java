package shopflow.monitoring;

import org.springframework.stereotype.Service;

import java.util.*;

import shopflow.model.Models.Metric;

@Service
public class MetricsService {
    private final List<Metric> entries = new ArrayList<>();

    public void record(String operation, long start, long threshold) {
        long duration = (System.nanoTime() - start) / 1_000_000;
        entries.add(new Metric(operation, duration, threshold, duration > threshold));
    }

    public List<Metric> entries() {
        return List.copyOf(entries);
    }

    public void reset() {
        entries.clear();
    }
}
