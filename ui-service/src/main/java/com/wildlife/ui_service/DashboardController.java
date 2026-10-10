
package com.wildlife.ui_service;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.ResourceAccessException;

import com.wildlife.ui_service.DashboardService;

@Controller
public class DashboardController {

    private final RestTemplate restTemplate;
    private final DashboardService dashboardService;

    public DashboardController(
            RestTemplate restTemplate,
            DashboardService dashboardService) {
        this.restTemplate = restTemplate;
        this.dashboardService = dashboardService;
    }

    @GetMapping("/")
    public String showDashboard(Model model) {

        model.addAttribute("wildlifeStatus",
                checkService("http://localhost:8081"));

        model.addAttribute("conservationStatus",
                checkService("http://localhost:8082"));

        model.addAttribute("incidentStatus",
                checkService("http://localhost:8083"));

        model.addAttribute("fieldStatus",
                checkService("http://localhost:8084"));

        model.addAttribute("stats", dashboardService.getStats());

        return "dashboard";
    }

    private String checkService(String url) {
        try {
            restTemplate.getForObject(url, String.class);
            return "Online";

        } catch (RestClientResponseException e) {
            return "Online";

        } catch (ResourceAccessException e) {
            return "Offline";

        } catch (Exception e) {
            return "Offline";
        }
    }
}