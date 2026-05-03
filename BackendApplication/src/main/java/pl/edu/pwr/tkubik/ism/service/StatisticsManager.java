package pl.edu.pwr.tkubik.ism.service;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class StatisticsManager {

    // save the nb of call for each methods
    private final Map<String, AtomicInteger> methodCalls = new ConcurrentHashMap<>();
    // main counter to compute the percentages
    private final AtomicInteger totalApiCalls = new AtomicInteger(0);
    // save the highesteventprice
    private Double highestEventPrice = 0.0;

    // methods to update stats (used by aspects)

    public void recordMethodCall(String methodName) {
        methodCalls.computeIfAbsent(methodName, k -> new AtomicInteger(0)).incrementAndGet();
        totalApiCalls.incrementAndGet();
    }

    public synchronized void updateHighestPrice(Double price) {
        if (price != null && price > highestEventPrice) {
            highestEventPrice = price;
        }
    }

    // methods to read stats (used by controllers) ---

    public Map<String, Double> getMethodUsagePercentages() {
        Map<String, Double> percentages = new ConcurrentHashMap<>();
        int total = totalApiCalls.get();

        if (total == 0) return percentages;

        methodCalls.forEach((method, count) -> {
            double percentage = (count.get() * 100.0) / total;
            percentages.put(method, Math.round(percentage * 100.0) / 100.0);
        });

        return percentages;
    }

    public Double getHighestEventPrice() {
        return highestEventPrice;
    }
}