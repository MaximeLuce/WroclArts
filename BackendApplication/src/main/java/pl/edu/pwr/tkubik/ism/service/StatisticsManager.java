package pl.edu.pwr.tkubik.ism.service;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class StatisticsManager {

    // Stocke le nombre d'appels pour chaque méthode
    private final Map<String, AtomicInteger> methodCalls = new ConcurrentHashMap<>();
    // Compteur total pour calculer les pourcentages
    private final AtomicInteger totalApiCalls = new AtomicInteger(0);
    // Stocke le prix le plus élevé trouvé jusqu'à présent
    private Double highestEventPrice = 0.0;

    // --- Méthodes pour mettre à jour les stats (utilisées par l'Aspect) ---

    public void recordMethodCall(String methodName) {
        methodCalls.computeIfAbsent(methodName, k -> new AtomicInteger(0)).incrementAndGet();
        totalApiCalls.incrementAndGet();
    }

    public synchronized void updateHighestPrice(Double price) {
        if (price != null && price > highestEventPrice) {
            highestEventPrice = price;
        }
    }

    // --- Méthodes pour lire les stats (utilisées par le Contrôleur) ---

    public Map<String, Double> getMethodUsagePercentages() {
        Map<String, Double> percentages = new ConcurrentHashMap<>();
        int total = totalApiCalls.get();

        if (total == 0) return percentages;

        methodCalls.forEach((method, count) -> {
            double percentage = (count.get() * 100.0) / total;
            // Arrondir à 2 décimales
            percentages.put(method, Math.round(percentage * 100.0) / 100.0);
        });

        return percentages;
    }

    public Double getHighestEventPrice() {
        return highestEventPrice;
    }
}