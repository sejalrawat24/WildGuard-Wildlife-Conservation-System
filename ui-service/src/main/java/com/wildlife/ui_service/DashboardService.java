
package com.wildlife.ui_service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DashboardService {

    private final RestTemplate restTemplate;

    public DashboardService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private List<Map<String, Object>> fetchList(String url) {
        try {
            List<Map<String, Object>> result =
                restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<
                        List<Map<String, Object>>>() {}
                ).getBody();

            return result == null
                    ? Collections.emptyList()
                    : result;

        } catch (Exception e) {
            System.err.println(
                "Dashboard statistics error [" + url + "]: "
                + e.getMessage()
            );

            return Collections.emptyList();
        }
    }

    private long countStatus(
            List<Map<String, Object>> records,
            String field,
            String expected) {

        return records.stream()
            .filter(record -> {
                Object value = record.get(field);

                return value != null
                    && expected.equalsIgnoreCase(
                        value.toString().trim()
                    );
            })
            .count();
    }

    public DashboardStats getStats() {

        DashboardStats stats = new DashboardStats();

        List<Map<String, Object>> animals =
            fetchList("http://localhost:8081/api/animals");

        List<Map<String, Object>> programmes =
            fetchList("http://localhost:8082/programmes/api/all");

        List<Map<String, Object>> incidents =
            fetchList("http://localhost:8083/incidents/all");

        List<Map<String, Object>> rescues =
            fetchList("http://localhost:8083/rescues/api/all");

        List<Map<String, Object>> patrols =
            fetchList("http://localhost:8084/patrols/api/all");

        List<Map<String, Object>> staff =
            fetchList("http://localhost:8084/staff/api/all");

        List<Map<String, Object>> resources =
            fetchList("http://localhost:8084/resources/api/all");

        stats.setTotalAnimals(animals.size());

        stats.setHealthyAnimals(
            countStatus(animals, "health_Status", "HEALTHY")
        );

        stats.setInjuredAnimals(
            countStatus(animals, "health_Status", "INJURED")
        );

        stats.setTotalProgrammes(programmes.size());
        stats.setTotalIncidents(incidents.size());
        stats.setTotalRescues(rescues.size());

        stats.setTotalPatrols(patrols.size());

        stats.setScheduledPatrols(
            countStatus(patrols, "status", "SCHEDULED")
        );

        stats.setOngoingPatrols(
            countStatus(patrols, "status", "ONGOING")
        );

        stats.setCompletedPatrols(
            countStatus(patrols, "status", "COMPLETED")
        );

        stats.setTotalStaff(staff.size());
        stats.setTotalResources(resources.size());

        return stats;
    }
}
