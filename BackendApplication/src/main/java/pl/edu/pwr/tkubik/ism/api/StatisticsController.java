package pl.edu.pwr.tkubik.ism.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pwr.tkubik.ism.aspect.LogExecutionTime;
import pl.edu.pwr.tkubik.ism.aspect.LogMethod;
import pl.edu.pwr.tkubik.ism.service.StatisticsManager;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin/statistics")
public class StatisticsController {

    @Autowired
    private StatisticsManager statisticsManager;

    @GetMapping
    @LogMethod
    @LogExecutionTime
    public ResponseEntity<Map<String, Object>> getSystemStatistics() {
        Map<String, Object> stats = new HashMap<>();

        // Ajout du prix d'événement le plus cher jamais chargé par un utilisateur
        stats.put("highestEventPriceRetrieved", statisticsManager.getHighestEventPrice());

        // Ajout de la répartition en pourcentage de l'utilisation de l'API
        stats.put("apiUsagePercentages", statisticsManager.getMethodUsagePercentages());

        return ResponseEntity.ok(stats);
    }
}